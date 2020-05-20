package logic.bean;

public class CenterOwnerBean {
	public static CenterOwnerBean instance = null;
	
    private String cobUsername;
    private String cobPassword;
    private String cobName;
    private String cobSurname;
    private String cobEmail;
    private String cobPhone;
    private String cobCenter;
    
    
	public static CenterOwnerBean getOwnerInstance(String username) {
		if (CenterOwnerBean.instance == null) {
			CenterOwnerBean.instance = new CenterOwnerBean();
			CenterOwnerBean.instance.cobUsername = username;
		}
		return instance;
	}

    public String getCobUsername() {
        return this.cobUsername;
    }


    public void setCobUsername(String username) {
        this.cobUsername = username;
    }

    public String getCobPassword() {
        return this.cobPassword;
    }

        
    public void setCobPassword(String password) {
        this.cobPassword = password;
    }
    
    public String getCobName() {
        return this.cobName;
    }


    public void setCobName(String name) {
        this.cobName = name;
    }
    
    public String getCobSurname() {
        return this.cobSurname;
    }


    public void setCobSurname(String surname) {
        this.cobSurname = surname;
    }
    
    public String getCobEmail() {
        return this.cobEmail;
    }


    public void setCobEmail(String emailAddress) {
        this.cobEmail = emailAddress;
    }
    
    public String getCobPhone() {
        return this.cobPhone;
    }


    public void setCobPhone(String phoneNumber) {
        this.cobPhone = phoneNumber;
    }
    
    public String getCobCenter() {
        return this.cobCenter;
    }


    public void setCobCenter(String centerName) {
        this.cobCenter = centerName;
    }
}