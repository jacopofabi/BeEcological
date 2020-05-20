package logic.model;

import logic.utilities.DaoHelper;


public class UnloadDAO {

	private UnloadDAO() {}
	
	//------------------------------------------------------------------------------
    public static void saveUnload(Unload unload) {
        String insert = String.format("INSERT INTO beecological.unload (user, center, date, time) VALUES ('%s' ,'%s' ,'%s' ,"
        		+ "'%s')", unload.getuUser(), unload.getuCenter(), unload.getuDate(), unload.getuTime());

        DaoHelper.manipulateStatement(insert);
        
    }
    
    
    //------------------------------------------------------------------------------
    public static void deleteUnload(Unload unload) {
    	String delete = String.format("DELETE FROM beecological.unload WHERE beecological.unload.user = '%s' AND "
    			+ "beecological.unload.center = '%s' AND beecological.unload.date = '%s' AND beecological.unload.time = '%s';", 
    			unload.getuUser(), unload.getuCenter(), unload.getuDate(), unload.getuTime());
    	
    	DaoHelper.manipulateStatement(delete);
    }
}