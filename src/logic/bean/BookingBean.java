package logic.bean;

public class BookingBean {
	private int bbId;
	private String bbUser;
	private String bbCenter;
	private String bbDate;
	private String bbTime;
	private String bbStatus;
	
	
	public int getBbId() {
		return this.bbId;
	}
	
	public void setBbId(int id) {
		this.bbId = id;
	}
	
	public String getBbUser() {
        return this.bbUser;
    }


    public void setBbUser(String user) {
        this.bbUser = user;
    }

    public String getBbCenter() {
        return this.bbCenter;
    }

        
    public void setBbCenter(String center) {
        this.bbCenter = center;
    }
    
    public String getBbDate() {
        return this.bbDate;
    }


    public void setBbDate(String date) {
        this.bbDate = date;
    }
    
    public String getBbTime() {
        return this.bbTime;
    }


    public void setBbTime(String time) {
        this.bbTime = time;
    }
    
    public String getBbStatus() {
        return this.bbStatus;
    }


    public void setBbStatus(String status) {
        this.bbStatus = status;
    }
}