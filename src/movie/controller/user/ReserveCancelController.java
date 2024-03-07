package movie.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import movie.controller.Controller;
import movie.dao.SeatDAO;
import movie.dao.TicKetDAO;
import movie.vo.Ticket;
import movie.vo.UserReservationView;

public class ReserveCancelController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int ticketID = Integer.parseInt(request.getParameter("ticketCode"));
		Ticket ticket = TicKetDAO.getInstance().getTicketByID(ticketID);
		String beforeSplit = ticket.getSeatIDList();
		String[] afterSplit = beforeSplit.split(",");

		// 취소된 좌석들을 돌려놓는 반복문입니다.
		for (int i = 0; i < afterSplit.length; i += 1) {
			SeatDAO.getInstance().putTheSeatsBack(Integer.parseInt(afterSplit[i]));
		}

		// 좌석을 전부 되돌려놓은 후에 예매객체의 canceled 컬럼이 ture 가 됩니다
		int cnt = TicKetDAO.getInstance().setTrueToTicket(ticketID);

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
