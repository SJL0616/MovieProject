package movie.dao;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
작성자 : 이상준
내용: 영화 DTO
최초 작성일: 2024.02.22
마지막 수정일: 2024.02.22
*/
public class MovieDAO {

	private static MovieDAO instance;
	
	private MovieDAO() {}
	
	public static MovieDAO getInstance() {
		if(instance == null) {
			instance = new MovieDAO();
		}
		return instance;
	}
	
	/**
	작성자 : 이상준
	내용: API를 사용해서 일일 박스오피스를 DB에 저장하는 합수
	파라미터: 없음
	리턴:
	최초 작성일: 2024.02.22
	마지막 수정일: 2024.02.22
	*/
	public void saveMovieData() {
		
		 // 인증키 (개인이 받아와야함)
        String key = "64ca2aeb06dc83995c52593480291dc2";
        String date = "&targetDt=20240219";
        // 파싱한 데이터를 저장할 변수
        String result = "";
        
        try {
            // 영화 정보를 제공하는 웹 서비스에 접속할 URL 생성
            /*URL url = new URL("http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key="
                    + key );*/
            URL url = new URL("	http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="
                    + key+date );
            // URL을 통해 데이터를 읽어오기 위한 BufferedReader 생성
            BufferedReader bf;
            
            // UTF-8 인코딩으로 데이터를 읽어오기 위해 InputStreamReader로 BufferedReader 초기화
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            
            // 읽어온 데이터를 문자열로 저장
            result = bf.readLine();
            
            // JSON 데이터 파싱을 위한 JSONParser 객체 생성
            JsonParser jsonParser = new JsonParser();
            
            // JSON 데이터를 파싱하여 JSONObject로 변환
            JsonObject jsonObject = (JsonObject) jsonParser.parseString(result);
            
            // JSON 데이터 출력
            //System.out.println(jsonObject);
            
            // 주석 처리된 부분은 하드코딩된 JSON 데이터입니다. 실제로는 API 또는 외부에서 데이터를 가져와야 합니다.
            
            // JSON 데이터 문자열
            // String jsonData = "{\"movieListResult\":{\"totCnt\":98893,\"source\":\"영화진흥위원회\",\"movieList\":[{...}]}}";
            
            // JSON 데이터 파싱
            
            // JSON 데이터에서 "movieListResult" 필드를 추출
            //JSONObject movieListResult = (JSONObject) jsonObject.get("movieListResult");
         
            
            // "movieListResult" 안에 있는 "movieList" 필드를 추출 (영화 목록)
           // JSONArray movieList = (JSONArray) movieListResult.get("movieList");
            
            
            // 영화 목록을 순회하면서 데이터 출력
           /* for (Object movie : movieList) {
                JSONObject movieData = (JSONObject) movie;
                System.out.println("영화명(한글): " + movieData.get("movieNm"));
                System.out.println("영화명(영문): " + movieData.get("movieNmEn"));
                System.out.println("제작국가: " + movieData.get("nationAlt"));
                System.out.println("영화 장르: " + movieData.get("repGenreNm"));
                System.out.println("제작 연도: " + movieData.get("prdtYear"));
                System.out.println("영화 타입: " + movieData.get("typeNm"));
                // 필요한 다른 데이터도 출력 가능
                System.out.println("--------");
            }*/
            
            JsonObject movieListResult = (JsonObject) jsonObject.get("boxOfficeResult");
            
            JsonArray movieList = (JsonArray) movieListResult.get("dailyBoxOfficeList");
            
            for (Object movie : movieList) {
                JsonObject movieData = (JsonObject) movie;
                System.out.println("1. 코드 : " + movieData.get("movieCd"));
                System.out.println("2.영화명(한글): " + movieData.get("movieNm"));
                getMCByTMDB(movieData.get("movieNm").toString());
                //System.out.println("3.영화명(영어): " + movieData.get("movieNm"));
                System.out.println("4. 개봉일: " + movieData.get("openDt"));
                /*System.out.println("5. 박스오피스 순위: " + movieData.get("rank"));
                System.out.println("6. 누적관객수 : " + movieData.get("audiAcc"));
                System.out.println("7. 개봉일: " + movieData.get("openDt"));*/
                // 필요한 다른 데이터도 출력 가능
                System.out.println("--------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	/**
	작성자 : 이상준
	내용: TMDB API를 사용해서 TMDB 에서 파라미터로 쓰일 CODE값을 가져오는 함수
	파라미터: 영화 이름(한글)
	리턴: String
	최초 작성일: 2024.02.22
	마지막 수정일: 2024.02.22
	*/
	private String getMCByTMDB(String movieName) {
		System.out.println("getMCByTMDB name +"+movieName);
		String movieCode = null;
		String replacedName = movieName.trim().replaceAll(" ", "+");
		
		 // 인증키 (개인이 받아와야함)
        String key = "64ca2aeb06dc83995c52593480291dc2";
        String query = "&query="+replacedName;
        String language = "&language=ko-KR";
        // 파싱한 데이터를 저장할 변수
        String result = "";
        try {
            // 영화 정보를 제공하는 웹 서비스에 접속할 URL 생성
            URL url = new URL("	https://api.themoviedb.org/3/search/movie?api_key=4bbd8a79baa451e755477a68934a9110"
                    +query+language);
            System.out.println(url.toString());
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
            System.out.println(jsonObject);
            JsonArray movieListResult =  (JsonArray) jsonObject.get("results");
          
            System.out.println(movieListResult.toString());
            int size = movieListResult.size();
            if(size == 0) {
            	return null;
            } else if(movieListResult.size() == 1) {
            	 JsonObject movieData = (JsonObject) movieListResult.get(0);
            	System.out.println( movieData.get("poster_path"));
            	return  movieData.get("poster_path").toString();
            }else {
            	 for (Object movie : movieListResult) {
                     JsonObject movieData = (JsonObject) movie;
                     System.out.println( movieData.get("title"));
                     System.out.println( movieData.get("poster_path"));
                     System.out.println( movieData.get("release_date"));
                     System.out.println( movieData.get("id"));
                     System.out.println( movieData.get("key"));
     				/*
     				 * System.out.println("영화명(영문): " + movieData.get("movieNmEn"));
     				 * System.out.println("제작국가: " + movieData.get("nationAlt"));
     				 * System.out.println("영화 장르: " + movieData.get("repGenreNm"));
     				 * System.out.println("제작 연도: " + movieData.get("prdtYear"));
     				 * System.out.println("영화 타입: " + movieData.get("typeNm"));
     				 */
                     // 필요한 다른 데이터도 출력 가능
                     System.out.println("--------");
                 }
            }
           
            // 주석 처리된 부분은 하드코딩된 JSON 데이터입니다. 실제로는 API 또는 외부에서 데이터를 가져와야 합니다.
            
            // JSON 데이터 문자열
            // String jsonData = "{\"movieListResult\":{\"totCnt\":98893,\"source\":\"영화진흥위원회\",\"movieList\":[{...}]}}";
            
            // JSON 데이터 파싱
            
            // JSON 데이터에서 "movieListResult" 필드를 추출
            //JSONObject movieListResult = (JSONObject) jsonObject.get("movieListResult");
         
            
            // "movieListResult" 안에 있는 "movieList" 필드를 추출 (영화 목록)
           // JSONArray movieList = (JSONArray) movieListResult.get("movieList");
            
            
            // 영화 목록을 순회하면서 데이터 출력
           /* for (Object movie : movieList) {
                JSONObject movieData = (JSONObject) movie;
                System.out.println("영화명(한글): " + movieData.get("movieNm"));
                System.out.println("영화명(영문): " + movieData.get("movieNmEn"));
                System.out.println("제작국가: " + movieData.get("nationAlt"));
                System.out.println("영화 장르: " + movieData.get("repGenreNm"));
                System.out.println("제작 연도: " + movieData.get("prdtYear"));
                System.out.println("영화 타입: " + movieData.get("typeNm"));
                // 필요한 다른 데이터도 출력 가능
                System.out.println("--------");
            }
            
            JSONObject movieListResult = (JSONObject) jsonObject.get("boxOfficeResult");
            
            JSONArray movieList = (JSONArray) movieListResult.get("dailyBoxOfficeList");
            
            for (Object movie : movieList) {
                JSONObject movieData = (JSONObject) movie;
                System.out.println("영화명(한글): " + movieData.get("movieNm"));
                System.out.println("순위: " + movieData.get("rank"));
                System.out.println("코드 : " + movieData.get("movieCd"));
                System.out.println("개봉일: " + movieData.get("openDt"));
                System.out.println("제작 연도: " + movieData.get("prdtYear"));
                System.out.println("영화 타입: " + movieData.get("typeNm"));
                // 필요한 다른 데이터도 출력 가능
                System.out.println("--------");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
		return movieCode;
	}
	
	private String getImageSrc(String movieCode) {
		 // 인증키 (개인이 받아와야함)
       String key = "64ca2aeb06dc83995c52593480291dc2";
       String date = "&query=웡카&language=ko-KR";
       // 파싱한 데이터를 저장할 변수
       String result = "";
       String jpgSrc ="https://image.tmdb.org/t/p/w500/";
       String movieId = "787699";
       try {
           // 영화 정보를 제공하는 웹 서비스에 접속할 URL 생성
           /*URL url = new URL("http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key="
                   + key );
           URL url = new URL("	https://api.themoviedb.org/3/search/movie?api_key=4bbd8a79baa451e755477a68934a9110"
                   +date);*/
           
       	
       	// 트레일러 검색 : 반환된 값들 중 key 값을  https://www.youtube.com/watch?v=  뒤에 붙여서 재생.
           //URL url = new URL("https://api.themoviedb.org/3/movie/"+movieId+"/images?api_key=4bbd8a79baa451e755477a68934a9110");	
           URL url = new URL("https://api.themoviedb.org/3/movie/"+movieId+"/videos?api_key=4bbd8a79baa451e755477a68934a9110&language=ko-KR");	
           	
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
           System.out.println(jsonObject);
           JsonArray movieListResult =  (JsonArray) jsonObject.get("results");
         
           System.out.println(movieListResult.toString());
           for (Object movie : movieListResult) {
               JsonObject movieData = (JsonObject) movie;
               System.out.println( movieData.get("title"));
               System.out.println( movieData.get("poster_path"));
               System.out.println( movieData.get("poster_path"));
               System.out.println( movieData.get("id"));
               System.out.println( movieData.get("key"));
				/*
				 * System.out.println("영화명(영문): " + movieData.get("movieNmEn"));
				 * System.out.println("제작국가: " + movieData.get("nationAlt"));
				 * System.out.println("영화 장르: " + movieData.get("repGenreNm"));
				 * System.out.println("제작 연도: " + movieData.get("prdtYear"));
				 * System.out.println("영화 타입: " + movieData.get("typeNm"));
				 */
               // 필요한 다른 데이터도 출력 가능
               System.out.println("--------");
           }
           // 주석 처리된 부분은 하드코딩된 JSON 데이터입니다. 실제로는 API 또는 외부에서 데이터를 가져와야 합니다.
           
           // JSON 데이터 문자열
           // String jsonData = "{\"movieListResult\":{\"totCnt\":98893,\"source\":\"영화진흥위원회\",\"movieList\":[{...}]}}";
           
           // JSON 데이터 파싱
           
           // JSON 데이터에서 "movieListResult" 필드를 추출
           //JSONObject movieListResult = (JSONObject) jsonObject.get("movieListResult");
        
           
           // "movieListResult" 안에 있는 "movieList" 필드를 추출 (영화 목록)
          // JSONArray movieList = (JSONArray) movieListResult.get("movieList");
           
           
           // 영화 목록을 순회하면서 데이터 출력
          /* for (Object movie : movieList) {
               JSONObject movieData = (JSONObject) movie;
               System.out.println("영화명(한글): " + movieData.get("movieNm"));
               System.out.println("영화명(영문): " + movieData.get("movieNmEn"));
               System.out.println("제작국가: " + movieData.get("nationAlt"));
               System.out.println("영화 장르: " + movieData.get("repGenreNm"));
               System.out.println("제작 연도: " + movieData.get("prdtYear"));
               System.out.println("영화 타입: " + movieData.get("typeNm"));
               // 필요한 다른 데이터도 출력 가능
               System.out.println("--------");
           }
           
           JSONObject movieListResult = (JSONObject) jsonObject.get("boxOfficeResult");
           
           JSONArray movieList = (JSONArray) movieListResult.get("dailyBoxOfficeList");
           
           for (Object movie : movieList) {
               JSONObject movieData = (JSONObject) movie;
               System.out.println("영화명(한글): " + movieData.get("movieNm"));
               System.out.println("순위: " + movieData.get("rank"));
               System.out.println("코드 : " + movieData.get("movieCd"));
               System.out.println("개봉일: " + movieData.get("openDt"));
               System.out.println("제작 연도: " + movieData.get("prdtYear"));
               System.out.println("영화 타입: " + movieData.get("typeNm"));
               // 필요한 다른 데이터도 출력 가능
               System.out.println("--------");
           }*/
       } catch (Exception e) {
           e.printStackTrace();
       }
		return movieCode;
	}
	
	
}
