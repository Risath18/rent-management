package Model.Property;

public class Address {
    private String street;
    private CityQuadrant cityQuadrant;
    private int houseNumber;

    public Address(){
        
    }

	public Address(String street, CityQuadrant cityQuadrant, int houseNumber) {
        this.setStreet(street);
        this.setCityQuadrant(cityQuadrant);
        this.setHouseNumber(houseNumber);
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public CityQuadrant getCityQuadrant() {
        return cityQuadrant;
    }

    public void setCityQuadrant(CityQuadrant cityQuadrant) {
        this.cityQuadrant = cityQuadrant;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFormattedAddress() {
		return street + ":" + houseNumber + ":" + cityQuadrant;
    }

    //INCOMPLETE, PARSE DATA
    public static Address makeAddress(String data){
        Address ad = new Address();
        return ad;
    }
    
}
