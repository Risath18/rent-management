package Model.Util;

public class Report {
    private Period period;
    private int totalHousesListed;
    private int totalHousesRented;
    private int totalActiveListing;

    public Report(Period period, int totalHousesListed, int totalHousesRented, int totalActiveListing) {
        this.setPeriod(period);
        this.setTotalHousesListed(totalHousesListed);
        this.setTotalHousesRented(totalHousesRented);
        this.setTotalActiveListing(totalActiveListing);
    }
    
    public int getTotalActiveListing() {
        return totalActiveListing;
    }
    public void setTotalActiveListing(int totalActiveListing) {
        this.totalActiveListing = totalActiveListing;
    }
    public int getTotalHousesRented() {
        return totalHousesRented;
    }
    public void setTotalHousesRented(int totalHousesRented) {
        this.totalHousesRented = totalHousesRented;
    }
    public int getTotalHousesListed() {
        return totalHousesListed;
    }
    public void setTotalHousesListed(int totalHousesListed) {
        this.totalHousesListed = totalHousesListed;
    }
    public Period getPeriod() {
        return period;
    }
    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getFormattedPeriod(){
        return period + ":" + totalHousesListed + ":" + totalHousesRented  + ":" + totalActiveListing;
    }

    public void setReport(String data){

    }
}
