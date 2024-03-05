package movie.controller.movie.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.controller.Controller;
import movie.dao.ReviewDAO;
import movie.dao.ReviewLikesDAO;

public class ReviewLikeController  implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = "qwer"; //추후 수정 예정
		String reviewID = request.getParameter("reviewID");
		
		int result1 = ReviewLikesDAO.getInstance().insertReviewLike(Integer.parseInt(reviewID), userID);
		int result2 = ReviewDAO.getInstance().plusLikeCount(Integer.parseInt(reviewID));
		int totalResult = (result1 == 1 && result2 == 1) ? 1 : 0;
		
		response.getWriter().print(totalResult);
		response.getWriter().close();
		
		return null;
	}

}
