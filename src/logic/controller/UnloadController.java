package logic.controller;

import java.util.List;

import logic.bean.CenterOwnerBean;
import logic.bean.UnloadBean;
import logic.model.CenterOwner;
import logic.model.CenterOwnerDAO;
import logic.model.Unload;
import logic.model.UnloadDAO;

public class UnloadController {
	
	public void insertAnUnload(UnloadBean unloadBean) {
		Unload unload = new Unload(unloadBean.getUbUser(), unloadBean.getUbCenter(), unloadBean.getUbDate(), unloadBean.getUbTime());
		UnloadDAO.saveUnload(unload);
	}
	
	public void deleteAnUnload(UnloadBean unloadBean) {
		Unload unload = new Unload(unloadBean.getUbUser(), unloadBean.getUbCenter(), unloadBean.getUbDate(), unloadBean.getUbTime());
		UnloadDAO.deleteUnload(unload);
	}
	
	public List<String> ownerData(CenterOwnerBean ownerBean) {
		CenterOwner owner = new CenterOwner(ownerBean.getCobUsername());
		return CenterOwnerDAO.ownerInfo(owner);
	}
}