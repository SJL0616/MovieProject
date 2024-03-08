package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.dao.TicKetDAO;

public class AdminTicketRemoveController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int ticketID = Integer.parseInt(request.getParameter("ticketID"));
		TicKetDAO.getInstance().cancelTicketByID(ticketID);
		String ctx = request.getContextPath();
		return "redirect:" + ctx + "/admin.do";
	}
}
