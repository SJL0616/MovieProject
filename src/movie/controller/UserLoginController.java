// 작성자 : 서원우
// 내용 : 로그인 컨트롤러입니다.
// 최초 작성일 : 24-02-22
// 마지막 수정일 : 24-02-22
package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String inputId = request.getParameter("id");
		String inputPw = request.getParameter("pw");
		System.out.printf("inputId : %s, inputPw : %s\n", inputId, inputPw);
		if (request.getParameter("id") == null)
			return "memberLogin";
		UserVO userVO = UserDAO.getInstance().getTheUserById(inputId);
		response.setContentType("text/html; charset=UTF-8");
		if (userVO == null || userVO.getPw().equals(inputPw) == false) {
			System.out.println("실패");
			response.getWriter().print("notValid");
		} else {
			HttpSession session = request.getSession();
			System.out.println("성공");
			session.setAttribute("log", userVO.getId());
			session.setAttribute("userVO", userVO);
			response.getWriter().print("valid");
		}
		return null;
	}
}
