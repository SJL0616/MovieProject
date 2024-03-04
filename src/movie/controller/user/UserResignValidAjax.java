package movie.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.UserDAO;
import movie.vo.UserVO;

public class UserResignValidAjax implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		user = UserDAO.getInstance().getTheUserById(user.getId());
		String resignPw = request.getParameter("resignPw");
		System.out.println("curPw : " + resignPw);
		System.out.println("myPw : " + user.getPw());
		if (user.getPw().equals(resignPw)) {
			response.getWriter().print("resignValid");
			return null;
		} else {
			response.getWriter().print("resignInvalid");
			return null;
		}
	}
}
