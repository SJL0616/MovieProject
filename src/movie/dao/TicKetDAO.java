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
import movie.vo.UserReservationView;

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

	public ArrayList<UserReservationView> getTicketListById(String user_id) {
		SqlSession session = sqlSessionFactory.openSession();
		List<UserReservationView> list = session.selectList("getTicketListById", user_id);
		session.commit();
		session.close();
		return (ArrayList<UserReservationView>) list;
	}

	public int cancelTicketByCode(int ticketCode) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.delete("cancelTicketByCode", ticketCode);
		session.commit();
		session.close();
		return cnt;
	}

	public Ticket getTicketByCode(int ticketCode) {
		SqlSession session = sqlSessionFactory.openSession();
		Ticket ticket = session.selectOne("getTicketByCode", ticketCode);
		session.close();
		return ticket;
	}
}
