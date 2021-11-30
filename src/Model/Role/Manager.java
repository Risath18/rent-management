package Model.Role;

import Model.Util.*;

public class Manager implements Person{
    private Name name;
    private String email;
    private Report report;

    public Report getReport() {
        return report;
    }

    //potential issue here
    public void setReport(Report report, Manager manager) {
        this.report = report;
    }

    // public boolean changePropertyStatus(){

    // }

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
