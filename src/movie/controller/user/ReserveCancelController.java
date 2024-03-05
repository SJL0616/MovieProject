package movie.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movie.controller.Controller;

public class ReserveCancelController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("");
		String ticketCode = request.getParameter("ticketCode");
		return "userMyMega";
	}
}
