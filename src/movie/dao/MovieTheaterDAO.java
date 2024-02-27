package movie.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import movie.vo.MovieTheater;

public class MovieTheaterDAO {
	
	private static MovieTheaterDAO instance;
	private static SqlSessionFactory sqlSessionFactory;
	
	public MovieTheaterDAO() {
	}
	
	public static MovieTheaterDAO getInstance() {
		if(instance == null) {
			instance = new MovieTheaterDAO();
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
	// movieTheaterList 뽑기
	public List<MovieTheater> movieTheaterList(){
		SqlSession session = sqlSessionFactory.openSession();
		List<MovieTheater> list = session.selectList("movieTheaterList");
		session.close();
		return list;
	}
	
}
