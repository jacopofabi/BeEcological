package logic.bean;

public class WasteUnloadedBean {
    private String wbUser;
    private String wbCenter;
    private String wbDate;
    private String wbTime;
    private String wbWaste;
    private int wbWasteQuantity;
    private int wbEcoPoints;

    
    public String getWbUser() {
    	return this.wbUser;
    }
    
    public void setWbUser(String user) {
    	this.wbUser = user;
    }

    public String getWbCenter() {
    	return this.wbCenter;
    }
    
    public void setWbCenter(String center) {
    	this.wbCenter = center;
    }
    
    public String getWbDate() {
    	return this.wbDate;
    }
    
    public void setWbDate(String date) {
    	this.wbDate = date;
    }
    
    public String getWbTime() {
    	return this.wbTime;
    }
    
    public void setWbTime(String time) {
    	this.wbTime = time;
    }
    
    public String getWbWaste() {
        return this.wbWaste;
    }
     
    public void setWbWaste(String waste) {
        this.wbWaste = waste;
    }
    
    public int getWbWasteQuantity() {
        return this.wbWasteQuantity;
    }


    public void setWbWasteQuantity(int wasteQuantity) {
        this.wbWasteQuantity = wasteQuantity;
    }
    
    public int getWbEcoPoints() {
        return this.wbEcoPoints;
    }


    public void setWbEcoPoints(int ecoPoints) {
        this.wbEcoPoints = ecoPoints;
    }
}