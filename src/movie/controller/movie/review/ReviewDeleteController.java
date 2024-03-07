package movie.controller.movie.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.controller.Controller;
import movie.dao.ReviewDAO;

public class ReviewDeleteController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reviewID = request.getParameter("reviewID");
		int result = ReviewDAO.getInstance().delete(Integer.parseInt(reviewID) );
		
		response.getWriter().print(result);
		response.getWriter().close();
		return null;
	}

}
