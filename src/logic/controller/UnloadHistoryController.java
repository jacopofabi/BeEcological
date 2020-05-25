package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BookingBean;
import logic.bean.UserBean;
import logic.bean.WasteUnloadedBean;
import logic.model.Booking;
import logic.model.BookingDAO;
import logic.model.WasteUnloaded;
import logic.model.WasteUnloadedDAO;

public class UnloadHistoryController {
	List<Booking> listOfBooking;
	
	public List<BookingBean> listBookingBean(List<Booking> listOfBooking) {
		List<BookingBean> listOfBookingBean1 = new ArrayList<>();
		for(Booking book : listOfBooking) {
			BookingBean bookBean = new BookingBean();
			bookBean.setBbId(book.getbId());
			bookBean.setBbUser(book.getbUser());
			bookBean.setBbCenter(book.getbCenter());
			bookBean.setBbDate(book.getbDate());
			bookBean.setBbTime(book.getbTime());
			bookBean.setBbStatus(book.getbStatus());
			listOfBookingBean1.add(bookBean);
		}
		return listOfBookingBean1;
	}
	
	public List<BookingBean> bookingListByUser(BookingBean bookingBean) {
		listOfBooking = BookingDAO.listOfBookingByUser(bookingBean.getBbUser(), bookingBean.getBbStatus());
		return listBookingBean(listOfBooking);
	}
	
	public List<WasteUnloadedBean> listUnloadBean(List<WasteUnloaded> listOfUnload) {
		List<WasteUnloadedBean> listOfUnloadBean1 = new ArrayList<>();
		for(WasteUnloaded waste : listOfUnload) {
			WasteUnloadedBean wasteBean = new WasteUnloadedBean();
			wasteBean.setWbUser(waste.getWuUser());
			wasteBean.setWbCenter(waste.getWuCenter());
			wasteBean.setWbDate(waste.getWuDate());
			wasteBean.setWbTime(waste.getWuTime());
			wasteBean.setWbWaste(waste.getWuWaste());
			wasteBean.setWbWasteQuantity(waste.getWuWasteQuantity());
			wasteBean.setWbEcoPoints(waste.getWuEcoPoints());
			listOfUnloadBean1.add(wasteBean);
		}
		return listOfUnloadBean1;
	}
	
	public List<WasteUnloadedBean> listUnloadByUser(UserBean userBean) {
		List<WasteUnloaded> listOfUnload = WasteUnloadedDAO.listOfUnloadRegisteredByUser(userBean.getUsbUsername());
		return listUnloadBean(listOfUnload);
	}

}