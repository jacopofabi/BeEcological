package logic.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Bean.CenterBean;
import logic.Bean.CenterOwnerBean;
import logic.Bean.UnloadBean;
import logic.Bean.WasteUnloadedBean;
import logic.Controller.UnloadController;
import logic.Controller.WasteUnloadedController;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteUnloadServlet")
public class DeleteUnloadServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String openScript = "<script type=\"text/javascript\">";
		String closeScript = "</script>";
		CenterOwnerBean ownerBean = new CenterOwnerBean();
    	CenterBean centerBean = new CenterBean();
    	WasteUnloadedBean wasteBean = new WasteUnloadedBean();
    	
        ownerBean.setCobUsername(request.getParameter("username"));
        ownerBean.setCobEmail(request.getParameter("mail"));
        ownerBean.setCobPhone(request.getParameter("ownerphone"));
        centerBean.setCbName(request.getParameter("centername"));
        centerBean.setCbAddress(request.getParameter("address"));
        centerBean.setCbPhone(request.getParameter("centerphone"));
        
        wasteBean.setWbUser(request.getParameter("userToDelete1"));
    	wasteBean.setWbCenter(request.getParameter("centername"));
    	wasteBean.setWbDate(request.getParameter("date1"));
    	wasteBean.setWbTime(request.getParameter("time1"));
    	wasteBean.setWbWaste(request.getParameter("waste"));
    	wasteBean.setWbWasteQuantity(Integer.parseInt(request.getParameter("quantity")));
        
		//cancello lo scarico di un rifiuto e tolgo ecoPoints con trigger
		WasteUnloadedController controller = new WasteUnloadedController();
		controller.deleteWasteForAnUnload(wasteBean);
		
		HttpSession session = request.getSession(true);
		
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        out.println(openScript);
        out.println("alert('Unload waste deleted successfully.');");
        out.println(closeScript);
		
		int count = controller.numberOfWasteForAnUnload(wasteBean);
		if(count==0) {
			//non ho piu niente registrato per quello scarico, lo elimino
	        UnloadBean unload = new UnloadBean();
			unload.setUbUser(wasteBean.getWbUser());
			unload.setUbCenter(wasteBean.getWbCenter());
			unload.setUbDate(wasteBean.getWbDate());
			unload.setUbTime(wasteBean.getWbTime());
			UnloadController controller1 = new UnloadController();
	        controller1.deleteAnUnload(unload);
	        session.setAttribute("loggedOwner", ownerBean);
	        session.setAttribute("centerInfo", centerBean);
	        out.println(openScript);
	        out.println("alert('Unload deleted successfully. No one waste already exists for this unload.');");
	        out.println(closeScript);
		}
        out.println(openScript);
        out.println("location='homeOwner.jsp';");
        out.println(closeScript);
	}

}
