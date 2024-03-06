package movie.controller.movie.review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import movie.controller.Controller;
import movie.dao.ReviewDAO;
import movie.vo.PageContext;
import movie.vo.Review;

public class ReviewListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String id = request.getParameter("id").trim();
		String order = request.getParameter("order");
		int sqlOrder = order.equals("공감순") ? 1 :  order.equals("평점순") ? 2 : 0;
		System.out.println(order);
		
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("log");
		if(userID == null) userID = "";
	
		int reviewCnt = ReviewDAO.getInstance().getCount(Integer.parseInt(id));
		if( reviewCnt > 0) {
			PageContext pageCxt = new PageContext(Integer.parseInt(currentPage), reviewCnt);
			ArrayList<Review> rlist = ReviewDAO.getInstance().getTotalList(userID,Integer.parseInt(id),pageCxt.getStartIndex() ,pageCxt.getPageSize(),sqlOrder);
			
			Gson gson = new Gson();
			JsonArray ja = new JsonArray ();
			ja.add(gson.toJson(pageCxt));
			
			JsonArray jsonRList = new JsonArray ();
			for(Review r : rlist) { 
				jsonRList.add(gson.toJson(r));
			};
			
			ja.add(jsonRList);
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print(ja.toString());
			response.getWriter().close();
		}
		return null;
	}

}
