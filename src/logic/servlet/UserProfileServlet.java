package logic.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.bean.UserBean;
import logic.bean.WasteUnloadedBean;
import logic.controller.UserController;
import logic.controller.WasteUnloadedController;

import java.io.IOException;
import java.util.List;

@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean userBean = new UserBean();
    	userBean.setUsbUsername(request.getParameter("username"));
    	UserController controller = new UserController();
        WasteUnloadedController controller1 = new WasteUnloadedController();
        
        List<String> information = controller.userInformation(userBean);
        userBean.setUsbName(information.get(0));
        userBean.setUsbSurname(information.get(1));
        userBean.setUsbEmail(information.get(2));
        userBean.setUsbPhone(information.get(3));
        userBean.setEcopoints(Integer.parseInt(information.get(4)));
        
        List<WasteUnloadedBean> listUnloadUser = controller1.listUnloadByUser(userBean);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedUser", userBean);
        session.setAttribute("listUnloadUser", listUnloadUser);
        response.sendRedirect("userProfile.jsp");
    }
}
