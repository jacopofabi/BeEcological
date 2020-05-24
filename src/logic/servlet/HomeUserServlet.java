package logic.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Bean.UserBean;

import java.io.IOException;

@WebServlet("/HomeUserServlet")
public class HomeUserServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserBean userBean = new UserBean();
        userBean.setUsbUsername(request.getParameter("username"));

        HttpSession session = request.getSession(true);
        session.setAttribute("loggedUser", userBean);
        response.sendRedirect("homepage.jsp");
	 }
}
