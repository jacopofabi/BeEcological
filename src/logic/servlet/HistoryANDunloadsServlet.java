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
import logic.bean.WasteUnloadedBean;
import logic.controller.ManageBookingController;
import logic.controller.RegisterUnloadController;

import java.io.IOException;
import java.util.List;

@WebServlet("/HistoryANDunloadsServlet")
public class HistoryANDunloadsServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	CenterOwnerBean ownerBean = new CenterOwnerBean();
    	CenterBean centerBean = new CenterBean();
        BookingBean bookingBean = new BookingBean();
        WasteUnloadedBean wasteBean = new WasteUnloadedBean();
        String centername = request.getParameter("centername");
        ownerBean.setCobUsername(request.getParameter("username"));
        ownerBean.setCobEmail(request.getParameter("mail"));
        ownerBean.setCobPhone(request.getParameter("ownerphone"));
        centerBean.setCbName(centername);
        centerBean.setCbAddress(request.getParameter("address"));
        centerBean.setCbPhone(request.getParameter("centerphone"));
    	bookingBean.setBbCenter(centername);
    	wasteBean.setWbCenter(centername);
    	bookingBean.setBbStatus("A");
        ManageBookingController controller = new ManageBookingController();
        List<BookingBean> bookAccept= controller.bookingListByCenter(bookingBean);
        RegisterUnloadController controller1 = new RegisterUnloadController();
        List<WasteUnloadedBean> unloadRegister = controller1.listUnloadByCenter(wasteBean);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("bookAccept", bookAccept);
        session.setAttribute("unloadRegister", unloadRegister);
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        response.sendRedirect("historyANDunloads.jsp");
	}

}
