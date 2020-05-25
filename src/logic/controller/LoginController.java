package logic.controller;

import error.AlreadyUsedUsernameException;
import error.EmptyFieldException;
import error.InexistentUsernameException;
import error.InvalidEmailException;
import error.ShortPasswordException;
import logic.bean.CenterOwnerBean;
import logic.bean.UserBean;
import logic.model.CenterOwner;
import logic.model.CenterOwnerDAO;
import logic.model.User;
import logic.model.UserDAO;

public class LoginController {
	
	public boolean loginUser(UserBean userBean) throws InexistentUsernameException, EmptyFieldException {
		User user = new User(userBean.getUsbUsername());
		user.setUsPassword(userBean.getUsbPassword());
		if(userBean.getUsbUsername().length() == 0 || userBean.getUsbPassword().length() == 0) {
			throw new EmptyFieldException();
		}
		return UserDAO.verifyLogin(user);
	}
	
	public boolean loginOwner(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getCobUsername());
		owner.setCoPassword(ownerBean.getCobPassword());
		return CenterOwnerDAO.verifyLogin(owner);
	}
	
	public boolean checkRegistrationUser(UserBean userBean) {
		return UserDAO.checkUsername(userBean.getUsbUsername());
	}
	
	public void saveRegistrationUser(UserBean userBean) throws EmptyFieldException, ShortPasswordException, InvalidEmailException, AlreadyUsedUsernameException {
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
}
