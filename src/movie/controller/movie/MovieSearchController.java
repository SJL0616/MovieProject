package movie.controller.movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import movie.controller.Controller;
import movie.dao.MovieDAO;
import movie.vo.Movie;
public class MovieSearchController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keywords = request.getParameter("keywords");
		String isFromIndex = request.getParameter("fromIndex");
		Gson gson = new Gson();
		
		List<String> keywordList = gson.fromJson(keywords,new TypeToken<List<String>>() {}.getType());
		for(String word : keywordList) {
			System.out.println(word);
		}
		ArrayList<Movie> list = MovieDAO.getInstance().searchMovie(keywordList);
		
		/*
		 * for(Movie m : list) { String show = gson.toJson(m); System.out.println("m "+
		 * show); }
		 */
		if(isFromIndex == null) {
			JsonArray jsonRList = new JsonArray ();
		    for(Movie m : list) {
				jsonRList.add(gson.toJson(m));
			};
			
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print(jsonRList.toString());
			response.getWriter().close();
			return null;
		}else {
			request.setAttribute("list", list);
			return "movie";
		}
	    
		
		
	}

}
