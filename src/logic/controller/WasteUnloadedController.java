package logic.controller;


import java.util.ArrayList;

import logic.bean.UserBean;
import logic.bean.WasteUnloadedBean;
import logic.model.WasteUnloaded;
import logic.model.WasteUnloadedDAO;

public class WasteUnloadedController {

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
	
	public ArrayList<WasteUnloadedBean> listUnloadBean(ArrayList<WasteUnloaded> listOfUnload) {
		ArrayList<WasteUnloadedBean> listOfUnloadBean = new ArrayList<>();
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
	
	public ArrayList<WasteUnloadedBean> listUnloadByCenter(WasteUnloadedBean wasteBean) {
		ArrayList<WasteUnloaded> listOfUnload = WasteUnloadedDAO.listOfUnloadRegisteredByCenter(wasteBean.getWbCenter());
		return listUnloadBean(listOfUnload);
	}
	
	public ArrayList<WasteUnloadedBean> listUnloadByUser(UserBean userBean) {
		ArrayList<WasteUnloaded> listOfUnload = WasteUnloadedDAO.listOfUnloadRegisteredByUser(userBean.getUsbUsername());
		return listUnloadBean(listOfUnload);
	}
}