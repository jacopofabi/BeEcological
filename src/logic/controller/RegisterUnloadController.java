package logic.controller;


import java.util.ArrayList;
import java.util.List;

import logic.bean.UnloadBean;
import logic.bean.UserBean;
import logic.bean.WasteUnloadedBean;
import logic.model.Unload;
import logic.model.UnloadDAO;
import logic.model.WasteUnloaded;
import logic.model.WasteUnloadedDAO;

public class RegisterUnloadController {

	public void insertAnUnload(UnloadBean unloadBean) {
		Unload unload = new Unload(unloadBean.getUbUser(), unloadBean.getUbCenter(), unloadBean.getUbDate(), unloadBean.getUbTime());
		UnloadDAO.saveUnload(unload);
	}
	
	public void deleteAnUnload(UnloadBean unloadBean) {
		Unload unload = new Unload(unloadBean.getUbUser(), unloadBean.getUbCenter(), unloadBean.getUbDate(), unloadBean.getUbTime());
		UnloadDAO.deleteUnload(unload);
	}
	
	public void insertWasteForAnUnload(WasteUnloadedBean wasteBean) {
		WasteUnloaded waste = new WasteUnloaded(wasteBean.getWbUser(), wasteBean.getWbCenter(), wasteBean.getWbDate(), 
				wasteBean.getWbTime(), wasteBean.getWbWaste(), wasteBean.getWbWasteQuantity());
		WasteUnloadedDAO.registerWasteForUnload(waste);
	}
	
	public void deleteWasteForAnUnload(WasteUnloadedBean wasteBean) {
		WasteUnloaded waste = new WasteUnloaded(wasteBean.getWbUser(), wasteBean.getWbCenter(), wasteBean.getWbDate(), 
				wasteBean.getWbTime(), wasteBean.getWbWaste(), wasteBean.getWbWasteQuantity());
		WasteUnloadedDAO.deleteWasteUnloaded(waste);
	}
	
	public int numberOfWasteForAnUnload(WasteUnloadedBean wasteBean) {
		WasteUnloaded waste = new WasteUnloaded(wasteBean.getWbUser(), wasteBean.getWbCenter(), wasteBean.getWbDate(), 
				wasteBean.getWbTime(), wasteBean.getWbWaste(), wasteBean.getWbWasteQuantity());
		return WasteUnloadedDAO.wasteForAnUnload(waste);
	}
	
	public List<WasteUnloadedBean> listUnloadBean(List<WasteUnloaded> listOfUnload) {
		List<WasteUnloadedBean> listOfUnloadBean = new ArrayList<>();
		for(WasteUnloaded waste : listOfUnload) {
			WasteUnloadedBean wasteB = new WasteUnloadedBean();
			wasteB.setWbUser(waste.getWuUser());
			wasteB.setWbCenter(waste.getWuCenter());
			wasteB.setWbDate(waste.getWuDate());
			wasteB.setWbTime(waste.getWuTime());
			wasteB.setWbWaste(waste.getWuWaste());
			wasteB.setWbWasteQuantity(waste.getWuWasteQuantity());
			wasteB.setWbEcoPoints(waste.getWuEcoPoints());
			listOfUnloadBean.add(wasteB);
		}
		return listOfUnloadBean;
	}
	
	public List<WasteUnloadedBean> listUnloadByCenter(WasteUnloadedBean wasteBean) {
		List<WasteUnloaded> listOfUnload = WasteUnloadedDAO.listOfUnloadRegisteredByCenter(wasteBean.getWbCenter());
		return listUnloadBean(listOfUnload);
	}
	
	public List<WasteUnloadedBean> listUnloadByUser(UserBean userBean) {
		List<WasteUnloaded> listOfUnload = WasteUnloadedDAO.listOfUnloadRegisteredByUser(userBean.getUsbUsername());
		return listUnloadBean(listOfUnload);
	}
}