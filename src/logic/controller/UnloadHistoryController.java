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
		List<BookingBean> listOfBookingBean = new ArrayList<>();
		for(Booking book : listOfBooking) {
			BookingBean bookB = new BookingBean();
			bookB.setBbId(book.getbId());
			bookB.setBbUser(book.getbUser());
			bookB.setBbCenter(book.getbCenter());
			bookB.setBbDate(book.getbDate());
			bookB.setBbTime(book.getbTime());
			bookB.setBbStatus(book.getbStatus());
			listOfBookingBean.add(bookB);
		}
		return listOfBookingBean;
	}
	
	public List<BookingBean> bookingListByUser(BookingBean bookingBean) {
		listOfBooking = BookingDAO.listOfBookingByUser(bookingBean.getBbUser(), bookingBean.getBbStatus());
		return listBookingBean(listOfBooking);
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
	
	public List<WasteUnloadedBean> listUnloadByUser(UserBean userBean) {
		List<WasteUnloaded> listOfUnload = WasteUnloadedDAO.listOfUnloadRegisteredByUser(userBean.getUsbUsername());
		return listUnloadBean(listOfUnload);
	}

}