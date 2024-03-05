package movie.controller.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.TicKetDAO;
import movie.vo.UserReservationView;
import movie.vo.UserVO;

public class UserMyMegaController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		ArrayList<UserReservationView> myBookedList = TicKetDAO.getInstance().getTicketListById(user.getId());
		if (myBookedList.size() == 0)
			return "userMyMega";
		session.setAttribute("myBookedList", myBookedList);
		myBookedList.forEach((obj) -> {
			System.out.println(obj.toString());
		});
		return "userMyMega";
	}
}
