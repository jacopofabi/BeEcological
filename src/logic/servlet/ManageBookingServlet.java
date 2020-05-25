package logic.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.bean.BookingBean;
import logic.bean.CenterBean;
import logic.bean.CenterOwnerBean;
import logic.controller.ManageBookingController;

import java.io.IOException;
import java.util.List;

@WebServlet("/ManageBookingServlet")
public class ManageBookingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	CenterOwnerBean ownerBean3 = new CenterOwnerBean();
    	CenterBean centerBean3 = new CenterBean();
        BookingBean bookingBean3 = new BookingBean();
        ownerBean3.setCobUsername(request.getParameter("username"));
        ownerBean3.setCobEmail(request.getParameter("mail"));
        ownerBean3.setCobPhone(request.getParameter("ownerphone"));
        centerBean3.setCbName(request.getParameter("centername"));
        centerBean3.setCbAddress(request.getParameter("address"));
        centerBean3.setCbPhone(request.getParameter("centerphone"));
    	bookingBean3.setBbCenter(request.getParameter("centername"));
    	bookingBean3.setBbStatus("W");
        ManageBookingController controller = new ManageBookingController();
        List<BookingBean> bookWait = controller.bookingListByCenter(bookingBean3);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("bookWait", bookWait);
        session.setAttribute("loggedOwner", ownerBean3);
        session.setAttribute("centerInfo", centerBean3);
        response.sendRedirect("manageBooking.jsp");
	}

}
