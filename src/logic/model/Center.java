package logic.model;

public class Center {	
    private String cName;
    private String cPhone;
    private String cCity;
    private String cAddress;
    private String cCap;
    private String cNum;
    
    public Center(String name) {
        this.cName = name;
    }
    
    public Center(String name, String city, String CAP, String address, String centerPhone) {
        this.cName = name;
        this.cPhone = centerPhone;
        this.cCity = city;
        this.cAddress = address;
    	this.cCap = CAP;
    }

    public Center(String name, String city, String CAP, String address, String num, String centerPhone) {
        this.cName = name;
        this.cPhone = centerPhone;
        this.cCity = city;
        this.cAddress = address;
        this.cNum = num;
    	this.cCap = CAP;
    }
    
    public String getcName() {
        return this.cName;
    }


    public void setcName(String name) {
        this.cName = name;
    }

    public String getcPhone() {
        return this.cPhone;
    }

        
    public void setcPhone(String centerPhone) {
        this.cPhone = centerPhone;
    }
    
    public String getcCity() {
        return this.cCity;
    }


    public void setcCity(String city) {
        this.cCity = city;
    }
    
    public String getcAddress() {
        return this.cAddress;
    }


    public void setcAddress(String address) {
        this.cAddress = address;
    }
    
    public String getcCap() {
        return this.cCap;
    }


    public void setcCap(String CAP) {
        this.cCap = CAP;
    }
    
    public String getcNum() {
        return this.cNum;
    }


    public void setcNum(String num) {
        this.cNum = num;
    }
    
    @Override
    public String toString(){
		return this.cName + " \"" + this.cAddress + "\" (" + this.cNum + ")";    	
    }
}