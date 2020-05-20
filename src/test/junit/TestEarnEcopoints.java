package test.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logic.bean.UserBean;
import logic.bean.WasteUnloadedBean;
import logic.controller.UserController;
import logic.controller.WasteUnloadedController;

public class TestEarnEcopoints {
	
	private UserBean userBean;
	private WasteUnloadedBean wasteBean;
	private UserController userController = new UserController();
	private WasteUnloadedController wasteController = new WasteUnloadedController();
	
	@Before
	public void prepareData() {
		String username = "sixpain";
		String center = "la vecchia mola";
		String date = "2020-01-02";
		String time = "15:53";
		String waste = "Plastic packaging";
		int wasteQuantity = 5;	//kg of waste unloaded
		
		userBean = new UserBean();
		wasteBean = new WasteUnloadedBean();
		
		userBean.setUsbUsername(username);
		wasteBean.setWbUser(username);
		wasteBean.setWbCenter(center);
		wasteBean.setWbDate(date);
		wasteBean.setWbTime(time);
		wasteBean.setWbWaste(waste);
		wasteBean.setWbWasteQuantity(wasteQuantity);
	}
	
	@Test
	public void testAddEcopoints() {
		int oldPoints, newPoints;
		int pointsObtained = 5*3; //3 is the value_per_kg of plastic packaging

		List<String> info = userController.userInformation(userBean);
		oldPoints = Integer.parseInt(info.get(4));
		int expected = oldPoints + pointsObtained;
		
		wasteController.insertWasteForAnUnload(wasteBean);
		info = userController.userInformation(userBean);
		newPoints = Integer.parseInt(info.get(4));
		
		String message = "expected: "+expected+" new: "+newPoints;
		assertEquals(message, expected, newPoints);
	}
	
	@After
	public void deleteUnload() {
		wasteController.deleteWasteForAnUnload(wasteBean);
	}
}