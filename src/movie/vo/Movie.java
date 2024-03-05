package movie.vo;

import java.text.DecimalFormat;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 작성자 : 이상준
 내용: 영화 VO
 최초 작성일: 2024.02.22
 마지막 수정일: 2024.02.22
*/
public class Movie {

	private int movieID;
	private String tmdbCd;
	private String title;
	private String titleEn;
	private String openDate;
	private int rank;
	private int audiCum;
	private String overview;
	private int showTime;
	private String showTypes;
	@Override
	public String toString() {
		return "Movie [movieID=" + movieID + ", tmdbCd=" + tmdbCd + ", title=" + title + ", titleEn=" + titleEn
				+ ", openDate=" + openDate + ", rank=" + rank + ", audiCum=" + audiCum + ", overview=" + overview
				+ ", showTime=" + showTime + ", showTypes=" + showTypes + ", director=" + director + ", actors="
				+ actors + ", genre=" + genre + ", watchGrade=" + watchGrade + ", image=" + image + ", trailer="
				+ trailer + ", trailerStr=" + trailerStr + ", regDate=" + regDate + "]";
	}

	private String director;
	private String actors;
	private String genre;
	private String watchGrade;
	private String image;
	private List<String> trailer;
	private String trailerStr;
	private String regDate;
	

	public Movie() {}
	
	public Movie(int movieID, String title, String titleEn, String openDate, int rank, int audiCum, String overview,
			int showTime, String showTypes, String director, String actors, String genre, String watchGrade,
			String image, List<String> trailer,String regDate) {
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
		this.regDate = regDate;
	}
	
	
	public Movie(int movieID, String title, String titleEn, String openDate, int rank, int audiCum, String overview,
			int showTime, JsonArray showTypes, JsonArray director, JsonArray actors, JsonArray genre, JsonArray watchGrade,
			String image, List<String> trailer,String regDate) {
		this.movieID = movieID;
		this.title = title;
		this.titleEn = titleEn;
		this.openDate = openDate;
		this.rank = rank;
		this.audiCum = audiCum;
		this.overview = overview;
		this.showTime = showTime;
		this.showTypes =  getSTFromJArr(showTypes);
		this.director = getDirFromJArr(director);
		this.actors = getActorsFormJArr(actors);
		this.genre = genre.get(0).getAsJsonObject().get("genreNm").getAsString();
		this.watchGrade = watchGrade.get(0).getAsJsonObject().get("watchGradeNm").getAsString();
		this.image = image;
		this.trailer = trailer;
		this.trailerStr = toStringByGson(trailer);
		this.regDate = regDate;
	}
	
	public String toStringByGson(List<String> str) {
		Gson gson = new Gson();
		return gson.toJson(str);
	}
	public List<String> toListByGson(String str) {
		Gson gson = new Gson();
		return gson.fromJson(str, new TypeToken<List<String>>(){}.getType());
	}
	public String getDirFromJArr(JsonArray jsonArray) {
		int length = jsonArray.size();
		String name = "";
		if(length >0) {
			name = jsonArray.get(0).getAsJsonObject().get("peopleNm").getAsString();
		}
		return name;
	}
	
	public String getSTFromJArr(JsonArray jsonArray) {
		int length = jsonArray.size();
		String showType = "";
		if(length >0) {
			JsonObject jobj = (JsonObject)jsonArray.get(0);
			showType = jobj.get("showTypeGroupNm").getAsString()
					   +", "
					   +jobj.get("showTypeNm").getAsString();
		}
		return showType;
	}
	
	public String getActorsFormJArr(JsonArray jsonArray) {
		int length = jsonArray.size();
		String actors = "";
		if(length > 0) {
			int i = 0;
			for(Object obj : jsonArray) {
				JsonObject oneActor = (JsonObject) obj;
				if(!oneActor.isJsonNull()) {
					actors += oneActor.get("peopleNm").getAsString();
				}
				i++;
				if(i != length) {
					actors +=", ";
				}
				
			}
		}
		return actors;
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
	public String getOpenDate() {
		return openDate;
	}
	public String getOpenDotDate() {
		return openDate.replaceAll("-", ".");
	}
	public void setOpenDate(String openDate) {
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
	public String getAudiCumWithCom() {
		DecimalFormat decFormat = new DecimalFormat("###,###");
		String str = decFormat.format(this.audiCum);
		return str;
	}
	public String getOverview() {
		return overview;
	}
	public String getOverviewInLine() {
		String str= overview.replace(".", ". <br>").replace("?", "? <br>");
		return str;
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
	public List<String> getTrailer() {
		return trailer;
	}
	public void setTrailer(List<String> trailer) {
		this.trailer = trailer;
		this.trailerStr = toStringByGson(trailer);
	}
	public String getTmdbCd() {
		return tmdbCd;
	}

	public void setTmdbCd(String tmdbCd) {
		this.tmdbCd = tmdbCd;
	}
	
	public String getTrailerStr() {
		return trailerStr;
	}

	public void setTrailerStr(String trailerStr) {
		this.trailerStr = trailerStr;
		this.trailer = toListByGson(trailerStr);
	}
	

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	
}
