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

@WebServlet("/RegisterUnloadServlet")
public class RegisterUnloadServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	CenterOwnerBean ownerBean2 = new CenterOwnerBean();
    	CenterBean centerBean2 = new CenterBean();
        BookingBean bookingBean2 = new BookingBean();
        ownerBean2.setCobUsername(request.getParameter("username"));
        ownerBean2.setCobEmail(request.getParameter("mail"));
        ownerBean2.setCobPhone(request.getParameter("ownerphone"));
        centerBean2.setCbName(request.getParameter("centername"));
        centerBean2.setCbAddress(request.getParameter("address"));
        centerBean2.setCbPhone(request.getParameter("centerphone"));
    	bookingBean2.setBbCenter(request.getParameter("centername"));
    	bookingBean2.setBbStatus("A");
        ManageBookingController controller2 = new ManageBookingController();
        List<BookingBean> bookAccept= controller2.bookingListByCenter(bookingBean2);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("bookAccept", bookAccept);
        session.setAttribute("loggedOwner", ownerBean2);
        session.setAttribute("centerInfo", centerBean2);
        response.sendRedirect("registerUnload.jsp");
	}

}
