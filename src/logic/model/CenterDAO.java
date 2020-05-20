package logic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.utilities.DaoHelper;


@SuppressWarnings("null")
public class CenterDAO {

	private CenterDAO() {}
	
	//------------------------------------------------------------------------------
    public static List<Center> verifyCenter(String name) {
    	Statement stmt = null;
        Connection conn = null;
    	ResultSet res = null;
        List<Center> listCenter = new ArrayList<>();
        
        try {
           DaoHelper.getConnection();
           DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	String query = "SELECT * FROM beecological.center WHERE beecological.center.centerName = '" + name + "' or "
        			+ "beecological.center.city = '" + name + "' or beecological.center.address = '" + name + "';";
        	res = stmt.executeQuery(query);
        	while (res.next()) {
        		listCenter.add(new Center(res.getString("centerName"), res.getString("city"), res.getString("CAP"), 
        				res.getString("address") +" "+ res.getString("num"), res.getString("centerPhone")));
        	}
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
        	DaoHelper.close(stmt);
        	DaoHelper.close(res);
        	DaoHelper.close(conn);
        }
        return listCenter;
    }
    
    
    //------------------------------------------------------------------------------
    public static CenterOwner ownerOfTheCenter(Center center) {
    	Statement stmt = null;
        Connection conn = null;
    	ResultSet res = null;
        CenterOwner owner = null;
        
    	try {
            DaoHelper.getConnection();
            DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	String query = "SELECT * FROM beecological.owner WHERE beecological.owner.center = '" + center.getcName() + "';";
        	
        	res = stmt.executeQuery(query);
        	res.next();
        	owner = new CenterOwner(res.getString("name"), res.getString("surname"), res.getString("email"), res.getString("phone"), 
        			res.getString("username"), res.getString("password"), res.getString("center"));
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
    	
        finally {
        	DaoHelper.close(stmt);
        	DaoHelper.close(res);
        	DaoHelper.close(conn);
        }
        return owner;
    }
}