package movie.frontcontroller;

import java.util.HashMap;

import movie.controller.AdminController;
import movie.controller.Controller;
import movie.controller.IndexController;
import movie.controller.movie.MovieInfoController;
import movie.controller.movie.MovieListController;
import movie.controller.movie.MovieSearchController;
import movie.controller.movie.review.ReviewCntController;
import movie.controller.movie.review.ReviewDeleteController;
import movie.controller.movie.review.ReviewLikeController;
import movie.controller.movie.review.ReviewListController;
import movie.controller.movie.review.ReviewRegController;
import movie.controller.movie.review.ReviewReportController;
import movie.controller.ticketing.MovieTheaterController;
import movie.controller.ticketing.PaymentController;
import movie.controller.ticketing.SeatController;
import movie.controller.ticketing.TicketingController;
import movie.controller.user.CheckLogController;
import movie.controller.user.DisconnectToKakao;
import movie.controller.user.DisconnectToNaver;
import movie.controller.user.KakaoController;
import movie.controller.user.KakaoResultController;
import movie.controller.user.NaverController;
import movie.controller.user.NaverResultController;
import movie.controller.user.ReserveCancelController;
import movie.controller.user.UserIdValidAjax;
import movie.controller.user.UserJoinController;
import movie.controller.user.UserLoginController;
import movie.controller.user.UserLogoutController;
import movie.controller.user.UserMyMegaController;
import movie.controller.user.UserPwValidAjax;
import movie.controller.user.UserResignController;
import movie.controller.user.UserResignValidAjax;
import movie.controller.user.UserUpdateController;

public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/main.do", new IndexController());
		mappings.put("/login.do", new UserLoginController());
		mappings.put("/logout.do", new UserLogoutController());
		mappings.put("/kakaoLogin.do", new KakaoController());
		mappings.put("/kakaoLoginResult.do", new KakaoResultController());
		mappings.put("/disconnectToKakao.do", new DisconnectToKakao());
		mappings.put("/naverLogin.do", new NaverController());
		mappings.put("/naverLoginResult.do", new NaverResultController());
		mappings.put("/disconnectToNaver.do", new DisconnectToNaver());
		mappings.put("/userJoin.do", new UserJoinController());
		mappings.put("/userIdValidAjax.do", new UserIdValidAjax());
		mappings.put("/checkLog.do", new CheckLogController());
		mappings.put("/userMyMega.do", new UserMyMegaController());
		mappings.put("/userUpdate.do", new UserUpdateController());
		mappings.put("/ticketing.do", new TicketingController());
		mappings.put("/movietheater.do", new MovieTheaterController());
		mappings.put("/movie.do", new MovieListController());
		mappings.put("/movie-detail.do", new MovieInfoController());
		mappings.put("/movieSearch.do", new MovieSearchController());
		mappings.put("/movieSearchFromMain.do", new MovieSearchController());
		mappings.put("/seat.do", new SeatController());
		mappings.put("/userPwValidAjax.do", new UserPwValidAjax());
		mappings.put("/userResignValidAjax.do", new UserResignValidAjax());
		mappings.put("/userResign.do", new UserResignController());
		mappings.put("/regReview.do", new ReviewRegController());
		mappings.put("/showReview.do", new ReviewListController());
		mappings.put("/likeReview.do", new ReviewLikeController());
		mappings.put("/reportReview.do", new ReviewReportController());
		mappings.put("/deleteReview.do", new ReviewDeleteController());		
		mappings.put("/getReviewCnt.do", new ReviewCntController());
		mappings.put("/reserveCancel.do", new ReserveCancelController());
		mappings.put("/payment.do", new PaymentController());
		mappings.put("/admin.do", new AdminController());
	}

	public Controller getController(String key) {
		return mappings.get(key);
	}
}
