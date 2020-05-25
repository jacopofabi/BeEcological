package logic.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import error.EmptyFieldException;
import error.InexistentUsernameException;
import logic.bean.UserBean;
import logic.controller.LoginController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {

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
        boolean result = false;
    	UserBean userBean = new UserBean();
        userBean.setUsbUsername(req.getParameter("username"));
        userBean.setUsbPassword(req.getParameter("password"));
        LoginController controller = new LoginController();
        try {
			result = controller.loginUser(userBean);
		} catch (InexistentUsernameException | EmptyFieldException e) {
			Logger.getGlobal().log(Level.SEVERE, "Invalid username");
		}

        if (!result) {
            req.setAttribute("alertMsg", "Not valid login credentials.");
            RequestDispatcher rd = req.getRequestDispatcher("/loginUser.jsp");
            rd.include(req, resp);
        } else {
            HttpSession session = req.getSession(true);
            session.setAttribute("loggedUser", userBean);
            resp.sendRedirect("homepage.jsp");
        }
    }
}
