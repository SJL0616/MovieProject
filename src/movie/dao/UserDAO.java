
// 작성자 : 서원우
// 내용 : 유저 dao 입니다.
// 최초 작성일 : 24-02-22
// 최종 수정일 : 24-02-26 (서원우)
package movie.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import movie.vo.UserVO;

public class UserDAO {
	private UserDAO() {
	}

	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	private static SqlSessionFactory sqlSessionFactory;
	static {
		String resource = "movie/dao/mybatis/config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UserVO getTheUserById(String id) {
		SqlSession session = sqlSessionFactory.openSession();
		UserVO user = session.selectOne("getTheUserById", id);
		session.close();
		return user;
	}

	public UserVO getTheUserByKakaoId(String kakaoId) {
		SqlSession session = sqlSessionFactory.openSession();
		UserVO user = session.selectOne("getTheUserByKakaoId", kakaoId);
		session.close();
		return user;
	}

	public UserVO getTheUserByNaverId(String naverId) {
		SqlSession session = sqlSessionFactory.openSession();
		UserVO user = session.selectOne("getTheUserByNaverId", naverId);
		session.close();
		return user;
	}

	public int addUserIntoDB(String inputId, String inputPw, String inputEmail, String inputPhone) {
		UserVO user = new UserVO();
		user.setId(inputId);
		user.setPw(inputPw);
		user.setEmail(inputEmail);
		user.setPhone(inputPhone);
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.insert("addUserIntoDB", user);
		session.commit();
		session.close();
		return cnt;
	}

	public int disconnectToKakao(String id) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.update("disconnectToKakao", id);
		session.commit();
		session.close();
		return cnt;
	}

	public int setKakaoIdToTheUser(String userId, String kakaoId) {
		Map<String, String> params = new HashMap<>();
		params.put("id", userId);
		params.put("kakaoId", kakaoId);
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.update("setKakaoIdToTheUser", params);
		session.commit();
		session.close();
		return cnt;
	}

	public int disconnectToNaver(String id) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.update("disconnectToNaver", id);
		session.commit();
		session.close();
		return cnt;
	}

	public int setNaverIdToTheUser(String userId, String naverId) {
		Map<String, String> params = new HashMap<>();
		params.put("id", userId);
		params.put("naverId", naverId);
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.update("setNaverIdToTheUser", params);
		session.commit();
		session.close();
		return cnt;
	}

	public int updateUser(String id, String inputPhone, String inputEmail, String newPw) {
		Map<String, String> params = new HashMap<>();
		params.put("id", id);
		params.put("inputPhone", inputPhone);
		params.put("inputEmail", inputEmail);
		params.put("newPw", newPw);
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.update("updateUser", params);
		session.commit();
		session.close();
		return cnt;
	}
}
