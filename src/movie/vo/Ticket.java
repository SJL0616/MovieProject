package movie.vo;
//작성자 : 김진수
//내용 : Ticket(예매) vo
//최초 작성일 : 24-02-27
//최종 수정일 : 24-02-27 (김진수)
public class Ticket {
	private int movieID; // 영화 코드
	private String userID; // 유저 아이디
	private int numberPeople; // 총 예매 인원수
	private String previewDate; // 관람날짜
	private String movieName; // 영화관명
	private String title; // 영화명
	private String paymentDate; // 결제날짜
	private String seat; // 좌석
	private int ticketPrice; // 티켓 총금액

	public Ticket(int movieID, String userID, int numberPeople, String previewDate, String movieName, String title,
			String paymentDate, String seat, int ticketPrice) {
		this.movieID = movieID;
		this.userID = userID;
		this.numberPeople = numberPeople;
		this.previewDate = previewDate;
		this.movieName = movieName;
		this.title = title;
		this.paymentDate = paymentDate;
		this.seat = seat;
		this.ticketPrice = ticketPrice;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getNumberPeople() {
		return numberPeople;
	}
	public void setNumberPeople(int numberPeople) {
		this.numberPeople = numberPeople;
	}
	public String getPreviewDate() {
		return previewDate;
	}
	public void setPreviewDate(String previewDate) {
		this.previewDate = previewDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public int getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
}
