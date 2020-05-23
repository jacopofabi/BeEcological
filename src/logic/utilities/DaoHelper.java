package logic.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DaoHelper {
	static String user = "root";
	static String pass = "root";
	static String url = "jdbc:mysql://127.0.0.1:3306/beecological?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String driverClassName = "com.mysql.cj.jdbc.Driver";
	
	static String errorMex = "Exception";
	
	
	//------------------------------------------------------------------------------
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driverClassName);
		return DriverManager.getConnection(url, user, pass);
	}
	
	
	//------------------------------------------------------------------------------
	public static Statement getStatement(Connection conn, StatementMode mode) throws SQLException {
		if(mode == StatementMode.READ) {
			return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} else {
			return conn.createStatement();
		}
	}
	
	
	//------------------------------------------------------------------------------
	public static void manipulateStatement(String str) {
		Statement stmt = null;
		Connection conn = null;
		
		try {
			conn = getConnection();
			stmt = getStatement(conn, StatementMode.READ);
            stmt.executeUpdate(str);  
        }
		catch (Exception e) {
			Logger.getGlobal().log(Level.SEVERE, errorMex);
        }
        finally {
        	close(stmt);
        	close(conn);
        }
	}
	
	
    //------------------------------------------------------------------------------
    public static int countStatement(String query) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        int count = 0;
        
        try {
			conn = DaoHelper.getConnection();
			stmt = DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	res = stmt.executeQuery(query);
        	res.next();
        	count = res.getInt(1);
        }
        catch (Exception e) {
        	Logger.getGlobal().log(Level.SEVERE, errorMex);
        }
        finally {
        	DaoHelper.close(stmt);
        	DaoHelper.close(res);
        	DaoHelper.close(conn);
        }
        return count;
    }
    
    
    //------------------------------------------------------------------------------
	public static List<String> listInfoStatement(String query) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        List<String> listInfo = new ArrayList<>();
        
        try {
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	res = stmt.executeQuery(query);   
    		res.next();
    		if(query.contains("beecological.User")) {
        		listInfo.add(res.getString("Name"));
            	listInfo.add(res.getString("Surname"));
            	listInfo.add(res.getString("Email"));
            	listInfo.add(res.getString("Phone"));
            	listInfo.add(res.getString("EcoPoints"));
    		}
    		else {
        		listInfo.add(res.getString("name"));
        		listInfo.add(res.getString("surname"));
        		listInfo.add(res.getString("email"));
            	listInfo.add(res.getString("phone"));
            	listInfo.add(res.getString("centerName"));
            	listInfo.add(res.getString("centerPhone"));
            	listInfo.add(res.getString("city"));
            	listInfo.add(res.getString("address"));
            	listInfo.add(res.getString("CAP"));
            	listInfo.add(res.getString("num"));
    		}
        }
        catch (Exception e) {
        	Logger.getGlobal().log(Level.SEVERE, errorMex);
        }
        finally {
        	DaoHelper.close(stmt);
        	DaoHelper.close(res);
        	DaoHelper.close(conn);
        }
        return listInfo;
	}
	
	
	//------------------------------------------------------------------------------
	public static void close(Statement stmt) {
		if(stmt != null) {
    		try {
    			stmt.close();
    		} 
    		catch (SQLException e) {
    			Logger.getGlobal().log(Level.SEVERE, errorMex);
    		}
    	}
	}
	
	
	//------------------------------------------------------------------------------
	public static void close(Connection conn) {
		if(conn != null) {
    		try {
    			conn.close();
    		} 
    		catch (SQLException e) {
    			Logger.getGlobal().log(Level.SEVERE, errorMex);
    		}
    	}
	}
	
	
	//------------------------------------------------------------------------------
	public static void close(ResultSet res) {
		if(res != null) {
    		try {
    			res.close();
    		} 
    		catch (SQLException e) {
    			Logger.getGlobal().log(Level.SEVERE, errorMex);
    		}
    	}
	}
	
	
	
	public enum StatementMode {
		READ,
		WRITE;
	}
}
