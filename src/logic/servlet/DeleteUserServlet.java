package logic.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Bean.UserBean;
import logic.Controller.UserController;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserBean userBean = new UserBean();
        
        userBean.setUsbUsername(request.getParameter("username"));
        UserController controller = new UserController();
        controller.deleteAccount(userBean);
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedUser", null);
        
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Account deleted successfully.');");
        out.println("location='homepage.jsp';");
        out.println("</script>");
	}

}
