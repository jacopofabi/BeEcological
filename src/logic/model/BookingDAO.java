package logic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.utilities.DaoHelper;

@SuppressWarnings("null")
public class BookingDAO {    
    
    //------------------------------------------------------------------------------
    public static void makeBooking(Booking booking) {
        String insert = String.format("INSERT INTO beecological.bookingrequest (user, center, date, time, status)"
        		+ " VALUES ('%s' ,'%s' ,'%s' ,'%s' , '%s')", booking.getbUser(), booking.getbCenter(), booking.getbDate(), 
        		booking.getbTime(), booking.getbStatus());
        
        DaoHelper.manipulateStatement(insert);
    }
    
    
    //------------------------------------------------------------------------------
    public static void updateBooking(Booking booking) {
        String update = String.format("UPDATE beecological.bookingrequest SET beecological.bookingrequest.status = '%s' "
        		+ "WHERE beecological.bookingrequest.user = '%s' AND beecological.bookingrequest.center ='%s' AND "
        		+ "beecological.bookingrequest.date = '%s' AND beecological.bookingrequest.time = '%s';", booking.getbStatus(),
        		booking.getbUser(), booking.getbCenter(), booking.getbDate(), booking.getbTime());

        DaoHelper.manipulateStatement(update);
    }
    
    
    //------------------------------------------------------------------------------
    public static int existingBooking(Booking booking) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        int count = 0;
        
        try {
			conn = DaoHelper.getConnection();
			stmt = DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	String query = "SELECT count(*) FROM beecological.bookingrequest WHERE beecological.bookingrequest.user = '" + booking.getbUser() +"' "
        			+ "and beecological.bookingrequest.center = '" + booking.getbCenter() + "' and beecological.bookingrequest.date = '" + booking.getbDate() + "' "
        					+ "and beecological.bookingrequest.time = '" + booking.getbTime() + "' and beecological.bookingrequest.status = '" + booking.getbStatus() + "';";
        	
        	res = stmt.executeQuery(query);
        	res.next();
        	count = res.getInt(1);
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
        	DaoHelper.close(stmt);
        	DaoHelper.close(res);
        	DaoHelper.close(conn);
        }
        return count;
    }
    
    
    //------------------------------------------------------------------------------
    public static List<Booking> listOfBookingByCenter(String center, String status) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        List<Booking> listBooking = new ArrayList<>();
        
        try {
        	DaoHelper.getConnection();
        	DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	String query = "SELECT * FROM beecological.bookingrequest WHERE beecological.bookingrequest.center = '" + center +"'"
        			+ " and beecological.bookingrequest.status = '" + status + "' ORDER BY beecological.bookingrequest.date;";
        	
        	res = stmt.executeQuery(query);
        	while (res.next()) {
        		listBooking.add(new Booking(res.getInt("ID"), res.getString("user"), res.getString("center"), 
        				res.getString("date"), res.getString("time"), res.getString("status")));
        	}
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
        	DaoHelper.close(stmt);
        	DaoHelper.close(res);
        	DaoHelper.close(conn);
        }
        return listBooking;
    }
    
    
    //------------------------------------------------------------------------------
    public static List<Booking> listOfBookingByUser(String user, String status) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        List<Booking> listBooking = new ArrayList<>();
        
        try {
            DaoHelper.getConnection();
            DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	String query = "SELECT * FROM beecological.bookingrequest WHERE beecological.bookingrequest.user = '" + user +"'"
        			+ " and beecological.bookingrequest.status = '" + status + "' ORDER BY beecological.bookingrequest.date;";
        	
        	res = stmt.executeQuery(query);
        	while (res.next()) {
        		listBooking.add(new Booking(res.getInt("ID"), res.getString("user"), res.getString("center"), 
        				res.getString("date"), res.getString("time"), res.getString("status")));
        	}
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
        	DaoHelper.close(stmt);
        	DaoHelper.close(res);
        	DaoHelper.close(conn);
        }
        return listBooking;
    }
}