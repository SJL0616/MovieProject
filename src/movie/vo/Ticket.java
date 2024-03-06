package movie.vo;

//작성자 : 김진수
//내용 : Ticket(예매) vo
//최초 작성일 : 24-02-27
//최종 수정일 : 24-03-05 (서원우 : 기본생성자를 추가했습니다. 변수 seat_id 와 ticketCode 를 추가했습니다.)
public class Ticket {
	private int ticketID; // 예매 코드
	private int movieID; // 영화 코드
	private String userID; // 유저 아이디
	private int numberPeople; // 총 예매 인원수
	private String previewDate; // 관람날짜
	private String movieName; // 영화관명
	private String title; // 영화명
	private String paymentDate; // 결제날짜
	private String seatList; // 좌석
	private String seatIDList;// 코드
	private int ticketPrice; // 티켓 총금액

	
	public Ticket() {}
	
	public Ticket(int ticketID, int movieID, String userID, int numberPeople, String previewDate, String movieName,
			String title, String paymentDate, String seatList, String seatIDList, int ticketPrice) {
		this.ticketID = ticketID;
		this.movieID = movieID;
		this.userID = userID;
		this.numberPeople = numberPeople;
		this.previewDate = previewDate;
		this.movieName = movieName;
		this.title = title;
		this.paymentDate = paymentDate;
		this.seatList = seatList;
		this.seatIDList = seatIDList;
		this.ticketPrice = ticketPrice;
	}

	

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
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

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
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

	public String getSeatList() {
		return seatList;
	}

	public void setSeatList(String seatList) {
		this.seatList = seatList;
	}

	public String getSeatIDList() {
		return seatIDList;
	}

	public void setSeatIDList(String seatIDList) {
		this.seatIDList = seatIDList;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "Ticket [ticketCode=" + ticketID + ", movieID=" + movieID + ", userID=" + userID + ", numberPeople="
				+ numberPeople + ", previewDate=" + previewDate + ", movieName=" + movieName + ", title=" + title
				+ ", paymentDate=" + paymentDate + ", seat=" + seatList + ", seat_id=" + seatIDList + ", ticketPrice="
				+ ticketPrice + "]";
	}
}
