package Model.Property;

import Model.Util.*;

public class Period {
    private Date startDate;
    private Date endDate;

    public Period(){}
    
    public Period(Date startDate, Date endDate) {
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getFormattedPeriod(){
        return startDate + ":" + endDate;
    }
}

