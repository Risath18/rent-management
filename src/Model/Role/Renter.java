package Model.Role;

import Model.Util.*;

public class Renter implements Person{
    private Date loginDate;
    private boolean notificationsOn;
    private Name name;
    private String email;

    public Date getLoginDate() {
        return loginDate;
    }
    public boolean isNotificationsOn() {
        return notificationsOn;
    }
    public void setNotificationsOn(boolean notificationsOn) {
        this.notificationsOn = notificationsOn;
    }
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Person getPerson(){
        return this;
    }

    public Name getName(){
        return this.name;
    }
    
    public String getEmail(){
        return this.email;
    }

    public void setName(Name name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
