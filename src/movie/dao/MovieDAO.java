package movie.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.protobuf.Type;

import movie.vo.Movie;
import movie.vo.UserVO;

/**
 * 작성자 : 이상준 내용: 영화 DTO 최초 작성일: 2024.02.22 마지막 수정일: 2024.02.22
 */
public class MovieDAO {

	private static MovieDAO instance;
	private final String kobisKey = "64ca2aeb06dc83995c52593480291dc2";
	private final String tmdbKey = "4bbd8a79baa451e755477a68934a9110";

	private MovieDAO() {
		if(!isLtstData()) {
			deleteData();
			saveMovieData();
		}
	}
	
	public static MovieDAO getInstance() {
		if (instance == null) {
			instance = new MovieDAO();
		}
		return instance;
	}
	private static SqlSessionFactory sqlSessionFactory;
	static {
		String resource = "movie/dao/mybatis/config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * public ArrayList<Movie> getTotalList() { return list; }
	 * 
	 * public ArrayList<Movie> getTopFour(){ return new
	 * ArrayList<Movie>(list.subList(0, 4)); }
	 * 
	 * public Movie getOne(int movieId) { for(Movie m : list) { if(m.getMovieID() ==
	 * movieId) { return m; } } return null; }
	 */
	
	
	/**
	 * 작성자 : 이상준 내용: API를 사용해서 일일 박스오피스를 DB에 저장하는 합수 
	 * 파라미터: 없음 
	 * 최초 작성일: 2024.02.22
	 * 마지막 수정일: 2024.02.22
	 */
	public void saveMovieData() {
		
        
        ArrayList<Movie> list = getMovieListByKobis();
		setVisualInfoByTBDB(list);
		addMovieIntoDB(list);
		/*
								 * 
								 * 
								 * for(Movie m : list) {
								 * 
								 * Gson gson = new Gson(); String show = gson.toJson(m);
								 * System.out.println("m "+ show); }
								 */
		 
	}
	
	//rank 탑4 영화리스트를 받아오는 함수
	public ArrayList<Movie> getTopFour(String userID){
		List<Movie> mainList = null;
		SqlSession session = sqlSessionFactory.openSession();
		mainList = session.selectList("selectTopMovies",userID);
		session.close();
		return new ArrayList<Movie>(mainList);
	}
	public ArrayList<Movie> getTotalList(){
		return getTotalList("");
	}
	//전체 영화리스트를 받아오는 함수
	public ArrayList<Movie> getTotalList(String userID){
		List<Movie> mainList = null;
		SqlSession session = sqlSessionFactory.openSession();
		mainList = session.selectList("selectAllMovies", userID);
		session.close();
		return new ArrayList<Movie>(mainList);
	}
	
	//전체 영화리스트를 받아오는 함수
	public ArrayList<Movie> searchMovie(List<String> keywords, String userID){
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("list", keywords);
		map.put("userID", userID);
		
		List<Movie> mainList = null;
		SqlSession session = sqlSessionFactory.openSession();
		mainList = session.selectList("searchByKeywords",map);
		session.close();
		return new ArrayList<Movie>(mainList);
	}
		
	//전체 영화리스트를 받아오는 함수
	public Movie getOneMovie(int id, String userID){
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("movieID", id);
		map.put("userID", userID);
		
		Movie m = null;
		SqlSession session = sqlSessionFactory.openSession();
		m = session.selectOne("selectMovieById", map);
		session.close();
		return m;
	}
	
	// regDate가 오늘인지 확인하는 함수
	private boolean isLtstData() {
		SqlSession session = sqlSessionFactory.openSession();
		int count = session.selectOne("checkRegDate");
		session.close();
		System.out.println(count > 0 ? "DB가 최신입니다." :"DB가 최신이 아닙니다.");
		return count > 0;
	}
	
	// 모든 영화 데이터 삭제 함수
	private boolean deleteData() {
		SqlSession session = sqlSessionFactory.openSession();
		int count = session.delete("deleteAllMovies");
		session.commit();
		session.close();
		return count > 0;
	}
	
	//영화 좋아요 
	public int likeMovie(String movieID, String userID) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("movieID", movieID);
		map.put("userID", userID);
		
		SqlSession session = sqlSessionFactory.openSession();
		int result = session.insert("movieLike",map);
		session.commit();
		session.close();
		return result;
	}
	//영화 좋아요 취소
	public int dislikeMovie(String movieID, String userID) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("movieID", Integer.parseInt(movieID));
		map.put("userID", userID.trim());
		
		SqlSession session = sqlSessionFactory.openSession();
		int result = session.insert("movieDislike",map);
		session.commit();
		session.close();
		return result;
	}
	
	// regDate가 오늘인지 확인하는 로직
	private boolean addMovieIntoDB(ArrayList<Movie> list) {
		SqlSession session = sqlSessionFactory.openSession();
		int sum = 0;
		for(Movie m :list) {
	          Gson gson = new Gson();
	          String show = gson.toJson(m);
	         // System.out.println("m "+ show);
			//System.out.println(m.getTrailerStr());
			int cnt = session.insert("insertMovie", m);
			sum +=cnt;
		}
		session.commit();
		session.close();
		return sum == list.size();
	}


	private ArrayList<Movie> getMovieListByKobis() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");        
		DateTimeFormatter DBformat = DateTimeFormatter.ofPattern("yyyy.MM.dd");        
        String DottedNow = localDate.format(DBformat);    
        String formatedNow = localDate.minusDays(1).format(formatter);      
        
		ArrayList<Movie> list = new ArrayList<Movie>();
		// 인증키 (개인이 받아와야함)
		String date = "&targetDt="+formatedNow;
		// 파싱한 데이터를 저장할 변수
		String result = "";

		try {
			URL url1 = new URL(
					"	http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="
							+ kobisKey + date);
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url1.openStream(), "UTF-8"));

			result = bf.readLine();

			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = (JsonObject) jsonParser.parseString(result);

			JsonObject movieListResult = (JsonObject) jsonObject.get("boxOfficeResult");
			JsonArray movieList = (JsonArray) movieListResult.get("dailyBoxOfficeList");
			Movie m = null;
			for (Object movie : movieList) {
				JsonObject movieData = (JsonObject) movie;

				JsonObject movieInfo = getMovieInfo(movieData.get("movieCd").getAsString());
				/*System.out.println(movieInfo);
				System.out.println("2.영화명(한글): " + movieData.get("movieNm").getAsString());
				System.out.println("4. 개봉일: " + movieData.get("openDt"));
				System.out.println("5. 박스오피스 순위: " + movieData.get("rank"));
				System.out.println("6. 누적관객수 : " + movieData.get("audiAcc"));
				System.out.println("7. 개봉일: " + movieData.get("openDt"));

				System.out.println("3. 영어 제목 :" + movieInfo.get("movieNmEn"));
				System.out.println("8. 상영 시간 :" + movieInfo.get("showTm"));
				System.out.println("9. 상영 형태 :" + movieInfo.get("showTypes"));
				
				System.out.println("10. 감독 :" + movieInfo.get("directors"));
				System.out.println("11. 출연진 :" + movieInfo.get("actors"));
				System.out.println("12. 장르 :" + movieInfo.get("genres"));
				System.out.println("13. 관람 등급 :" + movieInfo.get("audits"));*/    
				
				if(!movieInfo.isJsonNull()) {
					//System.out.println(movieInfo);
					m = new Movie(
							Integer.parseInt(movieData.get("movieCd").getAsString()),
							movieData.get("movieNm").getAsString(),
							movieInfo.get("movieNmEn").getAsString(),
							movieData.get("openDt").getAsString(),
							Integer.parseInt(movieData.get("rank").getAsString()),
							Integer.parseInt(movieData.get("audiAcc").getAsString()),
							null,
							Integer.parseInt(movieInfo.get("showTm").getAsString()),
							(JsonArray)movieInfo.get("showTypes"),
							(JsonArray)movieInfo.get("directors"),
							(JsonArray)movieInfo.get("actors"),
							(JsonArray)movieInfo.get("genres"),
							(JsonArray)movieInfo.get("audits"),
							null, null,
							DottedNow);
					
					list.add(m);
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void setVisualInfoByTBDB( ArrayList<Movie> list){
		for(Movie m : list) {
			
			String[] movieInfo = getMCByTMDB(String.valueOf(m.getTitle()), m.getOpenDate());
			
			try{
				m.setTmdbCd(movieInfo[0]);
				m.setOverview(movieInfo[1]);
				String tmdbMCode = movieInfo[0];
				String imgSrc = null;
				List<String> trailerSrc = null;
				if (tmdbMCode != null) {
					imgSrc = getImageSrc(tmdbMCode);
					trailerSrc = getTrailerSrc(tmdbMCode);
				}
				m.setImage(imgSrc);
				m.setTrailer(trailerSrc);
			}catch(Exception e) {
				System.out.println(m.getTitle());
				e.printStackTrace();
				continue;
			}
			
		}
	}
	
	
	
	
	
	/**
	 * 작성자 : 이상준 내용: TMDB API를 사용해서 TMDB 에서 파라미터로 쓰일 CODE값을 가져오는 함수 파라미터: 영화 이름(한글)
	 * 리턴: String 최초 작성일: 2024.02.22 마지막 수정일: 2024.02.22
	 */
	private String[] getMCByTMDB(String movieName, String openDt) {
		
		if(movieName.equals("듄: 파트2")) {
			movieName = "듄: 파트 2";
		}
		
		String[] movieInfo = null;
		String replacedName = movieName.trim().replaceAll(" ", "+");
		String language = "ko-KR";

		String result = "";
		try {
			URL url = new URL("	https://api.themoviedb.org/3/search/movie?"
					+ "api_key=" + tmdbKey
					+ "&query="+ replacedName 
					+ "&language="+language);
			
			BufferedReader bf;

			// UTF-8 인코딩으로 데이터를 읽어오기 위해 InputStreamReader로 BufferedReader 초기화
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			// 읽어온 데이터를 문자열로 저장
			result = bf.readLine();

			// JSON 데이터 파싱을 위한 JSONParser 객체 생성
			JsonParser jsonParser = new JsonParser();

			// JSON 데이터를 파싱하여 JSONObject로 변환
			JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
			// JSON 데이터 출력
			JsonArray movieListResult = (JsonArray) jsonObject.get("results");
//System.out.println(movieListResult.toString());
			int size = movieListResult.size();
			
			if (size == 0) {
				return null;
			} else if (movieListResult.size() == 1) {
				movieInfo = new String[2];
				JsonObject movieData = (JsonObject) movieListResult.get(0);
				movieInfo[0] = movieData.get("id").getAsString();
				movieInfo[1] = movieData.get("overview").getAsString();
			} else {
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(openDt);
				Date date2 = null;
				movieInfo = new String[2];
				for (Object movie : movieListResult) {
					JsonObject movieData = (JsonObject) movie;
					date2 = new SimpleDateFormat("yyyy-MM-dd")
							.parse(movieData.get("release_date").getAsString());
					if (date1.compareTo(date2) == 0) {
						movieInfo[0] = movieData.get("id").getAsString();
						movieInfo[1] = movieData.get("overview").getAsString();
						break;
					}
				}
				if (movieInfo[0] == null) {
					for (Object movie : movieListResult) {
						JsonObject movieData = (JsonObject) movie;
						if (movieData.get("title").getAsString()
								.equals(movieName)) {
							movieInfo[0] = movieData.get("id").getAsString();
							movieInfo[1] = movieData.get("overview").getAsString();
							break;
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieInfo;
	}

	private String getImageSrc(String movieCode) {
		// 인증키 (개인이 받아와야함)
		//System.out.println("=== getImageSrc ===");
		String imgSrc = null;

		// 파싱한 데이터를 저장할 변수
		String movieId = movieCode;
		// String jpgSrc = "https://image.tmdb.org/t/p/w500/";
		String result = "";
		try {
			URL url = new URL("https://api.themoviedb.org/3/movie/" + movieId + "/images?" 
		                    + "api_key="+ tmdbKey);

			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
			JsonArray images = (JsonArray) jsonObject.get("posters");
			for (Object movie : images) {
				JsonObject movieData = (JsonObject) movie;
				
				JsonElement element = movieData.get("iso_639_1");
				if(element != null && !element.isJsonNull()) {
					if (movieData.get("iso_639_1").getAsString().equals("ko")) {
						imgSrc = movieData.get("file_path").getAsString();
						break;
					}
				}
				
				/*
				 * System.out.println("movieData.get(\"iso_639_1\")");
				 * System.out.println(movieData);
				 * System.out.println(movieData.get("iso_639_1"));
				 * System.out.println(movieData.get("iso_639_1") == null);
				 * System.out.println(movieData.get("iso_639_1").toString());
				 */
				
			}
			if(imgSrc == null) {
				for (Object movie : images) {
					JsonObject movieData = (JsonObject) movie;
					JsonElement element = movieData.get("iso_639_1");
					if(element != null && !element.isJsonNull()) {
						imgSrc = movieData.get("file_path").getAsString();
						break;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgSrc;
	}

	private List<String> getTrailerSrc(String movieCode) {
		// 인증키 (개인이 받아와야함)
		//System.out.println("=== getTrailerSrc ===");
		List<String> videoList = null;
		String language = "ko-KR";
		String movieId = movieCode;
		//String jpgSrc = "https://image.tmdb.org/t/p/w500/";
		
		
		String result = "";
		try {
			URL url = new URL("https://api.themoviedb.org/3/movie/" + movieId + "/videos?" 
					 + "api_key="+ tmdbKey 
					 + "&language="+language);

			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
			JsonArray results = (JsonArray) jsonObject.get("results");

			if (results.size() == 0) {
				url = new URL("https://api.themoviedb.org/3/movie/" + movieId + "/videos?" 
			                + "api_key="+ tmdbKey);

				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				result = bf.readLine();
				jsonObject = (JsonObject) jsonParser.parse(result);
				results = (JsonArray) jsonObject.get("results");

				if (results.size() != 0) {
					//System.out.println(results);
				}

			}
			if (results.size() == 0) {
				return null;
			} else if (results.size() > 0) {
				videoList = new ArrayList<String>();
				for(Object obj : results) {
					JsonObject oneTrailer = (JsonObject) obj;
					videoList.add(oneTrailer.get("key").getAsString());
				}
				/*
				 * for (String m : videoList) {
				 * 
				 * System.out.println(m); }
				 */
				//System.out.println("-----");
				/*
				 * JsonObject oneTrailer = results.get(0).getAsJsonObject(); trailerSrc =
				 * oneTrailer.get("key").getAsString();
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoList;
	}

	JsonObject getMovieInfo(String mvCd) {
		JsonObject jObj = new JsonObject();
		// 파싱한 데이터를 저장할 변수
		String query = "&movieCd=" + mvCd;
		String result = "";
		try {
			URL url = new URL("	http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key="
					+ kobisKey + query);
			// URL을 통해 데이터를 읽어오기 위한 BufferedReader 생성
			BufferedReader bf;

			// UTF-8 인코딩으로 데이터를 읽어오기 위해 InputStreamReader로 BufferedReader 초기화
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			// 읽어온 데이터를 문자열로 저장
			result = bf.readLine();

			// JSON 데이터 파싱을 위한 JSONParser 객체 생성
			JsonParser jsonParser = new JsonParser();

			// JSON 데이터를 파싱하여 JSONObject로 변환
			JsonObject jsonObject = (JsonObject) jsonParser.parse(result);

			// JSON 데이터 출력
			JsonObject movieInfoResult = (JsonObject) jsonObject.get("movieInfoResult");
			JsonObject movieInfo = (JsonObject) movieInfoResult.get("movieInfo");
			if (movieInfo != null) {
				jObj = movieInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jObj;
	}

}
