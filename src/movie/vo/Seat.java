package movie.vo;

public class Seat {
	private int seatID; // 좌석코드
	private int movieThcd; // 영화관코드
	private int movieID; // 영화코드
	private int seatNumber; // 좌석 열
	private int seatGroup; // 좌석 행
	
	
	public int getMovieID() {
		return movieID;
	}
	public int getSeatID() {
		return seatID;
	}
	public int getMovieThcd() {
		return movieThcd;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public int getSeatGroup() {
		return seatGroup;
	}
}
