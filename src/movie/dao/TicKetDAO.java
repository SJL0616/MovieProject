package movie.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TicKetDAO {
	private static TicKetDAO instance;
	private static SqlSessionFactory sqlSessionFactory;
	
	public TicKetDAO(){
		
	}
	
	public static TicKetDAO getInstance() {
		if(instance == null) {
			instance = new TicKetDAO();
		}
		return instance;
	}
	
	static {
		String resource = "movie/dao/mybatis/config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory =
					  new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
