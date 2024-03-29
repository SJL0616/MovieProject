package movie.controller.ticketing;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.controller.Controller;
import movie.dao.MovieDAO;
import movie.vo.Movie;

public class TicketingController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//영화 리스트를 뽑아 가지고오기
		
		ArrayList<Movie> list = MovieDAO.getInstance().getTotalList();
		String move = "";
		if(request.getParameter("move") != null) {
			move = request.getParameter("move");
			request.setAttribute("move", move);
		}
		request.setAttribute("list", list);
		
		for(Movie m : list) {
			System.out.println(m);
		}

		System.out.println(move);
		return "ticketing";
	}

}
