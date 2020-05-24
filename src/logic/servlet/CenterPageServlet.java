package logic.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Bean.CenterBean;
import logic.Bean.CenterOwnerBean;
import logic.Bean.UserBean;
import logic.Controller.CenterController;

import java.io.IOException;

@WebServlet("/CenterPageServlet")
public class CenterPageServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean userBean = new UserBean();
        CenterBean centerBean = new CenterBean();
        CenterOwnerBean owner = new CenterOwnerBean();
    	userBean.setUsbUsername(request.getParameter("username"));
    	centerBean.setCbAddress(request.getParameter("address"));
    	centerBean.setCbPhone(request.getParameter("phone"));
    	centerBean.setCbName(request.getParameter("centername"));
    	
        CenterController controller = new CenterController();
        owner = controller.ownerOfTheSelectedCenter(centerBean);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedUser", userBean);
        session.setAttribute("ownerInfo", owner);
        session.setAttribute("centerInfo", centerBean);
        response.sendRedirect("centerView.jsp");
    }
}
