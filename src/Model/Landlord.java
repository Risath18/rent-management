package Model;

public class Landlord implements Person{
    String name;
    int age;

    public Landlord(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void printString(){
        System.out.println("Landlord name is " + name + " and age is " + age);
    }

    public void createProperty(String name, String address, String listing){
        new Property(name, address, listing);
        System.out.println("Property created");
    }
}
