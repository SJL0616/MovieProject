package movie.vo;

//작성자 : 김진수
//내용 : movieTheater(영화관) vo
//최초 작성일 : 24-02-27
//최종 수정일 : 24-02-27 (김진수)

public class MovieTheater {
	private int movieThcd; // 영화관코드
	private String movieName; // 영화관명
	private String movieAddress; // 영화관주소
	private double locationX;	// 위도
	private double locationY; // 경도
	
	
	public MovieTheater(int movieThcd, String movieName, String movieAddress, double locationX, double locationY) {
		super();
		this.movieThcd = movieThcd;
		this.movieName = movieName;
		this.movieAddress = movieAddress;
		this.locationX = locationX;
		this.locationY = locationY;
	}
	public int getMovieThcd() {
		return movieThcd;
	}
	public String getMovieName() {
		return movieName;
	}
	public String getMovieAddress() {
		return movieAddress;
	}
	public double getLocationX() {
		return locationX;
	}
	public double getLocationY() {
		return locationY;
	}
	@Override
	public String toString() {
		return "[movieThcd=" + movieThcd + ", movieName=" + movieName + ", movieAddress=" + movieAddress
				+ ", locationX=" + locationX + ", locationY=" + locationY + "]";
	}
	
	
}
