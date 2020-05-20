package logic.model;

public class Unload {
	private String uUser;
    private String uCenter;
    private String uDate;
    private String uTime;

    public Unload(String user, String center, String date, String time){
    	this.uUser = user;
        this.uCenter = center;
        this.uDate = date;
        this.uTime = time;
    }

    public String getuUser() {
        return this.uUser;
    }
     
    public void setuUser(String user) {
        this.uUser = user;
    }
    
    public String getuCenter() {
        return this.uCenter;
    }


    public void setuCenter(String center) {
        this.uCenter = center;
    }
    
    public String getuDate() {
        return this.uDate;
    }


    public void setuDate(String date) {
        this.uDate = date;
    }
    
    public String getuTime() {
        return this.uTime;
    }


    public void setuTime(String time) {
        this.uTime = time;
    }
}