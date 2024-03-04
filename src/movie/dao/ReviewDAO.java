package movie.dao;

import java.io.IOException;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import movie.vo.Review;

/**
 * 작성자 : 이상준 내용: 영화 리뷰 DTO 최초 작성일: 2024.02.29 마지막 수정일: 2024.02.29
 */
public class ReviewDAO {

	private static ReviewDAO instance;
	
	private ReviewDAO() {
	}
	
	public static ReviewDAO getInstance() {
		if (instance == null) {
			instance = new ReviewDAO();
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
	
	
	//영화의 리뷰 리스트 전체를 받아오는 함수
	public ArrayList<Review> getTotalList(int id, int start, int pageSize){
		Map<String,Integer> params = new HashMap<>();
		params.put("movieID", id);
		params.put("start", start);
		params.put("pageSize", pageSize);
		
		List<Review> mainList = null;
		SqlSession session = sqlSessionFactory.openSession();
		mainList = session.selectList("selectReviewById", params);
		session.close();
		return new ArrayList<Review>(mainList);
	}
	//영화의 리뷰 리스트 전체의 갯수 받아오는 함수
	public int getCount(int id){
		
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.selectOne("selectCountById", id);
		session.close();
		return cnt;
	}
	//영화의 리뷰 리스트 전체의 갯수 받아오는 함수
	public int insert(String uid, String mid , String point, String content,String  vPoint ){
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatedNow = formatter.format(date);  
		Review r = new Review(Integer.parseInt(mid), uid, content, formatedNow, Integer.parseInt(point), vPoint, 0);
		
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.insert("insertReview", r);
		session.commit();
		session.close();
		return cnt;
	}

}
