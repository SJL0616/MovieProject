package movie.controller.movie.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
	
		int reviewCnt = ReviewDAO.getInstance().getCount(Integer.parseInt(id));
		if( reviewCnt > 0) {
			PageContext pageCxt = new PageContext(Integer.parseInt(currentPage), reviewCnt);

			System.out.println("id : "+ id + " startIdex = "+ pageCxt.getStartIndex());
			ArrayList<Review> rlist = ReviewDAO.getInstance().getTotalList(Integer.parseInt(id),pageCxt.getStartIndex() ,pageCxt.getPageSize());
		
			for(Review r : rlist) {
				System.out.println(r.getContent());
			}
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
			//request.setAttribute("rlist", rlist);
			//request.setAttribute("pageCxt", pageCxt);
		}
		return null;
	}

}
