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

@WebServlet("/InsertBookingServlet")
public class InsertBookingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean checkTime(String hour){
	    try {
	    	LocalTime.parse(hour);
	        System.out.println("Valid time string: " + hour);
	        return true;
	    }
	    catch (DateTimeParseException | NullPointerException e) {
	        System.out.println("Invalid time string: " + hour);
	        return false;
	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String openScript = "<script type=\"text/javascript\">";
		String closeScript = "</script>";
		String locationManageBooking = "location='manageBooking.jsp';";
		UserBean userBean = new UserBean();
		CenterOwnerBean ownerBean = new CenterOwnerBean();
    	CenterBean centerBean = new CenterBean();
        BookingBean bookingBean = new BookingBean();
        
        userBean.setUsbUsername(request.getParameter("userToRegister"));
        
        ownerBean.setCobUsername(request.getParameter("username"));
        ownerBean.setCobEmail(request.getParameter("mail"));
        ownerBean.setCobPhone(request.getParameter("ownerphone"));
        centerBean.setCbName(request.getParameter("centername"));
        centerBean.setCbAddress(request.getParameter("address"));
        centerBean.setCbPhone(request.getParameter("centerphone"));
        
    	bookingBean.setBbCenter(request.getParameter("centername"));
    	bookingBean.setBbUser(request.getParameter("userToRegister"));
    	bookingBean.setBbDate(request.getParameter("date"));
    	bookingBean.setBbTime(request.getParameter("time"));
        
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        
    	UserController controller = new UserController();
    	boolean result = false;
		result = controller.checkRegistration(userBean);
    	if(result) {
    		//username non esiste non posso inserire
            out.println(openScript);
            out.println("alert('Username non exist in the system.');");
            out.println(locationManageBooking);
            out.println(closeScript);
            return;
    	}
    	
		if (!checkTime(bookingBean.getBbTime())) {
			//orario immesso invalido non posso inserire
            out.println(openScript);
            out.println("alert('Insert a correct time format: [HH:MM].');");
            out.println(locationManageBooking);
            out.println(closeScript);
            return;
		}
    	
    	BookingController controller1 = new BookingController();
    	bookingBean.setBbStatus("W");
    	int count = controller1.verifyBooking(bookingBean);
    	if(count!=0) {
    		//esiste prenotazione, la aggiorno accettandola
    		bookingBean.setBbStatus("A");
    		controller1.modifyBooking(bookingBean);
            out.println(openScript);
            out.println("alert('Booking accepted successfully.');");
            out.println("location='homeOwner.jsp';");
            out.println(closeScript);
            return;
    	}
    	
    	bookingBean.setBbStatus("A");
		count = controller1.verifyBooking(bookingBean);
    	if(count!=0) {
    		//prenotazione gia accettata, non posso inserire
            out.println(openScript);
            out.println("alert('Booking already exist in the system.');");
            out.println(locationManageBooking);
            out.println(closeScript);
            return;
    	}    	
        
    	try {
			controller1.insertBooking(bookingBean);
		} catch (InexistentUsernameException e) {

		}
        
        out.println(openScript);
        out.println("alert('Booking accepted successfully.');");
        out.println("location='homeOwner.jsp';");
        out.println(closeScript);
	}

}
