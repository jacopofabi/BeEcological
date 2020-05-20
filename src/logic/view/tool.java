package logic.view;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class tool {
	static String string = "";
	static String centerName = "";
	
	public static String getString() {
		return string;
	}
	
	public static String getCenterName() {
		return centerName;
	}
	
	public static boolean checkTime(String hour){
	    try {
	        LocalTime.parse(hour);
	        System.out.println("Valid time string: " + hour);
	        return true;
	    }
	    catch (DateTimeParseException | NullPointerException e) {
	        System.out.println("Invalid time string: " + hour);
	        return false;
	    }
	}
	
	public static boolean checkDate(String date) {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		int nowY = Integer.parseInt(today.substring(0,4));
		int nowM = Integer.parseInt(today.substring(5,7));
		int nowD = Integer.parseInt(today.substring(8));
		int selY = Integer.parseInt(date.substring(0,4));
		int selM = Integer.parseInt(date.substring(5,7));
		int selD = Integer.parseInt(date.substring(8));

		if (selY>nowY) {
			return true;
		}
		if (selY == nowY) {
			if (selM > nowM) {
				return true;
			}
			if (selM == nowM) {
				if (selD >= nowD) {
					return true;
				}
			}
		}
		return false;
	}
}
