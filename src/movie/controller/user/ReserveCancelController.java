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

public class ReserveCancelController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String ticketCode = request.getParameter("ticketCode");
		int cnt = TicKetDAO.getInstance().cancelTicketByCode(ticketCode);
		if (cnt > 0) {
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("log");
			ArrayList<UserReservationView> list = TicKetDAO.getInstance().getTicketListById(user_id);
			session.setAttribute("myBookedList", list);
			response.getWriter().print("canceled");
		} else {
			response.getWriter().print("notCanceled");
		}
		return null;
	}
}
