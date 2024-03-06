package movie.controller.movie.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;

public class ReviewCntController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("log");
		if(userID == null) {
			response.getWriter().print("userIDIsNull");
			return null;
		}
		
		
		return null;
	}

}
