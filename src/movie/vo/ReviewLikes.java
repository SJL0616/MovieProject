package movie.vo;


/*작성자 : 이상준
 * 내용: 좋아요 VO
 * 작성일 2024.03.04
 */
public class ReviewLikes {
	private int likeID;
	private int reviewID;
	private String userID;

	public ReviewLikes() {}

	public ReviewLikes(int likeID, int reviewID, String userID) {
		this.likeID = likeID;
		this.reviewID = reviewID;
		this.userID = userID;
	}

	public int getLikeID() {
		return likeID;
	}

	public void setLikeID(int likeID) {
		this.likeID = likeID;
	}

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
