package logic.bean;

public class CenterOwnerBean {
	private static CenterOwnerBean cobInstance = null;
	
    private String cobUsername;
    private String cobPassword;
    private String cobName;
    private String cobSurname;
    private String cobEmail;
    private String cobPhone;
    private String cobCenter;
    
    
	public static CenterOwnerBean getOwnerInstance(String username) {
		if (CenterOwnerBean.cobInstance == null) {
			CenterOwnerBean.cobInstance = new CenterOwnerBean();
			CenterOwnerBean.cobInstance.cobUsername = username;
		}
		return cobInstance;
	}
	
	public static CenterOwnerBean getInstance() {
		return CenterOwnerBean.cobInstance;
	}
	
	public static void setInstance(CenterOwnerBean centerOwnerBean) {
		CenterOwnerBean.cobInstance = centerOwnerBean;
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