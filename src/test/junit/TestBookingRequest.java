package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import error.InexistentUsernameException;
import logic.bean.BookingBean;
import logic.controller.BookingController;

public class TestBookingRequest {
	
	@Test
	public void testBooking() {
		String message = "";
		String username = "incorrectUser";
		String center = "la vecchia mola";
		String date = "2020/03/15";
		String time = "11:00";
		String status = "W"; //request booking (in wait to be accepted)
		int count;
		boolean result;
		
		BookingBean bookingBean = new BookingBean();
		bookingBean.setBbUser(username);
		bookingBean.setBbCenter(center);
		bookingBean.setBbDate(date);
		bookingBean.setBbTime(time);
		bookingBean.setBbStatus(status);
		
		BookingController bookingController = new BookingController();
		
		try {
			bookingController.insertBooking(bookingBean);
		} catch (InexistentUsernameException e) {
			message = "Username not valid";
		}
		
		count = bookingController.verifyBooking(bookingBean);
		if (count==0) {
			//book invalid
			result = false;
		}
		else {
			//book insert
			result = true;
		}
		
		assertEquals(message, true, result);
	}
}