package movie.controller.movie.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.controller.Controller;
import movie.dao.ReviewDAO;

public class ReviewReportController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reviewID = request.getParameter("reviewID");
		int result = ReviewDAO.getInstance().report(Integer.parseInt(reviewID) );
		
		response.getWriter().print(result);
		response.getWriter().close();
		
		return null;
	}

}
