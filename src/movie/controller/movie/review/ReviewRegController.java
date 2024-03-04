package movie.controller.movie.review;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.controller.Controller;
import movie.dao.ReviewDAO;

public class ReviewRegController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println(request);
	        
		String uid = request.getParameter("uid");
		String mid = request.getParameter("mid");
		String point = request.getParameter("point");
		String content = request.getParameter("content");
		String  vPoint = request.getParameter("vPoint");
		System.out.println("아이디 : "+uid +", 점수 : "+point +", 내용 "+ content +", 분야 "+vPoint);
		int success = ReviewDAO.getInstance().insert(uid, mid, point, content, vPoint);
		if(success == 0) {
			response.getWriter().print("fail");
		}
		return null;
	}

}
