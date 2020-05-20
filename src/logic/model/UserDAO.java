package logic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.utilities.DaoHelper;


@SuppressWarnings("null")
public class UserDAO {
	
	private UserDAO() {}
	
	//------------------------------------------------------------------------------
    public static boolean checkUsername(String username) {
    	Statement stmt = null;
        Connection conn = null;
    	ResultSet res = null;
        int count = 1;
        
        try {
            DaoHelper.getConnection();
            DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	String query = "SELECT count(*) FROM beecological.User WHERE Username = '" + username + "';";
        	
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
        
        if(count == 1) {
        	return false;	//username gia utilizzato da altro utente, non permetto registrazione(insert del nuovo utente)
        }
        return true;		//username disponibile
    }
        
    
    //------------------------------------------------------------------------------
    public static boolean verifyLogin(User user) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        int count = 0;
        
        try {
	        DaoHelper.getConnection();
	        DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	String query = "SELECT count(*) FROM beecological.User WHERE Username = '" + user.getUsUsername() + 
        			"' and Password = '" + user.getUsPassword() + "';";
        	
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
        
        if (count == 0) {
        	return false;	//utente immesso non esiste
        }
        return true; //count=1 nel db, l'utente matcha una registrazizone
    }


    //------------------------------------------------------------------------------
	public static List<String> userInfo(User user) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        List<String> listInfo = new ArrayList<>();
        
        try {
            DaoHelper.getConnection();
            DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
        	String query = "SELECT * FROM beecological.User WHERE Username = '" + user.getUsUsername() + "';";
        	
        	res = stmt.executeQuery(query);   
    		res.next();
    		listInfo.add(res.getString("Name"));
        	listInfo.add(res.getString("Surname"));
        	listInfo.add(res.getString("Email"));
        	listInfo.add(res.getString("Phone"));
        	listInfo.add(res.getString("EcoPoints"));
        	
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
    public static void saveUser(User user) {
        String insert = String.format("INSERT INTO beecological.User (Username, Password, Name, Surname, Email, "
        		+ "Phone, Ecopoints) VALUES ('%s' ,'%s' ,'%s' ,'%s' ,'%s' ,'%s' , 0)", user.getUsUsername(), user.getUsPassword(), 
        		user.getUsName(), user.getUsSurname(), user.getUsEmail(), user.getUsPhone());
        DaoHelper.manipulateStatement(insert);
    }
    
    
    //------------------------------------------------------------------------------
    public static void deleteUserAccount(String username) {
    	String delete = String.format("DELETE FROM beecological.user WHERE beecological.user.username = '%s';", 
    			username);
    	DaoHelper.manipulateStatement(delete);
    }
    
    
    //------------------------------------------------------------------------------
    public static void updateUserEcoPoints(String username,int ecoPoints) {
    	String update = String.format("UPDATE beecological.user SET beecological.user.ecoPoints = '%s' "
    			+ "WHERE beecological.user.username = '%s';", ecoPoints, username);
        DaoHelper.manipulateStatement(update);
    }
}