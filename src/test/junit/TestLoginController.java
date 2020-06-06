package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import error.EmptyFieldException;
import error.InexistentUsernameException;
import logic.bean.UserBean;
import logic.controller.LoginController;

public class TestLoginController {
	
	// Jacopo Fabi 0243948
	@Test
	public void testLogin() {
		String message = "";
		String username = "user"; //correct username
		String pass = "user";	//correct password
		boolean result = true;
		
		UserBean userBean = new UserBean();
		userBean.setUsbUsername(username);
		userBean.setUsbPassword(pass);
		
		LoginController loginController = new LoginController();
		try {
			result = loginController.loginUser(userBean);
		} catch (EmptyFieldException e) {
			message = "Empty field in credentials";
		} catch (InexistentUsernameException e) {
			message = "Username not valid";
			e.printStackTrace();
		} 
		assertEquals(message, true, result);
	}
}