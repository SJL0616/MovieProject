package movie.controller.ticketing;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.controller.Controller;
import movie.dao.MovieTheaterDAO;
import movie.vo.MovieTheater;

public class MovieTheaterController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ArrayList<MovieTheater> list = (ArrayList<MovieTheater>)MovieTheaterDAO.getInstance().movieTheaterList();
		
		request.setAttribute("list",list);
		
		return "reservation/movietheater";
	}

}
