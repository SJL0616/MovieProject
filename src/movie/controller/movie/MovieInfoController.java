package movie.controller.movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String id = request.getParameter("id");
		if(id == null) {
			return null;
		}
		Movie m = MovieDAO.getInstance().getOneMovie(Integer.parseInt(id));
		request.setAttribute("vo", m);
		
		String currentPage = request.getParameter("currentPage");
		if( currentPage == null) currentPage = "1";
		int reviewCnt = ReviewDAO.getInstance().getCount(Integer.parseInt(id));
		if( reviewCnt > 0) {
			PageContext pageCxt = new PageContext(Integer.parseInt(currentPage), reviewCnt);
			ArrayList<Review> rlist = ReviewDAO.getInstance().getTotalList("qwer",Integer.parseInt(id),pageCxt.getStartIndex() ,pageCxt.getPageSize(),0);
			
			request.setAttribute("rlist", rlist);
			request.setAttribute("pageCxt", pageCxt);
		}
		request.setAttribute("reviewCnt",reviewCnt);
		
		return "movie-detail";
	}

}
