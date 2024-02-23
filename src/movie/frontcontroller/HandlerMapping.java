package movie.frontcontroller;

import java.util.HashMap;

import movie.controller.Controller;
import movie.controller.IndexController;
import movie.controller.user.KakaoController;
import movie.controller.user.UserLoginController;
import movie.controller.user.UserLogoutController;

public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/main.do", new IndexController());
		mappings.put("/login.do", new UserLoginController());
		mappings.put("/logout.do", new UserLogoutController());
		mappings.put("/kakaoLogin.do", new KakaoController());
	}

	public Controller getController(String key) {
		return mappings.get(key);
	}
}
