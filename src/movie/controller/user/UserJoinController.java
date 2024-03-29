package movie.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.controller.Controller;
import movie.dao.UserDAO;

public class UserJoinController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String inputId = request.getParameter("id");
		if (inputId == null || inputId.equals(""))
			return "userJoin";
		String inputPw = request.getParameter("pw");
		String inputEmail = request.getParameter("email");
		String inputPhone = request.getParameter("phone");
		UserDAO.getInstance().addUserIntoDB(inputId, inputPw, inputEmail, inputPhone);
		String ctx = request.getContextPath();
		return "redirect:" + ctx + "/index.jsp";
	}
}
