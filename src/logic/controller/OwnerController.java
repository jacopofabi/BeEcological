package logic.controller;

import java.util.List;

import logic.bean.CenterOwnerBean;
import logic.model.CenterOwner;
import logic.model.CenterOwnerDAO;

public class OwnerController {
	
	public boolean login(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getCobUsername());
		owner.setCoPassword(ownerBean.getCobPassword());
		return CenterOwnerDAO.verifyLogin(owner);
	}
	
	public List<String> ownerData(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getCobUsername());
		return CenterOwnerDAO.ownerInfo(owner);
	}
	
	public void deleteAccount(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getCobUsername());
		CenterOwnerDAO.deleteOwnerAccount(owner.getCoUsername());
	}
}