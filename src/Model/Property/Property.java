package Model.Property;

public class Property {
    private int propertyId;
    private PropertyType propertyType;
    private int numOfBed;
    private int numOfBath;
    private boolean isFurnished;
    private PropertyStatus propertyStatus;
    private Address address;

    public Property(PropertyType propertyType, int numOfBed, int numOfBath, boolean isFurnished, int propertyId,
            PropertyStatus propertyStatus, Address address) {
        this.setPropertyType(propertyType);
        this.setNumOfBed(numOfBed);
        this.setNumOfBath(numOfBath);
        this.setFurnished(isFurnished);
        this.setPropertyId(propertyId);
        this.setPropertyStatus(propertyStatus);
        this.setAddress(address);
    }

    public String getFormattedProperty(){
        return propertyId + ":" + numOfBed + ":" + numOfBath + ":" + isFurnished + ":" + propertyType + 
            ":" + propertyStatus + ":" + address.getFormattedAddress();
    }

    public void setProperty(String data){
        this.propertyId = Integer.parseInt(data.split("[:]")[0]);
        this.propertyType = PropertyType.fromString(data.split("[:]")[1]);
        this.numOfBed = Integer.parseInt(data.split("[:]")[2]);
        this.numOfBath = Integer.parseInt(data.split("[:]")[3]);
        this.isFurnished = Boolean.parseBoolean(data.split("[:]")[4]);
        this.propertyStatus = PropertyStatus.fromString(data.split("[:]")[5]);
        this.address = Address.makeAddress(data.split("[:]")[6]);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PropertyStatus getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(PropertyStatus propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public boolean isFurnished() {
        return isFurnished;
    }

    public void setFurnished(boolean isFurnished) {
        this.isFurnished = isFurnished;
    }

    public int getNumOfBath() {
        return numOfBath;
    }

    public void setNumOfBath(int numOfBath) {
        this.numOfBath = numOfBath;
    }

    public int getNumOfBed() {
        return numOfBed;
    }

    public void setNumOfBed(int numOfBed) {
        this.numOfBed = numOfBed;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }


}


