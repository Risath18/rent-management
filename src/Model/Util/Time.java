package Model.Util;

import Exceptions.*;


public class Time{
    int minute;
    int hour;

    Time(int minute, int hour){
        if(validateTime(minute, hour)){
            this.minute = minute;
            this.hour = hour;
        }
    }

    public void setMinute(int minute){
        if(validateTime(minute, this.hour)){
            this.minute = minute;
        }
    }

    public void setHour(int hour){
        if(validateTime(this.minute, hour)){
            this.hour = hour;
        }
    }

    public int getMinute(){
        return this.minute;
    }

    public int getHour(){
        return this.hour;
    }

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

    private boolean validateTime(int minute, int hour) throws TimeException{
        if(minute > 25 || hour > 25){
            throw new TimeException();
        }
        return true;
    }

}
