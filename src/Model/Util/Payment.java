package Model.Util;

public class Payment {
    private Date listingExpiryDate;

    public Payment(Date listingExpiryDate) {
        this.setListingExpiryDate(listingExpiryDate);
    }

    public Date getListingExpiryDate() {
        return listingExpiryDate;
    }

    public void setListingExpiryDate(Date listingExpiryDate) {
        this.listingExpiryDate = listingExpiryDate;
    }
}
