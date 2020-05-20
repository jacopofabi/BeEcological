package logic.model;

public class WasteUnloaded {
    private String wuUser;
    private String wuCenter;
    private String wuDate;
    private String wuTime;
    private String wuWaste;
    private int wuWasteQuantity;
    private int wuEcoPoints;

    public WasteUnloaded(String user, String center, String date, String time, String waste, int wasteQuantity){
        this.wuUser = user;
        this.wuCenter = center;
        this.wuDate = date;
        this.wuTime = time;
        this.wuWaste = waste;
        this.wuWasteQuantity = wasteQuantity;
    }
    
    public WasteUnloaded(String user, String center, String date, String time, String waste, int wasteQuantity, int ecoPoints){
        this.wuUser = user;
        this.wuCenter = center;
        this.wuDate = date;
        this.wuTime = time;
        this.wuWaste = waste;
        this.wuWasteQuantity = wasteQuantity;
        this.wuEcoPoints = ecoPoints;
    }
    
    public String getWuUser() {
    	return this.wuUser;
    }
    
    public void setWuUser(String user) {
    	this.wuUser = user;
    }

    public String getWuCenter() {
    	return this.wuCenter;
    }
    
    public void setWuCenter(String center) {
    	this.wuCenter = center;
    }
    
    public String getWuDate() {
    	return this.wuDate;
    }
    
    public void setWuDate(String date) {
    	this.wuDate = date;
    }
    
    public String getWuTime() {
    	return this.wuTime;
    }
    
    public void setWuTime(String time) {
    	this.wuTime = time;
    }
    
    public String getWuWaste() {
        return this.wuWaste;
    }
     
    public void setWuWaste(String waste) {
        this.wuWaste = waste;
    }
    
    public int getWuWasteQuantity() {
        return this.wuWasteQuantity;
    }


    public void setWuWasteQuantity(int wasteQuantity) {
        this.wuWasteQuantity = wasteQuantity;
    }
    
    public int getWuEcoPoints() {
        return this.wuEcoPoints;
    }


    public void setWuEcoPoints(int ecoPoints) {
        this.wuEcoPoints = ecoPoints;
    }
}