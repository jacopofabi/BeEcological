package logic.controller;


import java.util.List;

import logic.bean.CenterOwnerBean;
import logic.bean.UserBean;
import logic.model.CenterOwner;
import logic.model.CenterOwnerDAO;
import logic.model.User;
import logic.model.UserDAO;

public class AccountInformationController {

	public List<String> ownerInformation(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getCobUsername());
		return CenterOwnerDAO.ownerInfo(owner);
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
	
	public void deleteUser(UserBean userBean) {
		User user = new User(userBean.getUsbUsername());
		UserDAO.deleteUserAccount(user.getUsUsername());
	}
	
	public void deleteOwner(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getCobUsername());
		CenterOwnerDAO.deleteOwnerAccount(owner.getCoUsername());
	}
}