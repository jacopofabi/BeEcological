package logic.servlet
;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.bean.CenterBean;
import logic.bean.UserBean;
import logic.controller.MakeBookingController;

import java.io.IOException;
import java.util.List;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	CenterBean centerBean = new CenterBean();
    	UserBean userBean = new UserBean();
        centerBean.setCbName(req.getParameter("search"));
        userBean.setUsbUsername(req.getParameter("username"));
        MakeBookingController controller = new MakeBookingController();
        List<CenterBean> centerList = controller.centerList(centerBean);
        
        HttpSession session = req.getSession(true);
        session.setAttribute("textSearched", centerBean.getCbName());
        session.setAttribute("listOfCenter", centerList);
        session.setAttribute("loggedUser", userBean);
        resp.sendRedirect("searchResult.jsp");
    }
}
