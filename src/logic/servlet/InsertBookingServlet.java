package logic.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import error.InexistentUsernameException;
import logic.bean.BookingBean;
import logic.bean.CenterBean;
import logic.bean.CenterOwnerBean;
import logic.bean.UserBean;
import logic.controller.BookingController;
import logic.controller.UserController;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/InsertBookingServlet")
public class InsertBookingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean checkTime(String hour){
	    try {
	    	LocalTime.parse(hour);
	        return true;
	    }
	    catch (DateTimeParseException | NullPointerException e) {
	    	Logger.getGlobal().log(Level.SEVERE, "Invalid time string");
	        return false;
	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String openScript = "<script type=\"text/javascript\">";
		String closeScript = "</script>";
		String locationManageBooking = "location='manageBooking.jsp';";
		UserBean userBean1 = new UserBean();
		CenterOwnerBean ownerBean1 = new CenterOwnerBean();
    	CenterBean centerBean1 = new CenterBean();
        BookingBean bookingBean1 = new BookingBean();
        
        userBean1.setUsbUsername(request.getParameter("userToRegister"));
        
        ownerBean1.setCobUsername(request.getParameter("username"));
        ownerBean1.setCobEmail(request.getParameter("mail"));
        ownerBean1.setCobPhone(request.getParameter("ownerphone"));
        centerBean1.setCbName(request.getParameter("centername"));
        centerBean1.setCbAddress(request.getParameter("address"));
        centerBean1.setCbPhone(request.getParameter("centerphone"));
        
    	bookingBean1.setBbCenter(request.getParameter("centername"));
    	bookingBean1.setBbUser(request.getParameter("userToRegister"));
    	bookingBean1.setBbDate(request.getParameter("date"));
    	bookingBean1.setBbTime(request.getParameter("time"));
        
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedOwner", ownerBean1);
        session.setAttribute("centerInfo", centerBean1);
        
    	UserController controller = new UserController();
    	boolean result = false;
		result = controller.checkRegistration(userBean1);
    	if(result) {
    		//username non esiste non posso inserire
            out.println(openScript);
            out.println("alert('Username non exist in the system.');");
            out.println(locationManageBooking);
            out.println(closeScript);
            return;
    	}
    	
		if (!checkTime(bookingBean1.getBbTime())) {
			//orario immesso invalido non posso inserire
            out.println(openScript);
            out.println("alert('Insert a correct time format: [HH:MM].');");
            out.println(locationManageBooking);
            out.println(closeScript);
            return;
		}
    	
    	BookingController controller1 = new BookingController();
    	bookingBean1.setBbStatus("W");
    	int count = controller1.verifyBooking(bookingBean1);
    	if(count!=0) {
    		//esiste prenotazione, la aggiorno accettandola
    		bookingBean1.setBbStatus("A");
    		controller1.modifyBooking(bookingBean1);
            out.println(openScript);
            out.println("alert('Booking accepted successfully.');");
            out.println("location='homeOwner.jsp';");
            out.println(closeScript);
            return;
    	}
    	
    	bookingBean1.setBbStatus("A");
		count = controller1.verifyBooking(bookingBean1);
    	if(count!=0) {
    		//prenotazione gia accettata, non posso inserire
            out.println(openScript);
            out.println("alert('Booking already exist in the system.');");
            out.println(locationManageBooking);
            out.println(closeScript);
            return;
    	}    	
        
    	try {
			controller1.insertBooking(bookingBean1);
		} catch (InexistentUsernameException e) {
			Logger.getGlobal().log(Level.SEVERE, "Invalid username");
		}
        
        out.println(openScript);
        out.println("alert('Booking accepted successfully.');");
        out.println("location='homeOwner.jsp';");
        out.println(closeScript);
	}

}
