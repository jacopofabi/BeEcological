package logic.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.bean.BookingBean;
import logic.bean.UserBean;
import logic.controller.UnloadHistoryController;

import java.io.IOException;
import java.util.List;

@WebServlet("/UserBookingListServlet")
public class UserBookingListServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserBean userBean = new UserBean();
        BookingBean bookingBean = new BookingBean();
    	userBean.setUsbUsername(request.getParameter("username"));
    	bookingBean.setBbUser(userBean.getUsbUsername());
    	bookingBean.setBbStatus("W");
        UnloadHistoryController controller = new UnloadHistoryController();
        List<BookingBean> bookingWait = controller.bookingListByUser(bookingBean);
        bookingBean.setBbStatus("A");
        List<BookingBean> bookingAccepted = controller.bookingListByUser(bookingBean);
        bookingBean.setBbStatus("D");
        List<BookingBean> bookingRefuse = controller.bookingListByUser(bookingBean);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("bookWait", bookingWait);
        session.setAttribute("bookAccept", bookingAccepted);
        session.setAttribute("bookRefuse", bookingRefuse);
        session.setAttribute("loggedUser", userBean);
        response.sendRedirect("userBookingList.jsp");
	}

}
