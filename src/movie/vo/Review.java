package movie.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
작성자 : 이상준 
내용 : 리뷰 VO
최초 작성일: 2024.02.29
마지막 수정일: 2024.02.29
*/
public class Review {
	private int reviewID; // 리뷰 아이디
	private int movieID; // 영화 코드
	private String userID; // 영화관코드
	private String content; // 내용
	private String regDate; // 등록일
	private String elapsedTime; // 경과 시간(작성일 ~> 출력일 까지 시간)
	private int point; // 점수
	private String viewPoint; // 관람평 요소

	private int like; // 좋아요(공감)수
	private boolean myLike; // 좋아요(공감)수
	private boolean reported;

	public Review() {
	};

	public Review(int movieID, String userID, String content, String regDate, int point, String viewPoint, int like) {

		this.movieID = movieID;
		this.userID = userID;
		this.content = content;
		this.regDate = regDate;
		this.point = point;
		this.viewPoint = viewPoint;
		this.like = like;
	}

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
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

	public String calcElapsedTime() {
		Date now = new Date();
		Date regTime = null;
		String result = "";
		try {
			regTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.regDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (regTime != null) {

			long diffSec = (now.getTime() - regTime.getTime()) / 1000; // 초 차이
			long diffMin = (now.getTime() - regTime.getTime()) / 60000; // 분 차이
			long diffHor = (now.getTime() - regTime.getTime()) / 3600000; // 시 차이
			long diffDays = diffSec / (24 * 60 * 60); // 일자수 차이

			if (diffDays > 0) {
				return diffDays + "일 전";
			}
			if (diffHor > 0) {
				return diffHor + "시간 전";
			}
			if (diffMin > 0) {
				return diffMin + "분 전";
			}
			if (diffSec > 0) {
				return diffSec + "초 전";
			}
		}
		return result;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getElapsedTime() {
		return this.elapsedTime;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
		String result = calcElapsedTime();
		this.elapsedTime = result;
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

	public boolean isMyLike() {
		return myLike;
	}

	public void setMyLike(boolean myLike) {
		this.myLike = myLike;
	}

	public boolean isReported() {
		return reported;
	}

	public void setReported(boolean reported) {
		this.reported = reported;
	}
}
