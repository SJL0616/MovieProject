package movie.controller.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.TicKetDAO;
import movie.vo.Ticket;
import movie.vo.UserReservationView;

public class ReserveCancelController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int ticketCode = Integer.parseInt(request.getParameter("ticketCode"));
		Ticket ticket = TicKetDAO.getInstance().getTicketByCode(ticketCode);

		int cnt = TicKetDAO.getInstance().cancelTicketByCode(ticketCode); // 좌석을 되돌려놓은 후 예매객체 삭제
		if (cnt > 0) {
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("log");
			ArrayList<UserReservationView> list = TicKetDAO.getInstance().getTicketListById(user_id);
			if (list.size() == 0) {
				session.removeAttribute("myBookedList");
			} else {
				session.setAttribute("myBookedList", list);
			}
			response.getWriter().print("canceled");
		} else {
			response.getWriter().print("notCanceled");
		}
		return null;
	}
}
