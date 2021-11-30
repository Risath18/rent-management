package Model.Util;

public class Name {
    private String fName;
    private String mName;
    private String lName;
    public Name(String fName, String mName, String lName) {
        this.setfName(fName);
        this.setmName(mName);
        this.setlName(lName);
    }
    public String getlName() {
        return lName;
    }
    public void setlName(String lName) {
        this.lName = lName;
    }
    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
    public String getfName() {
        return fName;
    }
    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getFormattedName(){
        return fName + ":" + mName + ":" + lName;
    }
}
