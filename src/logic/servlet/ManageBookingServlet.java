package logic.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Bean.BookingBean;
import logic.Bean.CenterBean;
import logic.Bean.CenterOwnerBean;
import logic.Controller.BookingController;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ManageBookingServlet")
public class ManageBookingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	CenterOwnerBean ownerBean = new CenterOwnerBean();
    	CenterBean centerBean = new CenterBean();
        BookingBean bookingBean = new BookingBean();
        ownerBean.setCobUsername(request.getParameter("username"));
        ownerBean.setCobEmail(request.getParameter("mail"));
        ownerBean.setCobPhone(request.getParameter("ownerphone"));
        centerBean.setCbName(request.getParameter("centername"));
        centerBean.setCbAddress(request.getParameter("address"));
        centerBean.setCbPhone(request.getParameter("centerphone"));
    	bookingBean.setBbCenter(request.getParameter("centername"));
    	bookingBean.setBbStatus("W");
        BookingController controller = new BookingController();
        ArrayList<BookingBean> bookWait = controller.bookingListByCenter(bookingBean);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("bookWait", bookWait);
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        response.sendRedirect("manageBooking.jsp");
	}

}
