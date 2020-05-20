package logic.model;

public class User {
    private String usUsername;
    private String usPassword;
    private String usName;
    private String usSurname;
    private String usEmail;
    private String usPhone;
    private int usEcoPoints;

    public User(String username){
        this.usUsername = username;
    }
    
    public User(String name, String surname, String emailAddress, String phoneNumber, String username, String password, 
    		int ecoPoints){
        this.usName = name;
        this.usSurname = surname;
        this.usEmail = emailAddress;
        this.usPhone = phoneNumber;
    	this.usUsername = username;
        this.usPassword = password;
        this.usEcoPoints = ecoPoints;
    }
    
    public String getUsUsername() {
        return this.usUsername;
    }


    public void setUsUsername(String username) {
        this.usUsername = username;
    }

    public String getUsPassword() {
        return this.usPassword;
    }

        
    public void setUsPassword(String password) {
        this.usPassword = password;
    }
    
    public String getUsName() {
        return this.usName;
    }


    public void setUsName(String name) {
        this.usName = name;
    }
    
    public String getUsSurname() {
        return this.usSurname;
    }


    public void setUsSurname(String surname) {
        this.usSurname = surname;
    }
    
    public String getUsEmail() {
        return this.usEmail;
    }


    public void setUsEmail(String emailAddress) {
        this.usEmail = emailAddress;
    }
    
    public String getUsPhone() {
        return this.usPhone;
    }


    public void setUsPhone(String phoneNumber) {
        this.usPhone = phoneNumber;
    }
    
    public int getEcopoints() {
        return this.usEcoPoints;
    }


    public void setEcopoints(int ecoPoints) {
        this.usEcoPoints = ecoPoints;
    }
    
    @Override
    public String toString(){
		return this.usUsername + " \"" + this.usPassword + "\" (" + this.usEcoPoints + ")";    	
    }

}