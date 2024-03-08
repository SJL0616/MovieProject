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

public class AdminController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<UserReservationView> list = TicKetDAO.getInstance().getAllCanceledTickets();
		request.setAttribute("userList", list);
		ArrayList<Review> reviewList = ReviewDAO.getInstance().getAllReportedReviews();
		request.setAttribute("reviewList", reviewList);
		System.out.println("어드민 컨트롤러입니다.");
		return "admin";
	}
}
