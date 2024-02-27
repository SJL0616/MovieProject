package movie.controller.movie;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.controller.Controller;
import movie.dao.MovieDAO;
import movie.vo.Movie;

public class MovieListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Movie> list = MovieDAO.getInstance().getTotalList();

		/*
		 * for(Movie m : topFour) {
		 * 
		 * Gson gson = new Gson(); String show = gson.toJson(m);
		 * System.out.println("m "+ show); }
		 */

		request.setAttribute("list", list);
		return "movie";
	}

}
