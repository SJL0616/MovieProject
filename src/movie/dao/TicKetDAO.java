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

	public ArrayList<UserReservationView> getTicketListById(String user_id) { // 고객 한 명이 예매한 예매리스트를 가져옵니다.
		SqlSession session = sqlSessionFactory.openSession();
		List<UserReservationView> list = session.selectList("getTicketListById", user_id);
		session.commit();
		session.close();
		return (ArrayList<UserReservationView>) list;
	}

	public int cancelTicketByID(int ticketID) { // 관리자가 취소된 티켓을 환불처리한 후 예매내역을 데이터베이스에서 삭제합니다.
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.delete("cancelTicketByID", ticketID);
		session.commit();
		session.close();
		return cnt;
	}

	// 예매 저장
	public int ticketInsert(Ticket vo) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.insert("ticketInsert", vo);
		session.commit();
		session.close();
		return cnt;
	}

	public Ticket getTicketByID(int ticketID) { // 티켓아이디로 티켓객체를 가져옵니다.
		SqlSession session = sqlSessionFactory.openSession();
		Ticket ticket = session.selectOne("getTicketByID", ticketID);
		session.close();
		return ticket;
	}

	public int setTrueToTicket(int ticketID) { // 고객이 예매를 취소하면 해당하는 티켓의 canceled 컬럼을 true로 바꿉니다.
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.update("setTrueToTicket", ticketID);
		System.out.println("예매 취소된 티켓 번호 : " + ticketID);
		System.out.println("cnt : " + cnt);
		session.commit();
		session.close();
		return cnt;
	}

	public ArrayList<UserReservationView> getAllCanceledTickets() { // 관리자가 예매취소된 티켓들을 조회하는 메서드입니다.
		SqlSession session = sqlSessionFactory.openSession();
		List<UserReservationView> list = session.selectList("getAllCanceledTickets");
		session.close();
		return (ArrayList<UserReservationView>) list;
	}
}
