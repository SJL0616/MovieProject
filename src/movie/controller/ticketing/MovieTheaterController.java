package movie.controller.ticketing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import movie.controller.Controller;
import movie.dao.MovieTheaterDAO;
import movie.vo.MovieTheater;

public class MovieTheaterController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// json 으로 보내기
		response.setContentType("application/json; charset=utf-8");
		
		List<MovieTheater> list = MovieTheaterDAO.getInstance().movieTheaterList();
		
		request.setAttribute("list",list);
		

		
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
	
		String json = gson.toJson(list);
		
		out.print(json);
		
		return null;
	}

}
