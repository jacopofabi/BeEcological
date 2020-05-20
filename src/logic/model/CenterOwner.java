package logic.model;

public class CenterOwner {
    private String coUsername;
    private String coPassword;
    private String coName;
    private String coSurname;
    private String coEmail;
    private String coPhone;
    private String coCenter;
    
    public CenterOwner(String username){
        this.coUsername = username;
    }
    
    public CenterOwner(String name, String surname, String emailAddress, String phoneNumber, String username, String password,
    		String centerName){
        this.coName = name;
        this.coSurname = surname;
        this.coEmail = emailAddress;
        this.coPhone = phoneNumber;
    	this.coUsername = username;
        this.coPassword = password;
        this.coCenter = centerName;
    }

    public String getCoUsername() {
        return this.coUsername;
    }


    public void setCoUsername(String username) {
        this.coUsername = username;
    }

    public String getCoPassword() {
        return this.coPassword;
    }

        
    public void setCoPassword(String password) {
        this.coPassword = password;
    }
    
    public String getCoName() {
        return this.coName;
    }


    public void setCoName(String name) {
        this.coName = name;
    }
    
    public String getCoSurname() {
        return this.coSurname;
    }


    public void setCoSurname(String surname) {
        this.coSurname = surname;
    }
    
    public String getCoEmail() {
        return this.coEmail;
    }


    public void setCoEmail(String emailAddress) {
        this.coEmail = emailAddress;
    }
    
    public String getCoPhone() {
        return this.coPhone;
    }


    public void setCoPhone(String phoneNumber) {
        this.coPhone = phoneNumber;
    }
    
    public String getCenter() {
        return this.coCenter;
    }


    public void setCenter(String centerName) {
        this.coCenter = centerName;
    }
    
    @Override
    public String toString(){
		return this.coUsername + " \"" + this.coPassword + "\" (" + this.coCenter + ")";    	
    }
}