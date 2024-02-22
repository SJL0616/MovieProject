package movie.frontcontroller;

import java.util.HashMap;

import movie.controller.Controller;
import movie.controller.IndexController;
import movie.controller.UserLoginController;

public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/main.do", new IndexController());
		mappings.put("/login.do", new UserLoginController());
	}

	public Controller getController(String key) {
		return mappings.get(key);
	}
}
