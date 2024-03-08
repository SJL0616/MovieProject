package movie.controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.MovieDAO;

public class MovieLikeController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("log");
		if(userID == null) {
			return null;
		}
		String movieID = request.getParameter("movieID");
		String isLike = request.getParameter("isLike");
		int result = 0;
		if(isLike.equals("true")) {
			result = MovieDAO.getInstance().likeMovie(movieID, userID);
		}else {
			result = MovieDAO.getInstance().dislikeMovie(movieID, userID);
		}
		return null;
	}

}
