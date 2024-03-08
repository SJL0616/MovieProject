package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.dao.ReviewDAO;

public class AdminReviewDeleteController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int reviewID = Integer.parseInt(request.getParameter("reviewID"));
		ReviewDAO.getInstance().delete(reviewID);
		String ctx = request.getContextPath();
		return "redirect:" + ctx + "/admin.do";
	}
}
