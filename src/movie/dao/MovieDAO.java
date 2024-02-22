package movie.dao;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 작성자 : 이상준 내용: 영화 DTO 최초 작성일: 2024.02.22 마지막 수정일: 2024.02.22
 */
public class MovieDAO {

	private static MovieDAO instance;

	private MovieDAO() {
	}

	public static MovieDAO getInstance() {
		if (instance == null) {
			instance = new MovieDAO();
		}
		return instance;
	}

	/**
	 * 작성자 : 이상준 내용: API를 사용해서 일일 박스오피스를 DB에 저장하는 합수 파라미터: 없음 리턴: 최초 작성일: 2024.02.22
	 * 마지막 수정일: 2024.02.22
	 */
	public void saveMovieData() {

		// 인증키 (개인이 받아와야함)
		String key = "64ca2aeb06dc83995c52593480291dc2";
		String date = "&targetDt=20240219";
		// 파싱한 데이터를 저장할 변수
		String result = "";

		try {
			// 영화 정보를 제공하는 웹 서비스에 접속할 URL 생성
			/*
			 * URL url = new URL(
			 * "http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key="
			 * + key );
			 */
			URL url1 = new URL(
					"	http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="
							+ key + date);
			// URL을 통해 데이터를 읽어오기 위한 BufferedReader 생성
			BufferedReader bf;

			// UTF-8 인코딩으로 데이터를 읽어오기 위해 InputStreamReader로 BufferedReader 초기화
			bf = new BufferedReader(new InputStreamReader(url1.openStream(), "UTF-8"));

			// 읽어온 데이터를 문자열로 저장
			result = bf.readLine();

			// JSON 데이터 파싱을 위한 JSONParser 객체 생성
			JsonParser jsonParser = new JsonParser();

			// JSON 데이터를 파싱하여 JSONObject로 변환
			JsonObject jsonObject = (JsonObject) jsonParser.parseString(result);

			
			JsonObject movieListResult = (JsonObject) jsonObject.get("boxOfficeResult");

			JsonArray movieList = (JsonArray) movieListResult.get("dailyBoxOfficeList");

			for (Object movie : movieList) {
				JsonObject movieData = (JsonObject) movie;
				
				System.out.println("1. 코드 : " + movieData.get("movieCd"));
			}
			
			
			
			for (Object movie : movieList) {
				JsonObject movieData = (JsonObject) movie;
				
				System.out.println("1. 코드 : " + movieData.get("movieCd"));
				
				
                System.out.println("2.영화명(한글): " + movieData.get("movieNm"));
                //System.out.println("3.영화명(영어): " + movieData.get("movieNm"));
                System.out.println("4. 개봉일: " + movieData.get("openDt"));
                System.out.println("5. 박스오피스 순위: " + movieData.get("rank"));
                System.out.println("6. 누적관객수 : " + movieData.get("audiAcc"));
                System.out.println("7. 개봉일: " + movieData.get("openDt"));
        
				String tmdbMCode = getMCByTMDB(movieData.get("movieNm").toString(), movieData.get("openDt").toString());
				String imgSrc = null;
				String trailerSrc = null;
				if (tmdbMCode != null) {
					//imgSrc = getImageSrc(tmdbMCode);
					trailerSrc = getTrailerSrc(tmdbMCode);
				}
				
				System.out.println("TMDB MOVIE CODE :" + tmdbMCode);
				System.out.println("TMDB imgSrc :" + imgSrc);
				System.out.println("TMDB trailerSrc :" + trailerSrc);
				// System.out.println("3.영화명(영어): " + movieData.get("movieNm"));

				/*
				 * System.out.println("5. 박스오피스 순위: " + movieData.get("rank"));
				 * System.out.println("6. 누적관객수 : " + movieData.get("audiAcc"));
				 * System.out.println("7. 개봉일: " + movieData.get("openDt"));
				 */
				// 필요한 다른 데이터도 출력 가능
				System.out.println("--------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 작성자 : 이상준 내용: TMDB API를 사용해서 TMDB 에서 파라미터로 쓰일 CODE값을 가져오는 함수 파라미터: 영화 이름(한글)
	 * 리턴: String 최초 작성일: 2024.02.22 마지막 수정일: 2024.02.22
	 */
	private String getMCByTMDB(String movieName, String openDt) {
		System.out.println("getMCByTMDB name +" + movieName);
		String movieCode = null;
		String replacedName = movieName.trim().replaceAll("\"", "").replaceAll(" ", "+");

		String key = "api_key=4bbd8a79baa451e755477a68934a9110";
		String query = "&query=" + replacedName;
		String language = "&language=ko-KR";

		String result = "";
		try {
			URL url = new URL("	https://api.themoviedb.org/3/search/movie?" + key + query + language);
			System.out.println(url.toString());
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
			System.out.println(jsonObject);
			JsonArray movieListResult = (JsonArray) jsonObject.get("results");

			int size = movieListResult.size();
			if (size == 0) {
				return null;
			} else if (movieListResult.size() == 1) {
				JsonObject movieData = (JsonObject) movieListResult.get(0);
				movieCode = movieData.get("id").toString();
			} else {
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(openDt.replaceAll("\"", ""));
				Date date2 = null;
				for (Object movie : movieListResult) {
					JsonObject movieData = (JsonObject) movie;
					date2 = new SimpleDateFormat("yyyy-MM-dd")
							.parse(movieData.get("release_date").toString().replaceAll("\"", ""));
					if (date1.compareTo(date2) == 0) {
						movieCode = movieData.get("id").toString();
						break;
					}
				}
				if (movieCode == null) {
					for (Object movie : movieListResult) {
						JsonObject movieData = (JsonObject) movie;
						System.out.println(movieData.get("title").toString());
						if (movieData.get("title").toString().replaceAll("\"", "")
								.equals(movieName.replaceAll("\"", ""))) {
							movieCode = movieData.get("id").toString();
							break;
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieCode;
	}

	private String getImageSrc(String movieCode) {
		// 인증키 (개인이 받아와야함)
		System.out.println("=== getImageSrc ===");
		String imgSrc = null;
		String key = "api_key=4bbd8a79baa451e755477a68934a9110";

		// 파싱한 데이터를 저장할 변수
		String movieId = movieCode;
		//String jpgSrc = "https://image.tmdb.org/t/p/w500/";
		String result = "";
		try {
			URL url = new URL("https://api.themoviedb.org/3/movie/" + movieId + "/images?" + key);

			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
			JsonArray images = (JsonArray) jsonObject.get("posters");
			System.out.println(images);
			for (Object movie : images) {
				JsonObject movieData = (JsonObject)movie;
				if(movieData.get("iso_639_1").toString().replaceAll("\"", "").equals("ko")) {
					imgSrc = movieData.get("file_path").toString().replaceAll("\"", "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgSrc;
	}
	
	
	
	private String getTrailerSrc(String movieCode) {
		// 인증키 (개인이 받아와야함)
		System.out.println("=== getTrailerSrc ===");
		String trailerSrc = null;
		String key = "api_key=4bbd8a79baa451e755477a68934a9110";
		String language = "&language=ko-KR";

		// 파싱한 데이터를 저장할 변수
		String movieId = movieCode;
		String jpgSrc = "https://image.tmdb.org/t/p/w500/";
		String result = "";
		try {
			URL url = new URL("https://api.themoviedb.org/3/movie/" + movieId + "/videos?" + key + language);

			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
			JsonArray results = (JsonArray) jsonObject.get("results");
			
			System.out.println(results.size());
			if(results.size() == 0 ) {
				url = new URL("https://api.themoviedb.org/3/movie/" + movieId + "/videos?" + key);

				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				result = bf.readLine();
				jsonObject = (JsonObject) jsonParser.parse(result);
				results = (JsonArray) jsonObject.get("results");
				
				System.out.println(results.size());
				if(results.size() != 0 ) {
					System.out.println(results);
				}
				
			}
			if(results.size() == 0 ) {
				return null;
			}else if (results.size() > 0) {
				JsonObject oneTrailer = (JsonObject)results.get(0);
				trailerSrc = oneTrailer.get("key").toString().replaceAll("\"", "");
			}
			System.out.println(results);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trailerSrc;
	}
	
	
	JsonObject getMovieInfo(String mvCd) {
		JsonObject jobj = null;
		/*
		 * URL url2 = new
		 * URL("	http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key="
		 * + key+mvCd ); // URL을 통해 데이터를 읽어오기 위한 BufferedReader 생성 BufferedReader bf;
		 * 
		 * // UTF-8 인코딩으로 데이터를 읽어오기 위해 InputStreamReader로 BufferedReader 초기화 bf = new
		 * BufferedReader(new InputStreamReader(url2.openStream(), "UTF-8"));
		 * 
		 * // 읽어온 데이터를 문자열로 저장 result = bf.readLine();
		 * 
		 * // JSON 데이터 파싱을 위한 JSONParser 객체 생성 JSONParser jsonParser = new JSONParser();
		 * 
		 * // JSON 데이터를 파싱하여 JSONObject로 변환 JSONObject jsonObject = (JSONObject)
		 * jsonParser.parse(result);
		 * 
		 * // JSON 데이터 출력 System.out.println(jsonObject);
		 */
		return jobj;
	}

}
