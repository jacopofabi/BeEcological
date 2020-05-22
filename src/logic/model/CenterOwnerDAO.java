package logic.model;

import java.util.List;

import logic.utilities.DaoHelper;


public class CenterOwnerDAO {
	
	private CenterOwnerDAO() {}
	
	//------------------------------------------------------------------------------
    public static boolean checkUsername(String username) {
    	String query = "SELECT count(*) FROM beecological.owner WHERE Username = '" + username + "';";
    	int count = DaoHelper.countStatement(query);
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
    	int count = DaoHelper.countStatement(query);
        if (count == 0) {
        	return false;	//utente immesso non esiste
        }
        else {
        	return true; //count=1 nel db, l'utente matcha una registrazione
        }
    }
    
    
    //------------------------------------------------------------------------------
    public static List<String> ownerInfo(CenterOwner owner) {
    	String query = "SELECT * FROM beecological.owner JOIN beecological.center ON beecological.owner.center = "
    			+ "beecological.center.centerName WHERE beecological.owner.username = '" + owner.getCoUsername() + "';";

    	return DaoHelper.listInfoStatement(query);
    }
    
    
    //------------------------------------------------------------------------------
    public static void deleteOwnerAccount(String username) {
    	String delete = "DELETE FROM beecological.owner WHERE beecological.owner.username = '"+username+"';";
    	DaoHelper.manipulateStatement(delete);
    }
    
}