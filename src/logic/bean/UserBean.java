package logic.bean;

public class UserBean {
	public static UserBean usbInstance = null;
	
    private String usbUsername;
    private String usbPassword;
    private String usbName;
    private String usbSurname;
    private String usbEmail;
    private String usbPhone;
    private String usbCenter;
    private int usbEcoPoints;
    
    
	public static UserBean getUserInstance(String username) {
		if (UserBean.usbInstance == null) {
			UserBean.usbInstance = new UserBean();
			UserBean.usbInstance.usbUsername = username;
		}
		return usbInstance;
	}
    
    public String getUsbUsername() {
        return this.usbUsername;
    }


    public void setUsbUsername(String username) {
        this.usbUsername = username;
    }

    public String getUsbPassword() {
        return this.usbPassword;
    }

        
    public void setUsbPassword(String password) {
        this.usbPassword = password;
    }
    
    public String getUsbName() {
        return this.usbName;
    }


    public void setUsbName(String name) {
        this.usbName = name;
    }
    
    public String getUsbSurname() {
        return this.usbSurname;
    }


    public void setUsbSurname(String surname) {
        this.usbSurname = surname;
    }
    
    public String getUsbEmail() {
        return this.usbEmail;
    }


    public void setUsbEmail(String emailAddress) {
        this.usbEmail = emailAddress;
    }
    
    public String getUsbPhone() {
        return this.usbPhone;
    }


    public void setUsbPhone(String phoneNumber) {
        this.usbPhone = phoneNumber;
    }
    
    public String getUsbCenter() {
        return this.usbCenter;
    }


    public void setUsbCenter(String center) {
        this.usbCenter = center;
    }
    
    public int getEcopoints() {
        return this.usbEcoPoints;
    }


    public void setEcopoints(int ecoPoints) {
        this.usbEcoPoints = ecoPoints;
    }
}