package movie.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.UserDAO;
import movie.vo.UserVO;

public class UserResignController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		int cnt = UserDAO.getInstance().resignUser(user.getId());
		if (cnt > 0) {
			session.invalidate();
			String ctx = request.getContextPath();
			System.out.println("회원탈퇴 성공");
			return "redirect:" + ctx + "/index.jsp";
		}
		System.out.println("회원탈퇴 실패");
		return null;
	}
}
