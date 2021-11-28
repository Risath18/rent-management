package Model;

public class Property {
    public String name;
    public String address;
    public String listing;

    public Property(String name, String address, String listing){
        this.name = name;
        this.address = address;
        this.listing = listing;
    }

    public String getAddress(){
        return this.address;
    }
}
