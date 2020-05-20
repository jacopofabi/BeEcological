package logic.controller;



import java.util.ArrayList;

import error.InexistentUsernameException;
import logic.bean.BookingBean;
import logic.model.Booking;
import logic.model.BookingDAO;
import logic.model.UserDAO;

public class BookingController {
	
	public int verifyBooking(BookingBean bookingBean) {
		Booking booking = new Booking(bookingBean.getBbUser(), bookingBean.getBbCenter(), bookingBean.getBbDate(), 
				bookingBean.getBbTime(), bookingBean.getBbStatus());
		return BookingDAO.existingBooking(booking);
	}
	
	public void insertBooking(BookingBean bookingBean) throws InexistentUsernameException {
		Booking booking = new Booking(bookingBean.getBbUser(), bookingBean.getBbCenter(), bookingBean.getBbDate(), 
				bookingBean.getBbTime(), bookingBean.getBbStatus());
		if(UserDAO.checkUsername(bookingBean.getBbUser())) {
			throw new InexistentUsernameException();
		}
		BookingDAO.makeBooking(booking);
	}
	
	public void modifyBooking(BookingBean bookingBean) {
		Booking booking = new Booking(bookingBean.getBbUser(), bookingBean.getBbCenter(), bookingBean.getBbDate(), 
				bookingBean.getBbTime(), bookingBean.getBbStatus());
		BookingDAO.updateBooking(booking);
	}
	
	public ArrayList<BookingBean> listBookingBean(ArrayList<Booking> listOfBooking) {
		ArrayList<BookingBean> listOfBookingBean = new ArrayList<>();
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
	
	public ArrayList<BookingBean> bookingListByCenter(BookingBean bookingBean) {
		ArrayList<Booking> listOfBooking = BookingDAO.listOfBookingByCenter(bookingBean.getBbCenter(), bookingBean.getBbStatus());
		return listBookingBean(listOfBooking);
	}
	
	public ArrayList<BookingBean> bookingListByUser(BookingBean bookingBean) {
		ArrayList<Booking> listOfBooking = BookingDAO.listOfBookingByUser(bookingBean.getBbUser(), bookingBean.getBbStatus());
		return listBookingBean(listOfBooking);
	}
}