package logic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BookingDAO {
	
	private static String bdaoUSER = "root";
    private static String bdaoPASS = "root";
    private static String bdaoDBUrl = "jdbc:mysql://127.0.0.1:3306/beecological?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String bdaoDriverClassName = "com.mysql.cj.jdbc.Driver";
    
    public static void makeBooking(Booking booking) {
    	Statement stmt = null;
        Connection conn = null;
        
        try {
            //caricamento driver mysql
        	Class.forName(bdaoDriverClassName);
            
        	//apertura connessione
            conn = DriverManager.getConnection(bdaoDBUrl, bdaoUSER, bdaoPASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            String insertStatement = String.format("INSERT INTO beecological.bookingrequest (user, center, date, time, status)"
            		+ " VALUES ('%s' ,'%s' ,'%s' ,'%s' , '%s')", booking.getbUser(), booking.getbCenter(), booking.getbDate(), 
            		booking.getbTime(), booking.getbStatus());
            stmt.executeUpdate(insertStatement);
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
            try {
				stmt.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
            try {
				conn.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
    
    public static void updateBooking(Booking booking) {
    	Statement stmt = null;
        Connection conn = null;
        
        try {
            //caricamento driver mysql
        	Class.forName(bdaoDriverClassName);
            
        	//apertura connessione
            conn = DriverManager.getConnection(bdaoDBUrl, bdaoUSER, bdaoPASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String updateStatement = String.format("UPDATE beecological.bookingrequest SET beecological.bookingrequest.status = '%s' "
            		+ "WHERE beecological.bookingrequest.user = '%s' AND beecological.bookingrequest.center ='%s' AND "
            		+ "beecological.bookingrequest.date = '%s' AND beecological.bookingrequest.time = '%s';", booking.getbStatus(),
            		booking.getbUser(), booking.getbCenter(), booking.getbDate(), booking.getbTime());
            stmt.executeUpdate(updateStatement);
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
            try {
				stmt.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
            try {
				conn.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
    
    public static int existingBooking(Booking booking) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        int count = 0;
        
        try {
            //caricamento driver mysql
        	Class.forName(bdaoDriverClassName);
            
        	//apertura connessione
            conn = DriverManager.getConnection(bdaoDBUrl, bdaoUSER, bdaoPASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	String selectStatement = "SELECT count(*) FROM beecological.bookingrequest WHERE beecological.bookingrequest.user = '" + booking.getbUser() +"' "
        			+ "and beecological.bookingrequest.center = '" + booking.getbCenter() + "' and beecological.bookingrequest.date = '" + booking.getbDate() + "' "
        					+ "and beecological.bookingrequest.time = '" + booking.getbTime() + "' and beecological.bookingrequest.status = '" + booking.getbStatus() + "';";
        	res = stmt.executeQuery(selectStatement);
        	res.next();
        	count = res.getInt(1);
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
            try {
				stmt.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
            try {
				res.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
            try {
				conn.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
        }
        return count;
    }
    
    public static List<Booking> listOfBookingByCenter(String center, String status) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        List<Booking> listBooking = new ArrayList<>();
        
        try {
            //caricamento driver mysql
        	Class.forName(bdaoDriverClassName);
            
        	//apertura connessione
            conn = DriverManager.getConnection(bdaoDBUrl, bdaoUSER, bdaoPASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
        	String selectStatement = "SELECT * FROM beecological.bookingrequest WHERE beecological.bookingrequest.center = '" + center +"'"
        			+ " and beecological.bookingrequest.status = '" + status + "' ORDER BY beecological.bookingrequest.date;";
        	res = stmt.executeQuery(selectStatement);
        	while (res.next()) {
        		listBooking.add(new Booking(res.getInt("ID"), res.getString("user"), res.getString("center"), 
        				res.getString("date"), res.getString("time"), res.getString("status")));
        	}
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
            try {
				stmt.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
            try {
				res.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
            try {
				conn.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
        }
        return listBooking;
    }
    
    public static List<Booking> listOfBookingByUser(String user, String status) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        List<Booking> listBooking = new ArrayList<>();
        
        try {
            //caricamento driver mysql
        	Class.forName(bdaoDriverClassName);
            
        	//apertura connessione
            conn = DriverManager.getConnection(bdaoDBUrl, bdaoUSER, bdaoPASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	String selectStatement = "SELECT * FROM beecological.bookingrequest WHERE beecological.bookingrequest.user = '" + user +"'"
        			+ " and beecological.bookingrequest.status = '" + status + "' ORDER BY beecological.bookingrequest.date;";
        	res = stmt.executeQuery(selectStatement);
        	while (res.next()) {
        		listBooking.add(new Booking(res.getInt("ID"), res.getString("user"), res.getString("center"), 
        				res.getString("date"), res.getString("time"), res.getString("status")));
        	}
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
            try {
				stmt.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
            try {
				res.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
            try {
				conn.close();
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
        }
        return listBooking;
    }
}