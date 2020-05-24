package logic.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.bean.UserBean;
import logic.controller.UserController;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        UserBean userBean = new UserBean();
        userBean.setUsbUsername(request.getParameter("username"));
        userBean.setEcopoints(Integer.parseInt(request.getParameter("totalPoints")));
        
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedUser", userBean);
        
        int newEcopoints = Integer.parseInt(request.getParameter("totalPoints")) - Integer.parseInt(request.getParameter("pointsToDelete"));
        if(newEcopoints<0) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Not enough ecoPoints to buy this item.');");
            out.println("location='shop.jsp';");
            out.println("</script>");
        }
        else {
		    userBean.setEcopoints(newEcopoints);
		    
		    UserController controller = new UserController();
		    controller.updateEcoPoints(userBean);
		    out.println("<script type=\"text/javascript\">");
		    out.println("alert('Item acquired successfully.');");
		    out.println("location='shop.jsp';");
		    out.println("</script>");
        }
    }
}
