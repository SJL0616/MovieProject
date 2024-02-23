package movie.controller.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import movie.controller.Controller;
import movie.dao.UserDAO;
import movie.vo.UserVO;

public class KakaoController implements Controller {
	private static KakaoRestApiHelper apiHelper = new KakaoRestApiHelper();

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String code = request.getParameter("code");
		apiHelper.setAccessToken(getKakaoAccessToken(code));
		String myJsonData = apiHelper.accessToken();
		String[] tempList = myJsonData.split(",");
		String kakaoId = tempList[1].substring(5);
		apiHelper.unlink();
		UserVO user = UserDAO.getInstance().getTheUserByKakaoId(kakaoId);
		if (user == null) {
			response.getWriter().print("notValid");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("log", user.getId());
			session.setAttribute("user", user);
			response.getWriter().print("valid");
		}
		return null;
	}

	private String getKakaoAccessToken(String code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=21ab5b4db87c2754b7ad5637ffdc7eb3"); // TODO REST_API_KEY 입력
			sb.append("&redirect_uri=http://localhost:8085/MovieProject/kakaoLoginResult.jsp"); // TODO 인가코드 받은
																								// redirect_uri
			// 입력
			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			@SuppressWarnings("deprecation")
			JsonParser parser = new JsonParser();
			@SuppressWarnings("deprecation")
			JsonElement element = parser.parse(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_Token;
	}
}
