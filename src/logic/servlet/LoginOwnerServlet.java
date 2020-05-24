package logic.Servlet;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/LoginOwnerServlet")
public class LoginOwnerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result;
    	CenterOwnerBean ownerBean = new CenterOwnerBean();
    	CenterBean centerBean = new CenterBean();
        ownerBean.setCobUsername(req.getParameter("username"));
        ownerBean.setCobPassword(req.getParameter("password"));
        OwnerController controller = new OwnerController();
        result = controller.login(ownerBean);

        if (!result) {
            req.setAttribute("alertMsg", "Not valid login credentials.");
            RequestDispatcher rd = req.getRequestDispatcher("/loginOwner.jsp");
            rd.include(req, resp);
        } else {
        	//credenziali corrette
            List<String> information = controller.ownerData(ownerBean);
            ownerBean.setCobName(information.get(0));
            ownerBean.setCobSurname(information.get(1));
            ownerBean.setCobEmail(information.get(2));
            ownerBean.setCobPhone(information.get(3));
            centerBean.setCbName(information.get(4));
            centerBean.setCbPhone(information.get(5));
            centerBean.setCbCity(information.get(6));
            System.out.println(information.get(7));
            centerBean.setCbAddress(information.get(7));
            centerBean.setCbCap(information.get(8));
            centerBean.setCbNum(information.get(9));
            HttpSession session = req.getSession(true);
            session.setAttribute("loggedOwner", ownerBean);
            session.setAttribute("centerInfo", centerBean);
            resp.sendRedirect("homeOwner.jsp");
        }
    }
}
