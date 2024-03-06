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
	public List<Seat> seatCheckList(Seat vo){
		SqlSession session = sqlSessionFactory.openSession();
		List<Seat> list = session.selectList("seatCheckList",vo);
		session.close();
		return list;
	}
	// 구입한 좌석 리스트에 담기
	public int seatInsert(Seat vo) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.insert("seatInsert",vo);
		session.commit();
		session.close();
		return cnt;
	}
	// user에 맞는 좌석코드리스트 가지고오기
	public List<Seat> seatCodeList(String id){
		SqlSession session = sqlSessionFactory.openSession();
		List<Seat> list = session.selectList("seatCodeList",id);
		session.close();
		return list;
	}
	
}
