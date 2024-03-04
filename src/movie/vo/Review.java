package movie.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/*
작성자 : 이상준 
내용 : 리뷰 VO
최초 작성일: 2024.02.29
마지막 수정일: 2024.02.29
*/
public class Review {
	private int movieID; // 영화 코드
	private String userID; // 영화관코드
	private String content; // 내용
	private String regDate; // 등록일
	private int point; // 점수
	private String viewPoint; // 관람평 요소
	private int like; // 좋아요(공감)수

	public Review() {
	};

	public Review(int movieID, String userID, String content, String regDate, int point,String viewPoint, int like) {

		this.movieID = movieID;
		this.userID = userID;
		this.content = content;
		this.regDate = regDate;
		this.point = point;
		this.viewPoint = viewPoint;
		this.like = like;
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
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}
	public String getElapsedTime() {
       
        Date now = new Date();
        Date regTime = null;
		try {
			regTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.regDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(regTime != null) {
			
			long diffSec = (now.getTime() - regTime.getTime()) / 1000; //초 차이
	        long diffMin = (now.getTime() - regTime.getTime()) / 60000; //분 차이
	        long diffHor = (now.getTime() - regTime.getTime()) / 3600000; //시 차이
	        long diffDays = diffSec / (24*60*60); //일자수 차이
	        
	        if(diffDays > 0) {
	        	 return diffDays + "일 전";
	        }
	        if(diffHor > 0) {
	        	 return diffHor + "시간 전";
	        }
	        if(diffMin > 0) {
	        	 return diffMin + "분 전";
	        }
	        if(diffSec > 0) {
	        	 return diffSec + "초 전";
	        }
		}
		return "";
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getViewPoint() {
		return viewPoint;
	}

	public void setViewPoint(String viewPoint) {
		this.viewPoint = viewPoint;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}
}
