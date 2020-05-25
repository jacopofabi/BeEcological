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
import java.io.PrintWriter;

@WebServlet("/ConfirmBookingServlet")
public class ConfirmBookingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out5 = response.getWriter();
		CenterOwnerBean ownerBean5 = new CenterOwnerBean();
    	CenterBean centerBean5 = new CenterBean();
        BookingBean bookingBean5 = new BookingBean();
        
        ownerBean5.setCobUsername(request.getParameter("username"));
        ownerBean5.setCobEmail(request.getParameter("mail"));
        ownerBean5.setCobPhone(request.getParameter("ownerphone"));
        centerBean5.setCbName(request.getParameter("centername"));
        centerBean5.setCbAddress(request.getParameter("address"));
        centerBean5.setCbPhone(request.getParameter("centerphone"));
        
    	bookingBean5.setBbCenter(request.getParameter("centername"));
    	bookingBean5.setBbUser(request.getParameter("userToRegister1"));
    	bookingBean5.setBbDate(request.getParameter("date1"));
    	bookingBean5.setBbTime(request.getParameter("time1"));
    	bookingBean5.setBbStatus("A");
    	
        HttpSession session5 = request.getSession(true);
    	
        ManageBookingController controller5 = new ManageBookingController();
    	controller5.modifyBooking(bookingBean5);
        
        session5.setAttribute("loggedOwner", ownerBean5);
        session5.setAttribute("centerInfo", centerBean5);
        out5.println("<script type=\"text/javascript\">");
        out5.println("alert('Booking accepted successfully.');");
        out5.println("location='homeOwner.jsp';");
        out5.println("</script>");
	}
}
