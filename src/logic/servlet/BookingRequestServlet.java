package logic.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import error.InexistentUsernameException;
import logic.Bean.BookingBean;
import logic.Bean.UserBean;
import logic.Controller.BookingController;
import logic.Controller.UserController;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/BookingRequestServlet")
public class BookingRequestServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserBean userBean = new UserBean();
    	BookingBean bookingBean = new BookingBean();
        userBean.setUsbUsername(request.getParameter("username"));
        
        UserController controller = new UserController();
        boolean result = true;
		result = controller.checkRegistration(userBean);

        HttpSession session = request.getSession(true);
        
        //se false l'username esiste nel sistema
        if(!result) {
            bookingBean.setBbUser(userBean.getUsbUsername());
            bookingBean.setBbCenter(request.getParameter("centername"));
            bookingBean.setBbDate(request.getParameter("date"));
            bookingBean.setBbTime(request.getParameter("time"));
            bookingBean.setBbStatus("W");
            BookingController controller1 = new BookingController();
            try {
				controller1.insertBooking(bookingBean);
			} catch (InexistentUsernameException e) {
				
			}
        	session.setAttribute("loggedUser", userBean);
		    out.println("<script type=\"text/javascript\">");
		    out.println("alert('Your booking request is completed successfully!');");
		    out.println("location='homepage.jsp';");
		    out.println("</script>");
        }
        else {
        	request.setAttribute("alertMsg", "You must login in the system for book an unload.");
            RequestDispatcher rd = request.getRequestDispatcher("/loginUser.jsp");
            rd.include(request, response);
        }
	}
}