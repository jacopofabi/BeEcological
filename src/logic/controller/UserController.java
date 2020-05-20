package logic.controller;

import java.util.List;

import error.AlreadyUsedUsernameException;
import error.EmptyFieldException;
import error.InexistentUsernameException;
import error.InvalidEmailException;
import error.ShortPasswordException;
import logic.bean.UserBean;
import logic.model.User;
import logic.model.UserDAO;

public class UserController {
	
	public boolean login(UserBean userBean) throws InexistentUsernameException, EmptyFieldException {
		User user = new User(userBean.getUsbUsername());
		user.setUsPassword(userBean.getUsbPassword());
		if(userBean.getUsbUsername().length() == 0 || userBean.getUsbPassword().length() == 0) {
			throw new EmptyFieldException();
		}
		return UserDAO.verifyLogin(user);
	}
	
	public boolean checkRegistration(UserBean userBean) {
		return UserDAO.checkUsername(userBean.getUsbUsername());
	}
	
	public void saveRegistration(UserBean userBean) throws EmptyFieldException, ShortPasswordException, InvalidEmailException, AlreadyUsedUsernameException {
		User user = new User(userBean.getUsbName(), userBean.getUsbSurname(), userBean.getUsbEmail(), userBean.getUsbPhone(), 
				userBean.getUsbUsername(), userBean.getUsbPassword(), userBean.getEcopoints());
		if(userBean.getUsbPassword().length() == 0) {
			throw new EmptyFieldException();
		}
		if(userBean.getUsbPassword().length() < 8) {
			throw new ShortPasswordException();
		}
		if(!userBean.getUsbEmail().contains("@")) {
			throw new InvalidEmailException();
		}
		if(!UserDAO.checkUsername(userBean.getUsbUsername())) {
			throw new AlreadyUsedUsernameException();
		}
 		UserDAO.saveUser(user);
	}
	
	public List<String> userInformation(UserBean userBean) {
		User user = new User(userBean.getUsbUsername());
		return UserDAO.userInfo(user);
	}

	public void updateEcoPoints(UserBean userBean) {
		User user = new User(userBean.getUsbUsername());
		user.setEcopoints(userBean.getEcopoints());
		UserDAO.updateUserEcoPoints(user.getUsUsername(), user.getEcopoints());
	}
	
	public void deleteAccount(UserBean userBean) {
		User user = new User(userBean.getUsbUsername());
		UserDAO.deleteUserAccount(user.getUsUsername());
	}
}
