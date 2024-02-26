package movie.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movie.controller.Controller;
import movie.dao.UserDAO;
import movie.vo.UserVO;

public class UserIdValidAjax implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String inputId = request.getParameter("id");
		UserVO user = UserDAO.getInstance().getTheUserById(inputId);
		String passData = "notValid";
		if (user == null)
			passData = "valid";
		response.getWriter().print(passData);
		return null;
	}
}
