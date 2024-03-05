package movie.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/*작성자 : 이상준
 * 내용: 좋아요 DAO
 * 작성일 2024.03.04
 */

import movie.vo.ReviewLikes;
public class ReviewLikesDAO {
	private static ReviewLikesDAO instance;

	private ReviewLikesDAO() {
	}

	public static ReviewLikesDAO getInstance() {
		if (instance == null) {
			instance = new ReviewLikesDAO();
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
	
	public int insertReviewLike(int reviewID, String userID){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reviewID", reviewID);
		map.put("userID", userID);
		
		SqlSession session = sqlSessionFactory.openSession();
		int result = session.insert("insertReviewLike", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	/*
	 * public ArrayList<ReviewLikes> getLikesByUserID(int start, int end, String
	 * user_ID){ Map<String, Object> map = new HashMap<String, Object>();
	 * map.put("start", start); map.put("end", end); map.put("userID", user_ID);
	 * 
	 * SqlSession session = sqlSessionFactory.openSession(); List<ReviewLikes> likes
	 * = session.selectList("getLikesByUserID", map); session.close();
	 * 
	 * return new ArrayList<ReviewLikes>(likes); }
	 */
	
	
	
	
	
	
	
	

}
