package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BookingBean;
import logic.model.Booking;
import logic.model.BookingDAO;

public class ManageBookingController {
	Booking booking;
	List<Booking> listOfBooking;
	
	public int verifyBooking(BookingBean bookingBean) {
		booking = new Booking(bookingBean.getBbUser(), bookingBean.getBbCenter(), bookingBean.getBbDate(), 
				bookingBean.getBbTime(), bookingBean.getBbStatus());
		return BookingDAO.existingBooking(booking);
	}
	
	public void modifyBooking(BookingBean bookingBean) {
		booking = new Booking(bookingBean.getBbUser(), bookingBean.getBbCenter(), bookingBean.getBbDate(), 
				bookingBean.getBbTime(), bookingBean.getBbStatus());
		BookingDAO.updateBooking(booking);
	}
	
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
	
	public List<BookingBean> bookingListByCenter(BookingBean bookingBean) {
		listOfBooking = BookingDAO.listOfBookingByCenter(bookingBean.getBbCenter(), bookingBean.getBbStatus());
		return listBookingBean(listOfBooking);
	}
}