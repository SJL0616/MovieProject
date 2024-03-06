package movie.controller.movie.review;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.ReviewDAO;

public class ReviewRegController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println(request);
	        
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("log");
		if(userID == null) {
			response.getWriter().print("userIDIsNull");
			response.getWriter().close();
			return null;
		}
 		String mid = request.getParameter("mid");
		String point = request.getParameter("point");
		String content = request.getParameter("content");
		String  vPoint = request.getParameter("vPoint");
		System.out.println("아이디 : "+userID +", 점수 : "+point +", 내용 "+ content +", 분야 "+vPoint);
		int success = ReviewDAO.getInstance().insert(userID, mid, point, content, vPoint);
		if(success == 0) {
			response.getWriter().print("fail");
		}else {
			response.getWriter().print("success");
		}
		response.getWriter().close();
		return null;
	}

}
