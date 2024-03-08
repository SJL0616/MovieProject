package movie.controller.movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.MovieDAO;
import movie.dao.ReviewDAO;
import movie.dao.ReviewLikesDAO;
import movie.vo.Movie;
import movie.vo.PageContext;
import movie.vo.Review;
import movie.vo.ReviewLikes;

public class MovieInfoController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mid = request.getParameter("id");
		if(mid == null) {
			return null;
		}
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("log");
		if(userID == null) userID = "";
		
		Movie m = MovieDAO.getInstance().getOneMovie(Integer.parseInt(mid),userID);
		request.setAttribute("vo", m);
		
		String currentPage = request.getParameter("currentPage");
		if( currentPage == null) currentPage = "1";
		int reviewCnt = ReviewDAO.getInstance().getCount(Integer.parseInt(mid));
		int pointAvg = ReviewDAO.getInstance().getAvgPoint(Integer.parseInt(mid));
		if( reviewCnt > 0) {
			PageContext pageCxt = new PageContext(Integer.parseInt(currentPage), reviewCnt);
			ArrayList<Review> rlist = ReviewDAO.getInstance().getTotalList(userID,Integer.parseInt(mid),pageCxt.getStartIndex() ,pageCxt.getPageSize(),0);
			
			request.setAttribute("rlist", rlist);
			request.setAttribute("pageCxt", pageCxt);
		}
		request.setAttribute("reviewCnt",reviewCnt);
		request.setAttribute("pointAvg",pointAvg);
		
		return "movie-detail";
	}

}
