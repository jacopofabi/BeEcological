package logic.controller;

import java.util.ArrayList;
import java.util.List;

import error.InexistentUsernameException;
import logic.bean.BookingBean;
import logic.bean.CenterBean;
import logic.bean.CenterOwnerBean;
import logic.model.Booking;
import logic.model.BookingDAO;
import logic.model.Center;
import logic.model.CenterDAO;
import logic.model.CenterOwner;
import logic.model.UserDAO;

public class MakeBookingController {
	Booking booking;
	
	public void insertBooking(BookingBean bookingBean) throws InexistentUsernameException {
		booking = new Booking(bookingBean.getBbUser(), bookingBean.getBbCenter(), bookingBean.getBbDate(), 
				bookingBean.getBbTime(), bookingBean.getBbStatus());
		if(UserDAO.checkUsername(bookingBean.getBbUser())) {
			throw new InexistentUsernameException();
		}
		BookingDAO.makeBooking(booking);
	}
	
	public List<CenterBean> centerList(CenterBean centerBean) {
		List<CenterBean> listOfCenterBean = new ArrayList<>();
		List<Center> listOfCenter = CenterDAO.verifyCenter(centerBean.getCbName());
		for(Center center : listOfCenter) {
			CenterBean centerB = new CenterBean();
			centerB.setCbName(center.getcName());
			centerB.setCbCity(center.getcCity());
			centerB.setCbCap(center.getcCap());
			centerB.setCbAddress(center.getcAddress());
			centerB.setCbNum(center.getcNum());
			centerB.setCbPhone(center.getcPhone());
			listOfCenterBean.add(centerB);
		}
		return listOfCenterBean;
	}
	
	public CenterOwnerBean ownerOfTheSelectedCenter(CenterBean centerBean) {
		CenterOwner owner;
		CenterOwnerBean ownerBean = new CenterOwnerBean();
		Center center = new Center(centerBean.getCbName());
		owner = CenterDAO.ownerOfTheCenter(center);
		ownerBean.setCobCenter(owner.getCenter());
		ownerBean.setCobEmail(owner.getCoEmail());
		ownerBean.setCobName(owner.getCoName());
		ownerBean.setCobPassword(owner.getCoPassword());
		ownerBean.setCobPhone(owner.getCoPhone());
		ownerBean.setCobSurname(owner.getCoSurname());
		ownerBean.setCobUsername(owner.getCoUsername());
		return ownerBean;
	}
}