package Model.Util;
import Exceptions.*;

public class Date {
    private Time time;
    private int month;
    private int day;
    private int year;

    public Date(){}

    public Date(int year, int month, int day, Time time){
        if(validateYear(year))
            setYear(year);
        if(validateMonth(month))
            setMonth(month);
        if(validateDay(day))
            setDay(day);
        setTime(time);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(validateYear(year))
            this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if(validateDay(day))
            this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if(validateMonth(month))
            this.month = month;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getFormattedDate(){
        return day + ":" + month + ":" + year + ":" + time.getFormattedTime();
    }

    private boolean validateYear(int year){
        if(year < 2000 || year > 2023){
            throw new IllegalDateException();
        }
        return true;
    }

    private boolean validateMonth(int month){
        if(month < 1 || month > 12){
            throw new IllegalDateException();
        }
        return true;
    }

    private boolean validateDay(int day)throws IllegalDateException{
        if(day < 1)
            throw new IllegalDateException();
        if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day > 31){
            throw new IllegalDateException();
        } else if((month == 4 || month == 6 || month == 9 || month == 11) && day > 30){
            throw new IllegalDateException();
        } else if(month == 2 && day > 28)
            throw new IllegalDateException();
        return true;
    }
}
