// 작성자 : 서원우
// 내용 : 유저 dao 입니다.
// 최초 작성일 : 24-02-22
// 최종 수정일 : 24-02-22 (서원우)
package movie.dao;

import java.io.IOException;
import java.io.InputStream;

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
		UserVO member = session.selectOne("getTheUserById");
		return member;
	}
}
