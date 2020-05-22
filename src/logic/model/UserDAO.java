package logic.model;

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
    	String query = "SELECT * FROM beecological.User WHERE Username = '" + user.getUsUsername() + "';";
    	
    	return DaoHelper.listInfoStatement(query);
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