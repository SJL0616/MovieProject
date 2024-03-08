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

public class AdminReviewDeleteController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int reviewID = Integer.parseInt(request.getParameter("reviewID"));
		System.out.println("review_ID : " + reviewID);
		ReviewDAO.getInstance().delete(reviewID);
		ArrayList<Review> reviewList = ReviewDAO.getInstance().getAllReportedReviews();
		request.setAttribute("reviewList", reviewList);
		ArrayList<UserReservationView> list = TicKetDAO.getInstance().getAllCanceledTickets();
		request.setAttribute("userList", list);
		String ctx = request.getContextPath();
		return "redirect:" + ctx + "/admin.do";
	}
}
