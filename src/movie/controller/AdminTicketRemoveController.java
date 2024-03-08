package movie.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.dao.ReviewDAO;
import movie.dao.TicKetDAO;
import movie.vo.Review;
import movie.vo.UserReservationView;

public class AdminTicketRemoveController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int ticketID = Integer.parseInt(request.getParameter("ticketID"));
		TicKetDAO.getInstance().cancelTicketByID(ticketID);
		ArrayList<UserReservationView> list = TicKetDAO.getInstance().getAllCanceledTickets();
		request.setAttribute("userList", list);
		ArrayList<Review> reviewList = ReviewDAO.getInstance().getAllReportedReviews();
		request.setAttribute("reviewList", reviewList);
		System.out.println("환불 완료.");
		String ctx = request.getContextPath();
		return "redirect:" + ctx + "/admin.do";
	}
}
