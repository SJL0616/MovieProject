package movie.frontcontroller;

import java.util.HashMap;

import movie.controller.Controller;
import movie.controller.IndexController;
import movie.controller.user.KakaoController;
import movie.controller.user.NaverController;
import movie.controller.user.UserIdValidAjax;
import movie.controller.user.UserJoinController;
import movie.controller.user.UserLoginController;
import movie.controller.user.UserLogoutController;
import movie.controller.user.UserUpdateController;
public class HandlerMapping {
	private HashMap<String, Controller> mappings;
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/main.do", new IndexController());
		mappings.put("/login.do", new UserLoginController());
		mappings.put("/logout.do", new UserLogoutController());
		mappings.put("/kakaoLogin.do", new KakaoController());
		mappings.put("/naverLogin.do", new NaverController());
		mappings.put("/userJoin.do", new UserJoinController());
		mappings.put("/userIdValidAjax.do", new UserIdValidAjax());
		mappings.put("/userUpdate.do", new UserUpdateController());
	}
	public Controller getController(String key) {
		return mappings.get(key);
	}
}
