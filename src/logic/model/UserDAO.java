package logic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.utilities.DaoHelper;


public class UserDAO {
	
	private UserDAO() {}
	
	//------------------------------------------------------------------------------
    public static boolean checkUsername(String username) {
    	String query = "SELECT count(*) FROM beecological.User WHERE Username = '" + username + "';";
    	int count = DaoHelper.countStatement(query);        
        if(count == 1) {
        	return false;	//username gia utilizzato da altro utente, non permetto registrazione(insert del nuovo utente)
        }
        else {
        	return true;	//username disponibile
        }
    }
        
    
    //------------------------------------------------------------------------------
    public static boolean verifyLogin(User user) {
    	String query = "SELECT count(*) FROM beecological.User WHERE Username = '" + user.getUsUsername() + 
    			"' and Password = '" + user.getUsPassword() + "';";
    	int count = DaoHelper.countStatement(query);
        if (count == 0) {
        	return false;	//utente immesso non esiste
        }
        else {
        	return true; 	//count=1 nel db, l'utente matcha una registrazione
        }
    }


    //------------------------------------------------------------------------------
	public static List<String> userInfo(User user) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res = null;
        List<String> listInfo = new ArrayList<>();
        
        try {
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, DaoHelper.StatementMode.READ);
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