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
import java.util.List;

@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
        UserBean userBean = new UserBean();
        userBean.setUsbUsername(req.getParameter("param"));
        UserController controller = new UserController();
        List<String> userInformation = controller.userInformation(userBean);
        userBean.setUsbName(userInformation.get(0));
        userBean.setUsbSurname(userInformation.get(1));
        userBean.setUsbEmail(userInformation.get(2));
        userBean.setUsbPhone(userInformation.get(3));
        userBean.setEcopoints(Integer.parseInt(userInformation.get(4)));
        
        session.setAttribute("loggedUser", userBean);
        resp.sendRedirect("shop.jsp");
    }
}
