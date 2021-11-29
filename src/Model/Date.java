package Model;

public class Date {
    private String date;
    private Time time;

    Date(String date, Time time){
        this.date = date;
        this.time = time;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public Time getTime(){
        return this.time;
    }

    public void setTime(Time time){
        this.time = time;
    }

}
