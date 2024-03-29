package movie.controller.movie;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import movie.controller.Controller;
import movie.dao.MovieDAO;
import movie.vo.Movie;

public class MovieListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("log");
		if(userID == null) userID = "";
		ArrayList<Movie> list = MovieDAO.getInstance().getTotalList(userID);

		/*
		 * for(Movie m : list) {
		 * 
		 * Gson gson = new Gson(); String show = gson.toJson(m);
		 * System.out.println("m "+ show); }
		 */

		request.setAttribute("list", list);
		return "movie";
	}

}
