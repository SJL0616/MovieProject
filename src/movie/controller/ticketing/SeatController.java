package movie.controller.ticketing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import movie.controller.Controller;
import movie.dao.MovieTheaterDAO;
import movie.dao.SeatDAO;
import movie.vo.MovieTheater;
import movie.vo.Seat;

public class SeatController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int thcd = Integer.parseInt(request.getParameter("btn"));
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List<Seat> list = SeatDAO.getInstance().seatCheckList(thcd);
		
		Gson gson = new Gson();
	
		String json = gson.toJson(list);
		System.out.println(json);
		out.print(json);
		return null;
	}
}
