package movie.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import movie.dao.MovieDAO;
import movie.dao.ReviewDAO;
import movie.vo.Movie;

public class IndexController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("log");
		if(userID == null) userID = "";
		ArrayList<Movie> topFour =  MovieDAO.getInstance().getTopFour(userID);
		
		
		
		  for(Movie m : topFour) {
			  System.out.println(m.getMovieID());
			  int avg = ReviewDAO.getInstance().getAvgPoint(m.getMovieID());
			  m.setPointAvg(avg);
				/*
				 * System.out.println(m.getTitle() +" "+m.isMyLike()); Gson gson = new Gson();
				 * String show = gson.toJson(m); // System.out.println("m "+ show);
				 */
		  }
		 
		
		  
		 request.setAttribute("list", topFour);
		 
		return "main";
	}

}
