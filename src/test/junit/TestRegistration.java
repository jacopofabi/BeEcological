package test.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import error.AlreadyUsedUsernameException;
import error.EmptyFieldException;
import error.InvalidEmailException;
import error.ShortPasswordException;
import logic.bean.UserBean;
import logic.controller.UserController;

public class TestRegistration {
	
	UserBean userBean;
	UserController userController = new UserController();
	boolean result;
	
	@Before
	public void prepareData() {
		String name = "name";
		String surname = "surname";
		String email = "newEmail@gmail.com";	
		String phone = "3333333333";
		String username = "sixpain";
		String password = "newPassword";
		userBean = new UserBean();
		userBean.setUsbUsername(username);
		userBean.setUsbName(name);
		userBean.setUsbSurname(surname);
		userBean.setUsbEmail(email);
		userBean.setUsbPhone(phone);
		userBean.setUsbPassword(password);
	}
	
	@Test
	public void testRegistration() {
		String message = "";
		result = true;
		
		try {
			userController.saveRegistration(userBean);
		} catch (EmptyFieldException e){
			message = "Empty field";
			result = false;
		} catch (ShortPasswordException e) {
			message = "Password too short";
			result = false;
		} catch (InvalidEmailException e) {
			message = "Invalid email";
			result = false;
		} catch (AlreadyUsedUsernameException e) {
			message = "Username already exists";
			result = false;
		}
		assertEquals(message, true, result);
	}
	
	@After
	public void deleteRegister() {
		if(result) {
			userController.deleteAccount(userBean);
		}
	}
}