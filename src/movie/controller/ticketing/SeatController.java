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

		response.setContentType("application/json; charset=utf-8");
		
		//영화 코드 영화관 코드 관람날짜
		int thcd = Integer.parseInt(request.getParameter("movieThcd"));
		int movieID = Integer.parseInt(request.getParameter("moiveID"));
		String time = request.getParameter("time");
		String previewDate = request.getParameter("previewDate");
		// 시간 넣기
		previewDate += " "+ time.substring(0,2) + ":" + time.substring(2);
		
		System.out.println(previewDate);
		PrintWriter out = response.getWriter();
		
		Seat vo = new Seat();
		vo.setMovieThcd(thcd);
		vo.setPreviewDate(previewDate.trim());
		vo.setMovieID(movieID);
		
		List<Seat> list = SeatDAO.getInstance().seatCheckList(vo);
		
		Gson gson = new Gson();

		String json = gson.toJson(list);
		System.out.println(json);
		out.print(json);
		return null;
	}
}
