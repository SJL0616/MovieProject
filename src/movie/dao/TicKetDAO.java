package movie.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import movie.vo.Ticket;

public class TicKetDAO {
	private static TicKetDAO instance;
	private static SqlSessionFactory sqlSessionFactory;

	private TicKetDAO() {
	}

	public static TicKetDAO getInstance() {
		if (instance == null) {
			instance = new TicKetDAO();
		}
		return instance;
	}

	static {
		String resource = "movie/dao/mybatis/config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Ticket> getTicketListById(String user_id) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Ticket> list = session.selectList("getTicketListById", user_id);
		session.commit();
		session.close();
		return (ArrayList<Ticket>) list;
	}
}
