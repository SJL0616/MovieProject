package movie.vo;

public class Movie {

	private int movieID;
	private String title;
	private String titleEn;
	private int openDate;
	private int rank;
	private int audiCum;
	private String overview;
	private int showTime;
	private String showTypes;
	private String director;
	private String actors;
	private String genre;
	private String watchGrade;
	private String image;
	private String trailer;
	
	public Movie() {}
	
	public Movie(int movieID, String title, String titleEn, int openDate, int rank, int audiCum, String overview,
			int showTime, String showTypes, String director, String actors, String genre, String watchGrade,
			String image, String trailer) {
		this.movieID = movieID;
		this.title = title;
		this.titleEn = titleEn;
		this.openDate = openDate;
		this.rank = rank;
		this.audiCum = audiCum;
		this.overview = overview;
		this.showTime = showTime;
		this.showTypes = showTypes;
		this.director = director;
		this.actors = actors;
		this.genre = genre;
		this.watchGrade = watchGrade;
		this.image = image;
		this.trailer = trailer;
	}
	
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleEn() {
		return titleEn;
	}
	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}
	public int getOpenDate() {
		return openDate;
	}
	public void setOpenDate(int openDate) {
		this.openDate = openDate;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getAudiCum() {
		return audiCum;
	}
	public void setAudiCum(int audiCum) {
		this.audiCum = audiCum;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public int getShowTime() {
		return showTime;
	}
	public void setShowTime(int showTime) {
		this.showTime = showTime;
	}
	public String getShowTypes() {
		return showTypes;
	}
	public void setShowTypes(String showTypes) {
		this.showTypes = showTypes;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getWatchGrade() {
		return watchGrade;
	}
	public void setWatchGrade(String watchGrade) {
		this.watchGrade = watchGrade;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	
	
}
