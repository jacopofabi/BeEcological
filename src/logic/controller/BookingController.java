package logic.controller;



import java.util.ArrayList;
import java.util.List;

import error.InexistentUsernameException;
import logic.bean.BookingBean;
import logic.model.Booking;
import logic.model.BookingDAO;
import logic.model.UserDAO;

public class BookingController {
	Booking booking;
	ArrayList<Booking> listOfBooking;
	
	public int verifyBooking(BookingBean bookingBean) {
		booking = new Booking(bookingBean.getBbUser(), bookingBean.getBbCenter(), bookingBean.getBbDate(), 
				bookingBean.getBbTime(), bookingBean.getBbStatus());
		return BookingDAO.existingBooking(booking);
	}
	
	public void insertBooking(BookingBean bookingBean) throws InexistentUsernameException {
		booking = new Booking(bookingBean.getBbUser(), bookingBean.getBbCenter(), bookingBean.getBbDate(), 
				bookingBean.getBbTime(), bookingBean.getBbStatus());
		if(UserDAO.checkUsername(bookingBean.getBbUser())) {
			throw new InexistentUsernameException();
		}
		BookingDAO.makeBooking(booking);
	}
	
	public void modifyBooking(BookingBean bookingBean) {
		booking = new Booking(bookingBean.getBbUser(), bookingBean.getBbCenter(), bookingBean.getBbDate(), 
				bookingBean.getBbTime(), bookingBean.getBbStatus());
		BookingDAO.updateBooking(booking);
	}
	
	public List<BookingBean> listBookingBean(ArrayList<Booking> listOfBooking) {
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
	
	public List<BookingBean> bookingListByUser(BookingBean bookingBean) {
		listOfBooking = BookingDAO.listOfBookingByUser(bookingBean.getBbUser(), bookingBean.getBbStatus());
		return listBookingBean(listOfBooking);
	}
}