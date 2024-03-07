package movie.vo;

public class UserReservationView {
	private String image; // 영화 이미지
	private int ticketID; // 예매 코드
	private int movieID; // 영화 코드
	private String userID; // 유저 아이디
	private int numberPeople; // 총 예매 인원수
	private String previewDate; // 관람날짜
	private String movieName; // 영화관명
	private String title; // 영화명
	private String paymentDate; // 결제날짜
	private String seatList; // 좌석
	private String seatIDList;
	private int ticketPrice; // 티켓 총금액
	private boolean canceled;

	@Override
	public String toString() {
		return "UserReservationView [image=" + image + ", ticketID=" + ticketID + ", movieID=" + movieID + ", userID="
				+ userID + ", numberPeople=" + numberPeople + ", previewDate=" + previewDate + ", movieName="
				+ movieName + ", title=" + title + ", paymentDate=" + paymentDate + ", seatList=" + seatList
				+ ", seatIDList=" + seatIDList + ", ticketPrice=" + ticketPrice + ", canceled=" + canceled + "]";
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
}
