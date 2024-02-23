// 작성자 : 서원우
// 내용 : 로그인 컨트롤러입니다.
// 최초 작성일 : 24-02-22
// 마지막 수정일 : 24-02-22 (서원우)
package movie.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.UserDAO;
import movie.vo.UserVO;

public class UserLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String inputId = request.getParameter("id");
		String inputPw = request.getParameter("pw");
		System.out.printf("inputId : %s, inputPw : %s\n", inputId, inputPw);
		if (request.getParameter("id") == null)
			return "userLogin";
		UserVO user = UserDAO.getInstance().getTheUserById(inputId);
		System.out.println("user : " + user);
		response.setContentType("text/html; charset=UTF-8");
		if (user == null || user.getPw().equals(inputPw) == false) {
			System.out.println("실패");
			response.getWriter().print("notValid");
		} else {
			HttpSession session = request.getSession();
			System.out.println("성공");
			session.setAttribute("log", user.getId());
			session.setAttribute("user", user);
			response.getWriter().print("valid");
		}
		return null;
	}
}
