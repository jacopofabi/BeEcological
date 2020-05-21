package logic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.utilities.DaoHelper;


@SuppressWarnings("null")
public class WasteUnloadedDAO {

	private WasteUnloadedDAO() {}
	
	//------------------------------------------------------------------------------
    public static void registerWasteForUnload(WasteUnloaded wasteUnloaded) {
    	String insert = String.format("INSERT INTO beecological.wasteunloaded(user,center,date,time,waste,wasteQuantity)"
    			+ " VALUES ('%s' ,'%s' ,'%s' ,'%s' ,'%s' ,'%s')", wasteUnloaded.getWuUser(), wasteUnloaded.getWuCenter(), wasteUnloaded.getWuDate(), 
    			wasteUnloaded.getWuTime(), wasteUnloaded.getWuWaste(), wasteUnloaded.getWuWasteQuantity());
        DaoHelper.manipulateStatement(insert);
    }
    
    
    //------------------------------------------------------------------------------
    public static void deleteWasteUnloaded(WasteUnloaded wasteUnloaded) {
    	String delete = String.format("DELETE FROM beecological.wasteunloaded WHERE beecological.wasteunloaded.user = '%s' "
    			+ "AND beecological.wasteunloaded.center = '%s' AND beecological.wasteunloaded.date = '%s' AND beecological.wasteunloaded.time = '%s' "
    			+ "AND beecological.wasteunloaded.waste = '%s';", wasteUnloaded.getWuUser(), wasteUnloaded.getWuCenter(), 
    			wasteUnloaded.getWuDate(), wasteUnloaded.getWuTime(), wasteUnloaded.getWuWaste());
    	DaoHelper.manipulateStatement(delete);
    }
    
    
    //------------------------------------------------------------------------------
    public static List<WasteUnloaded> listOfUnloadRegistered(String query) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        List<WasteUnloaded> listUnload = new ArrayList<>();
        
        try {
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	res = stmt.executeQuery(query);
        	while (res.next()) {
        		listUnload.add(new WasteUnloaded(res.getString("user"), res.getString("center"), res.getString("date"), 
        				res.getString("time"), res.getString("name"), res.getInt("wasteQuantity"), res.getInt("ecoPoints")));
        	}
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
        	DaoHelper.close(stmt);
        	DaoHelper.close(res);
        	DaoHelper.close(conn);
        }
        return listUnload;
    }
    
    
    //------------------------------------------------------------------------------
	public static List<WasteUnloaded> listOfUnloadRegisteredByCenter(String center) {
    	String query = "SELECT * FROM beecological.wasteunloaded JOIN beecological.unload JOIN beecological.waste "
    			+ "WHERE beecological.wasteunloaded.user = beecological.unload.user AND beecological.wasteunloaded.date = beecological.unload.date AND "
    			+ "beecological.wasteunloaded.time = beecological.unload.time AND beecological.wasteunloaded.waste = beecological.waste.name "
    			+ "AND beecological.wasteunloaded.center = beecological.unload.center AND beecological.wasteunloaded.center = '" + center + "';";
  	
    	return listOfUnloadRegistered(query);
    }
    
    
    //------------------------------------------------------------------------------
    public static List<WasteUnloaded> listOfUnloadRegisteredByUser(String user) {
    	String query = "SELECT * FROM beecological.wasteunloaded JOIN beecological.unload JOIN beecological.waste "
    			+ "WHERE beecological.wasteunloaded.center = beecological.unload.center AND beecological.wasteunloaded.date = beecological.unload.date AND "
    			+ "beecological.wasteunloaded.time = beecological.unload.time AND beecological.wasteunloaded.waste = beecological.waste.name "
    			+ "AND beecological.wasteunloaded.user = beecological.unload.user AND beecological.wasteunloaded.user = '" + user + "';";
    	
    	return listOfUnloadRegistered(query);
    }
    
    
    //------------------------------------------------------------------------------
    public static int wasteForAnUnload(WasteUnloaded wasteUnloaded) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        int count = 1;
        
    	try {
    		conn = DaoHelper.getConnection();
    		stmt = DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	String query = "SELECT count(*) FROM beecological.wasteunloaded WHERE beecological.wasteunloaded.user = '"+wasteUnloaded.getWuUser()+"' "
        			+ "AND beecological.wasteunloaded.center = '"+wasteUnloaded.getWuCenter()+"' AND beecological.wasteunloaded.date = '"+wasteUnloaded.getWuDate()+"' "
        					+ "AND beecological.wasteunloaded.time = '"+wasteUnloaded.getWuTime()+"';";
        	
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
}