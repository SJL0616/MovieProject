package movie.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import movie.vo.Seat;

public class SeatDAO {
	private static SeatDAO instance;
	private static SqlSessionFactory sqlSessionFactory;
	
	public SeatDAO(){
		
	}
	
	public static SeatDAO getInstance() {
		if(instance == null) {
			instance = new SeatDAO();
		}
		return instance;
	}
	
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
	// 해당하는 영화관 채워진 좌석 찾기
	public List<Seat> seatCheckList(int movieThcd){
		SqlSession session = sqlSessionFactory.openSession();
		List<Seat> list = session.selectList("seatCheckList",movieThcd);
		session.close();
		return list;
	}
	
}
