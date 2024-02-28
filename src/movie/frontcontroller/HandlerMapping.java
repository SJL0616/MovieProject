package movie.frontcontroller;

import java.util.HashMap;

import movie.controller.Controller;
import movie.controller.IndexController;
import movie.controller.movie.MovieInfoController;
import movie.controller.movie.MovieListController;
import movie.controller.ticketing.MovieTheaterController;
import movie.controller.ticketing.TicketingController;
import movie.controller.user.CheckLogController;
import movie.controller.user.DisconnectToKakao;
import movie.controller.user.DisconnectToNaver;
import movie.controller.user.KakaoController;
import movie.controller.user.KakaoResultController;
import movie.controller.user.NaverController;
import movie.controller.user.NaverResultController;
import movie.controller.user.UserIdValidAjax;
import movie.controller.user.UserJoinController;
import movie.controller.user.UserLoginController;
import movie.controller.user.UserLogoutController;
import movie.controller.user.UserMyMegaController;
import movie.controller.user.UserPwValidAjax;
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
		mappings.put("/userPwValidAjax.do", new UserPwValidAjax());
	}

	public Controller getController(String key) {
		return mappings.get(key);
	}
}
