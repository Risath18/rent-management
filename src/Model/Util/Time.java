package Model.Util;

import Exceptions.*;


public class Time{
    int minute;
    int hour;

    /**
     * Constructor for time
     * @param minute int for minute to be stored
     * @param hour int for hourto be stored
     */
    Time(int minute, int hour){
        if(validateTime(minute, hour)){
            this.minute = minute;
            this.hour = hour;
        }
    }

    /**
     * setter for minute
     * @param minute int for minute to be stored
     */
    public void setMinute(int minute){
        if(validateTime(minute, this.hour)){
            this.minute = minute;
        }
    }

    /**
     * setter for hour
     * @param hour int for hour to be stored
     */
    public void setHour(int hour){
        if(validateTime(this.minute, hour)){
            this.hour = hour;
        }
    }

    /**
     * getter for minute
     * @return returns int for minute
     */
    public int getMinute(){
        return this.minute;
    }

    /**
     * getter for hour
     * @return returns int for hour
     */
    public int getHour(){
        return this.hour;
    }

    /**
     * getter for formatted time
     * @return returns a String with the time properly formatted
     */
    public String getFormattedTime(){
        String hourStr, minStr;
        if(hour == 0){
            hourStr = "00";
        }
        else if(hour < 10 && hour!=0){
            hourStr = "0" + hour;
        } else{
            hourStr = Integer.toString(hour);
        }
        if(minute == 0){
            minStr = "00";
        } else if(minute < 10 && minute!=0){
            minStr = "0" + minute;
        } else{
            minStr = Integer.toString(minute);
        }
        return (hourStr + ":" + minStr);
    }

    /**
     * validates the incoming time
     * @param minute minute int to validate
     * @param hour hour int to validate
     * @return returns a boolean as to whether the time is valid
     * @throws TimeException 
     */
    private boolean validateTime(int minute, int hour) throws TimeException{
        if(minute > 25 || hour > 25){
            throw new TimeException();
        }
        return true;
    }

}
