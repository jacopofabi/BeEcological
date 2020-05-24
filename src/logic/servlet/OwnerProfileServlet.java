package logic.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Bean.CenterBean;
import logic.Bean.CenterOwnerBean;
import logic.Controller.OwnerController;

import java.io.IOException;
import java.util.List;

@WebServlet("/OwnerProfileServlet")
public class OwnerProfileServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CenterOwnerBean ownerBean = new CenterOwnerBean();
        CenterBean centerBean = new CenterBean();
    	ownerBean.setCobUsername(request.getParameter("username"));
    	OwnerController controller = new OwnerController();
        
        List<String> information = controller.ownerData(ownerBean);
        ownerBean.setCobName(information.get(0));
        ownerBean.setCobSurname(information.get(1));
        ownerBean.setCobEmail(information.get(2));
        ownerBean.setCobPhone(information.get(3));
        ownerBean.setCobCenter(information.get(4));
        centerBean.setCbName(information.get(4));
        centerBean.setCbPhone(information.get(5));
        centerBean.setCbCity(information.get(6));
        centerBean.setCbAddress(information.get(7));
        centerBean.setCbCap(information.get(8));
        centerBean.setCbNum(information.get(9));
        
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        response.sendRedirect("ownerProfile.jsp");
    }
}
