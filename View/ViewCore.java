package View;
import java.util.Scanner;  // Import the Scanner class

import javax.lang.model.util.ElementScanner14;

import Controller.*;


public class ViewCore {

    String message;
    Scanner myObj;
    ControllerCore controller;
    String role;

    public static void main(String[] args) {
        ViewCore myCore = new ViewCore("Initializing Program");
        while(true){
            myCore.requestMessage();
        }
    }

    public ViewCore(String message){
        this.message = message;
        this.myObj = new Scanner(System.in);  // Create a Scanner object
        this.controller = new ControllerCore();
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void requestMessage(){
        System.out.println("Enter Message");
        this.message =  myObj.nextLine();

        if(message.compareTo("Landlord")==0){
            controller.setLandlord();
            role = "Landlord";
        } else if(message.compareTo("Manager")==0){
            controller.setManager();
        } else if (message.compareTo("Renter")==0){
            controller.setRenter();
        }

        if(role == "Landlord"){
            if(message.compareTo("New Property")==0){
                controller.createProperty();
                System.out.println(controller.getPropertyAddress());
            }
        }
    }
}
