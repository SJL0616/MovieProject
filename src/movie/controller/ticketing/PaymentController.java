package movie.controller.ticketing;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.SeatDAO;
import movie.dao.TicKetDAO;
import movie.vo.Seat;
import movie.vo.Ticket;

public class PaymentController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html charset=utf-8");
		
		HttpSession session = request.getSession();
		
		int movieID = Integer.parseInt(request.getParameter("movie-code")); // 영화 코드
		String userID = (String)session.getAttribute("log"); // 유저 아이디
		int numberPeople = Integer.parseInt(request.getParameter("count")); // 인원수
		String movieName = request.getParameter("movie-theater"); // 영화관 명
		
		String time = request.getParameter("movie-time"); // 관란 시간
		String previewDate = request.getParameter("select-date"); // 관람 날짜
		// 시간 넣기
		previewDate += " "+ time.substring(0,2) + ":" + time.substring(2);
		
		String title = request.getParameter("movie-name"); // 영화제목

		// 당일 결제일
		LocalDateTime now = LocalDateTime.now();
		String paymentDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")); // 결제일
		
		String seatList = request.getParameter("seat");// 좌석 리스트
		int ticketPrice = Integer.parseInt(request.getParameter("money")); // 금액
		
		// 여러 좌석을 받아온걸 스플릿
		String[] seat = seatList.split(",");
		
		for(int i = 0; i < seat.length; i+=1) {
			getSeatInsert(movieID, ticketPrice, previewDate, seat[i].charAt(1), seat[i].charAt(0),userID);
		}
		// 예매 여러좌석 코드
		String seatIDList = getSeatID(userID);
		
		Ticket vo = new Ticket(0, movieID, userID, numberPeople, previewDate, movieName, title, paymentDate, seatList, seatIDList, ticketPrice);

		TicKetDAO.getInstance().ticketInsert(vo);
		System.out.println("결제 처리완료");
		
		PrintWriter out = response.getWriter();
		
		out.print("true");
		
		return null;
	}
	
	// 좌석 인설트
	private void getSeatInsert(int movieID, int moiveThcd,String previewDate,int seatNumber,char seatGroup,String userID) {
		Seat vo = new Seat();
		vo.setUserID(userID);
		vo.setMovieID(movieID);
		vo.setMovieThcd(movieID);
		vo.setPreviewDate(previewDate);
		vo.setSeatNumber(seatNumber);
		vo.setSeatGroup(seatGroup);
		
		SeatDAO.getInstance().seatInsert(vo);
		System.out.println("좌석 처리완료");
	}
	// 좌석 코드 받아오기
	private String getSeatID(String userID) {
		
		ArrayList<Seat> list = (ArrayList<Seat>)SeatDAO.getInstance().seatCodeList(userID);
		String seatIDList = "";
		for(int i = 0; i < list.size(); i+=1) {
			seatIDList += i == 0? list.get(i).getSeatID() : "," + list.get(i).getSeatID();
		}

		System.out.println("좌석 코드 받아오기 완료");
		return seatIDList;
	}
	
	
}
