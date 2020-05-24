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
import logic.controller.BookingController;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteBookingServlet")
public class DeleteBookingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out4 = response.getWriter();
		CenterOwnerBean ownerBean4 = new CenterOwnerBean();
    	CenterBean centerBean4 = new CenterBean();
        BookingBean bookingBean4 = new BookingBean();
        
        ownerBean4.setCobUsername(request.getParameter("username"));
        ownerBean4.setCobEmail(request.getParameter("mail"));
        ownerBean4.setCobPhone(request.getParameter("ownerphone"));
        centerBean4.setCbName(request.getParameter("centername"));
        centerBean4.setCbAddress(request.getParameter("address"));
        centerBean4.setCbPhone(request.getParameter("centerphone"));
        
    	bookingBean4.setBbCenter(request.getParameter("centername"));
    	bookingBean4.setBbUser(request.getParameter("userToDelete"));
    	bookingBean4.setBbDate(request.getParameter("date"));
    	bookingBean4.setBbTime(request.getParameter("time"));
    	bookingBean4.setBbStatus("D");
    	
        HttpSession session4 = request.getSession(true);
    	
        BookingController controller4 = new BookingController();
    	controller4.modifyBooking(bookingBean4);
        
        session4.setAttribute("loggedOwner", ownerBean4);
        session4.setAttribute("centerInfo", centerBean4);
        out4.println("<script type=\"text/javascript\">");
        out4.println("alert('Booking refused successfully.');");
        out4.println("location='homeOwner.jsp';");
        out4.println("</script>");
	}
}
