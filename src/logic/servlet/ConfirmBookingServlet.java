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

@WebServlet("/ConfirmBookingServlet")
public class ConfirmBookingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
    	bookingBean.setBbUser(request.getParameter("userToRegister1"));
    	bookingBean.setBbDate(request.getParameter("date1"));
    	bookingBean.setBbTime(request.getParameter("time1"));
    	bookingBean.setBbStatus("A");
    	
        HttpSession session = request.getSession(true);
    	
        BookingController controller = new BookingController();
    	controller.modifyBooking(bookingBean);
        
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Booking accepted successfully.');");
        out.println("location='homeOwner.jsp';");
        out.println("</script>");
	}
}
