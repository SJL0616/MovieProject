package movie.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.UserDAO;
import movie.vo.UserVO;

public class UserUpdateController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		String id = user.getId();
		String inputPhone = request.getParameter("inputPhone");
		String inputEmail = request.getParameter("inputEmail");
		String newPw = request.getParameter("newPw");
		int cnt = UserDAO.getInstance().updateUser(id, inputPhone, inputEmail, newPw);
		if (cnt > 0) {
			System.out.println("개인정보를 수정했습니다.");
			user = UserDAO.getInstance().getTheUserById(id);
			session.setAttribute("user", user);
			return "userMyMega";
		} else {
			System.out.println("개인정보 수정에 실패했습니다.");
			return null;
		}
	}
}
