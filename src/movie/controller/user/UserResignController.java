package movie.controller.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.controller.Controller;
import movie.dao.SeatDAO;
import movie.dao.TicKetDAO;
import movie.dao.UserDAO;
import movie.vo.Ticket;
import movie.vo.UserReservationView;
import movie.vo.UserVO;

public class UserResignController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");

		// 회원탈퇴 전에 모든 좌석과 모든 예매내역을 되돌려놓습니다.
		ArrayList<UserReservationView> list = TicKetDAO.getInstance().getTicketListById(user.getId());
		for (int i = 0; i < list.size(); i += 1) {
			int ticketID = list.get(i).getTicketID();
			Ticket ticket = TicKetDAO.getInstance().getTicketByID(ticketID);
			String beforeSplit = ticket.getSeatIDList();
			String[] afterSplit = beforeSplit.split(",");

			// 취소된 좌석들을 돌려놓는 반복문입니다.
			for (int j = 0; j < afterSplit.length; j += 1) {
				SeatDAO.getInstance().putTheSeatsBack(Integer.parseInt(afterSplit[j]));
			}

			// 좌석을 전부 되돌려놓은 후에 예매객체가 삭제됩니다.(실제로 삭제는 아니고 관리자에게는 보임)
			TicKetDAO.getInstance().setTrueToTicket(ticketID);
		}

		// 회원이 삭제됩니다.
		int cnt = UserDAO.getInstance().resignUser(user.getId());
		if (cnt > 0) {
			session.invalidate();
			String ctx = request.getContextPath();
			System.out.println("회원탈퇴 성공");
			return "redirect:" + ctx + "/index.jsp";
		}
		System.out.println("회원탈퇴 실패");
		return null;
	}
}
