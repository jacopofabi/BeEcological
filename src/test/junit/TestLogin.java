package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import error.EmptyFieldException;
import error.InexistentUsernameException;
import logic.bean.UserBean;
import logic.controller.UserController;

public class TestLogin {
	
	@Test
	public void testLogin() {
		String message = "";
		String username = "stormjack"; //correct username
		String pass = "22222222";	//correct password
		boolean result = true;
		
		UserBean userBean = new UserBean();
		userBean.setUsbUsername(username);
		userBean.setUsbPassword(pass);
		
		UserController userController = new UserController();
		try {
			result = userController.login(userBean);
		} catch (EmptyFieldException e) {
			message = "Empty field in credentials";
		} catch (InexistentUsernameException e) {
			message = "Username not valid";
			e.printStackTrace();
		} 
		assertEquals(message, true, result);
	}
}