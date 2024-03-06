package movie.controller.movie.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.ReviewDAO;

public class ReviewCntController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("log");
		String mid = request.getParameter("movieID");
		if(userID == null || mid == null) {
			response.getWriter().print("paramIsNull");
			response.getWriter().close();
			return null;
		}
		int cnt = ReviewDAO.getInstance().getMyReviewCount(Integer.parseInt(mid), userID);
		response.getWriter().print(cnt);
		response.getWriter().close();
		
		return null;
	}

}
