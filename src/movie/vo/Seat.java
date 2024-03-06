package movie.vo;

import java.util.Objects;

public class Seat {
	private int seatID; // 좌석코드
	private String userID; // 유저 아이디
	private int movieThcd; // 영화관코드
	private int movieID; // 영화코드
	private String previewDate; // 관람 날짜
	private int seatNumber; // 좌석 열
	private char seatGroup; // 좌석 행
	
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
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
	public char getSeatGroup() {
		return seatGroup;
	}
	public String getPreviewDate() {
		return previewDate;
	}
	public void setPreviewDate(String previewDate) {
		this.previewDate = previewDate;
	}
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	public void setMovieThcd(int movieThcd) {
		this.movieThcd = movieThcd;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public void setSeatGroup(char seatGroup) {
		this.seatGroup = seatGroup;
	}
	
}
