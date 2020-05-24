package logic.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.bean.CenterBean;
import logic.bean.CenterOwnerBean;

import java.io.IOException;

@WebServlet("/HomeOwnerServlet")
public class HomeOwnerServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	CenterOwnerBean ownerBean = new CenterOwnerBean();
    	CenterBean centerBean = new CenterBean();
    	ownerBean.setCobUsername(request.getParameter("username"));
    	ownerBean.setCobPhone(request.getParameter("ownerphone"));
    	ownerBean.setCobEmail(request.getParameter("mail"));
    	centerBean.setCbName(request.getParameter("centername"));
    	centerBean.setCbAddress(request.getParameter("address"));
    	centerBean.setCbPhone(request.getParameter("centerphone"));
    	
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        response.sendRedirect("homeOwner.jsp");
	 }
}
