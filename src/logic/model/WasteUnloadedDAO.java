package logic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class WasteUnloadedDAO {
	
	private static String wudaoUSER = "root";
    private static String wudaoPASS = "root";
    private static String wudaoDBUrl = "jdbc:mysql://127.0.0.1:3306/beecological?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String wudaoDriverClassName = "com.mysql.cj.jdbc.Driver";
   

    public static void registerWasteForUnload(WasteUnloaded wasteUnloaded) {
    	Statement stmt = null;
        Connection conn = null;
        
        try {
            //caricamento driver mysql
        	Class.forName(wudaoDriverClassName);
            
        	//apertura connessione
            conn = DriverManager.getConnection(wudaoDBUrl, wudaoUSER, wudaoPASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
        	String insertStatement = String.format("INSERT INTO beecological.wasteunloaded(user,center,date,time,waste,wasteQuantity)"
        			+ " VALUES ('%s' ,'%s' ,'%s' ,'%s' ,'%s' ,'%s')", wasteUnloaded.getWuUser(), wasteUnloaded.getWuCenter(), wasteUnloaded.getWuDate(), 
        			wasteUnloaded.getWuTime(), wasteUnloaded.getWuWaste(), wasteUnloaded.getWuWasteQuantity());
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
    
    public static List<WasteUnloaded> listOfUnloadRegisteredByCenter(String center) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        List<WasteUnloaded> listUnload = new ArrayList<>();
        
        try {
            //caricamento driver mysql
        	Class.forName(wudaoDriverClassName);
            
        	//apertura connessione
            conn = DriverManager.getConnection(wudaoDBUrl, wudaoUSER, wudaoPASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	
        	String selectStatement = "SELECT * FROM beecological.wasteunloaded JOIN beecological.unload JOIN beecological.waste "
        			+ "WHERE beecological.wasteunloaded.user = beecological.unload.user AND beecological.wasteunloaded.date = beecological.unload.date AND "
        			+ "beecological.wasteunloaded.time = beecological.unload.time AND beecological.wasteunloaded.waste = beecological.waste.name "
        			+ "AND beecological.wasteunloaded.center = beecological.unload.center AND beecological.wasteunloaded.center = '" + center + "';";
        	res = stmt.executeQuery(selectStatement);
        	while (res.next()) {
        		listUnload.add(new WasteUnloaded(res.getString("user"), res.getString("center"), res.getString("date"), 
        				res.getString("time"), res.getString("name"), res.getInt("wasteQuantity"), res.getInt("ecoPoints")));
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
        return listUnload;
    }
    
    public static List<WasteUnloaded> listOfUnloadRegisteredByUser(String user) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        List<WasteUnloaded> listUnload = new ArrayList<>();
        
        try {
            //caricamento driver mysql
        	Class.forName(wudaoDriverClassName);
            
        	//apertura connessione
            conn = DriverManager.getConnection(wudaoDBUrl, wudaoUSER, wudaoPASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
        	
        	String selectStatement = "SELECT * FROM beecological.wasteunloaded JOIN beecological.unload JOIN beecological.waste "
        			+ "WHERE beecological.wasteunloaded.center = beecological.unload.center AND beecological.wasteunloaded.date = beecological.unload.date AND "
        			+ "beecological.wasteunloaded.time = beecological.unload.time AND beecological.wasteunloaded.waste = beecological.waste.name "
        			+ "AND beecological.wasteunloaded.user = beecological.unload.user AND beecological.wasteunloaded.user = '" + user + "';";
        	res = stmt.executeQuery(selectStatement);
        	while (res.next()) {
        		listUnload.add(new WasteUnloaded(res.getString("user"), res.getString("center"), res.getString("date"), 
        				res.getString("time"), res.getString("name"), res.getInt("wasteQuantity"), res.getInt("ecoPoints")));
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
        return listUnload;
    }
    
    public static void deleteWasteUnloaded(WasteUnloaded wasteUnloaded) {
    	Statement stmt = null;
        Connection conn = null;
        
    	try {
            //caricamento driver mysql
        	Class.forName(wudaoDriverClassName);
            
        	//apertura connessione
            conn = DriverManager.getConnection(wudaoDBUrl, wudaoUSER, wudaoPASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	String deleteStatement = String.format("DELETE FROM beecological.wasteunloaded WHERE beecological.wasteunloaded.user = '%s' "
        			+ "AND beecological.wasteunloaded.center = '%s' AND beecological.wasteunloaded.date = '%s' AND beecological.wasteunloaded.time = '%s' "
        			+ "AND beecological.wasteunloaded.waste = '%s';", wasteUnloaded.getWuUser(), wasteUnloaded.getWuCenter(), 
        			wasteUnloaded.getWuDate(), wasteUnloaded.getWuTime(), wasteUnloaded.getWuWaste());
        	stmt.executeUpdate(deleteStatement);

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
    
    public static int wasteForAnUnload(WasteUnloaded wasteUnloaded) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        int count = 1;
        
    	try {
            //caricamento driver mysql
        	Class.forName(wudaoDriverClassName);
            
        	//apertura connessione
            conn = DriverManager.getConnection(wudaoDBUrl, wudaoUSER, wudaoPASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	String selectStatement = "SELECT count(*) FROM beecological.wasteunloaded WHERE beecological.wasteunloaded.user = '"+wasteUnloaded.getWuUser()+"' "
        			+ "AND beecological.wasteunloaded.center = '"+wasteUnloaded.getWuCenter()+"' AND beecological.wasteunloaded.date = '"+wasteUnloaded.getWuDate()+"' "
        					+ "AND beecological.wasteunloaded.time = '"+wasteUnloaded.getWuTime()+"';";
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
}