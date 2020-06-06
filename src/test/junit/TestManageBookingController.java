package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.bean.BookingBean;
import logic.controller.ManageBookingController;

public class TestManageBookingController {
	
	// Jacopo Fabi 0243948
	@Test
	public void testBookingRequest() {
		String message = "Booking not exist";
		String username = "incorrectUser";
		String center = "la vecchia mola";
		String date = "2020/07/15";
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
		
		ManageBookingController control = new ManageBookingController();
		
		count = control.verifyBooking(bookingBean);
		if (count==0) {
			//inexistent booking
			result = false;
		}
		else {
			result = true;
		}
		
		assertEquals(message, true, result);
	}
}