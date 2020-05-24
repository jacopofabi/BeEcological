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
import java.io.PrintWriter;

@WebServlet("/DeleteBookingServlet")
public class DeleteBookingServlet extends HttpServlet {

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
    	bookingBean.setBbUser(request.getParameter("userToDelete"));
    	bookingBean.setBbDate(request.getParameter("date"));
    	bookingBean.setBbTime(request.getParameter("time"));
    	bookingBean.setBbStatus("D");
    	
        HttpSession session = request.getSession(true);
    	
        BookingController controller = new BookingController();
    	controller.modifyBooking(bookingBean);
        
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Booking refused successfully.');");
        out.println("location='homeOwner.jsp';");
        out.println("</script>");
	}
}
