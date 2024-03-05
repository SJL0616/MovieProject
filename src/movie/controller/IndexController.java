package movie.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import movie.dao.MovieDAO;
import movie.vo.Movie;

public class IndexController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Movie> topFour =  MovieDAO.getInstance().getTopFour();
		
		/*
		  for(Movie m : topFour) {
		  
		  Gson gson = new Gson(); String show = gson.toJson(m);
		  System.out.println("m "+ show); 
		  }
		  */
		
		  
		 request.setAttribute("list", topFour);
		 
		return "main";
	}

}
