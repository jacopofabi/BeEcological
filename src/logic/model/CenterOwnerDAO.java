package logic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.utilities.DaoHelper;


@SuppressWarnings("null")
public class CenterOwnerDAO {
	
	private CenterOwnerDAO() {}
	
	//------------------------------------------------------------------------------
	public static int getOwner(String query) {
		Statement stmt = null;
        Connection conn = null;
    	ResultSet res = null;
        int count = 10;
        
        try {
            DaoHelper.getConnection();
            DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
            res = stmt.executeQuery(query);
        	res.next();				//res.next e' la prima riga del risultato della query
        	count = res.getInt(1);	//ottengo la prima colonna del risultato della query

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
    public static boolean checkUsername(String username) {
    	String query = "SELECT count(*) FROM beecological.owner WHERE Username = '" + username + "';";
    	int count = getOwner(query);
        if(count == 1) {
        	return false;	//username gia utilizzato oppure errore non permetto registrazione(insert del nuovo utente)
        }
        else {
        	return true;		//username disponibile
        }
    }
    
    
    //------------------------------------------------------------------------------
    public static boolean verifyLogin(CenterOwner owner) {
    	String query = "SELECT count(*) FROM beecological.Owner WHERE Username = '" + owner.getCoUsername() + 
    			"' and Password = '" + owner.getCoPassword() + "';";
    	int count = getOwner(query);
        if (count == 0) {
        	return false;	//utente immesso non esiste
        }
        else {
        	return true; //count=1 nel db, l'utente matcha una registrazione
        }
    }
    
    
    //------------------------------------------------------------------------------
    public static List<String> ownerInfo(CenterOwner owner) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        List<String> listInfo = new ArrayList<>();
        
        try {
            DaoHelper.getConnection();
            DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	String query = "SELECT * FROM beecological.owner JOIN beecological.center ON beecological.owner.center = "
        			+ "beecological.center.centerName WHERE beecological.owner.username = '" + owner.getCoUsername() + "';";
        	
        	res = stmt.executeQuery(query);
    		res.next();
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
            
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally {
            DaoHelper.close(stmt);
            DaoHelper.close(res);
            DaoHelper.close(conn);
        }
    	return listInfo;
    }
    
    
    //------------------------------------------------------------------------------
    public static void deleteOwnerAccount(String username) {
    	String delete = "DELETE FROM beecological.owner WHERE beecological.owner.username = '"+username+"';";
    	DaoHelper.manipulateStatement(delete);
    }
    
}