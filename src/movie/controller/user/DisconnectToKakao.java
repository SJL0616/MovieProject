package movie.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.UserDAO;
import movie.vo.UserVO;

public class DisconnectToKakao implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		int cnt = UserDAO.getInstance().disconnectToKakao(user.getId());
		if (cnt == 1) {
			System.out.println("카카오 연결 해제 성공");
		} else {
			System.out.println("카카오 연결 해제 실패");
		}
		user = UserDAO.getInstance().getTheUserById(user.getId());
		session.setAttribute("user", user);
		return "userMyMega";
	}
}
