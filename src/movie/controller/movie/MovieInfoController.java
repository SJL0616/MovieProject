package movie.controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.controller.Controller;
import movie.dao.MovieDAO;
import movie.vo.Movie;

public class MovieInfoController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id == null) {
			return null;
		}
		Movie m = MovieDAO.getInstance().getOne(Integer.parseInt(id));
		request.setAttribute("vo", m);
		return "movie-detail";
	}

}
