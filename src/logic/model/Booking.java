package logic.model;

public class Booking {
	private int bId;
	private String bUser;
	private String bCenter;
	private String bDate;
	private String bTime;
	private String bStatus;
	
	public Booking(int ID, String user, String center, String date, String time, String status) {
		this.bId = ID;
		this.bUser = user;
		this.bCenter = center;
		this.bDate = date;
		this.bTime = time;
		this.bStatus = status;
	}
	
	public Booking(String user, String center, String date, String time, String status) {
		this.bUser = user;
		this.bCenter = center;
		this.bDate = date;
		this.bTime = time;
		this.bStatus = status;
	}
	
	public int getbId() {
		return this.bId;
	}
	
	public void setbId(int id) {
		this.bId = id;
	}
	
	public String getbUser() {
        return this.bUser;
    }


    public void setbUser(String user) {
        this.bUser = user;
    }

    public String getbCenter() {
        return this.bCenter;
    }

        
    public void setbCenter(String center) {
        this.bCenter = center;
    }
    
    public String getbDate() {
        return this.bDate;
    }


    public void setbDate(String date) {
        this.bDate = date;
    }
    
    public String getbTime() {
        return this.bTime;
    }


    public void setbTime(String time) {
        this.bTime = time;
    }
    
    public String getbStatus() {
        return this.bStatus;
    }


    public void setbStatus(String status) {
        this.bStatus = status;
    }
}