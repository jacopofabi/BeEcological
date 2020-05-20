package logic.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DaoHelper {
	static String user = "root";
	static String pass = "root";
	static String url = "jdbc:mysql://127.0.0.1:3306/beecological?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";;
	static String driverClassName = "com.mysql.cj.jdbc.Driver";
	
	
	//------------------------------------------------------------------------------
	public static void manipulateStatement(String str) {
		Statement stmt = null;
		Connection conn = null;
		
		try {
			conn = getConnection();
			stmt = getStatement(conn, StatementMode.READ);
            stmt.executeUpdate(str);
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
        	close(stmt);
        	close(conn);
        }
	}
	
	
	//------------------------------------------------------------------------------
	public static ResultSet selectStatement(String query) {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = getStatement(conn, StatementMode.READ);
            rs = stmt.executeQuery(query);
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
        	close(stmt);
        	close(conn);
        }
		return rs;
	}
	
	
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
	public static void close(Statement stmt) {
		if(stmt != null) {
    		try {
    			stmt.close();
    		} 
    		catch (SQLException e) {
    			e.printStackTrace();
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
    			e.printStackTrace();
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
    			e.printStackTrace();
    		}
    	}
	}
	
	
	
	public enum StatementMode {
		READ,
		WRITE;
	}
}
