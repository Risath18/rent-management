package com.rent.management.app.Controller;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import com.rent.management.app.Exceptions.IllegalQueryException;
import com.rent.management.app.Model.Role.Person;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

public class DBCore {
    private final String DB_HOST;
    private final String DB_PORT;
    private final String DB_NAME;
    private final String DB_USER;
    private final String DB_PASS;
    private final String DB_URL;

    public Connection dbConnect;
    private ResultSet result;

    /**
     * Constructor for DBCore. Stores the username, password, and database URL
     * @param url String argument for database url
     * @param username String argument for username
     * @param password String argument for password
     */
    public DBCore(){

        //Retrieve ENV Variables
        Dotenv env = Dotenv.load();
        this.DB_HOST = env.get("DB_HOST");
        this.DB_PORT = env.get("DB_PORT");
        this.DB_NAME = env.get("DB_NAME");
        this.DB_USER = env.get("DB_USER");
        this.DB_PASS = env.get("DB_PASS");

        this.DB_URL = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
        createConnection();
    }

    /**
     * creates connection to the database. Will catch an exception if the connection is not successful.
     */
    public void createConnection(){
        try{
            dbConnect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException ex) {
            System.err.println("Error connecting to database");
        }
    }
    /**
     * Validation function for user log in.
     * @param email username input
     * @param password password input
     * @throws IllegalQueryException if the SQL query is invalid
     */
    public int validateLogin(String email, String password) throws IllegalQueryException{
        int accessLevel=0;
        try{
            System.out.println(password);
            String query = "SELECT AccessLevel FROM Person WHERE Email = '" + email + "' AND Password = '" + password + "'";

            Statement stmt =  dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                accessLevel = rs.getInt("AccessLevel");
            }

            stmt.close();
            rs.close();

        } catch (SQLException ex){
            System.err.println("Error during validation");
            throw new IllegalQueryException();
        }
        return accessLevel;
    }

    /**
     * 
     * @param email String argument for the email to be found
     * @return returns a JSONObject
     * @throws IllegalQueryException
     */
    public JSONObject findPerson(String email) throws IllegalQueryException{
        JSONObject obj = new JSONObject();

        try{
            String query = "SELECT * FROM Person WHERE Email = '" + email + "'";

            Statement stmt =  dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

        
            while(rs.next()){
                obj.put("Name", rs.getString("Name"));
                obj.put("AccessLevel", Integer.parseInt(rs.getString("AccessLevel")));
            }
            stmt.close();
            rs.close();

        } catch (SQLException ex){
            System.err.println("Error in 'find person' sql");
            throw new IllegalQueryException();
        }
        return obj;
    }

    /**
     * Finder function for renter.
     * @param obj a JSONObject which provides search criteria for renter
     * @return JSONObject of found renter
     */
    public JSONObject findRenter(JSONObject obj){
        String search = "";
        try{
            String query = "SELECT * FROM Renter WHERE r_email = '" + obj.get("Email").toString() + "'";

            Statement stmt =  dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                obj.put("Notifications_on", rs.getInt("Notifications_on"));
                search = rs.getString("SavedSearch_ID");
            }
    
            stmt.close();
            rs.close();

        } catch (SQLException ex){
            System.err.println("Error in find renter sql");
            throw new IllegalQueryException();
        }

        try{
            String query = "SELECT * FROM SavedSearch WHERE savedsearch_id = '" + search + "'";

            Statement stmt =  dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            while(rs.next()){
                obj.put("search", search);
                obj.put("type", rs.getString("type"));
                obj.put("num_bed", rs.getInt("num_bed"));
                obj.put("num_bath", rs.getInt("num_bath"));
                obj.put("furnished", Boolean.parseBoolean(rs.getString("furnished")));
                System.out.println(rs.getString("quadrant"));
                obj.put("quadrant", rs.getString("quadrant"));
            }


            stmt.close();
            rs.close();

        } catch (SQLException ex){
            System.err.println("Error in find renter sql with searchId");
            throw new IllegalQueryException();
        }
        return obj;
    }

    /**
     * Function to register a persion.
     * @param email person email
     * @param password new person password
     * @param access access level to information
     * @param name person name
     */
    public void registerPerson(String email, String password, int access, String name) throws IllegalQueryException{
        try{
            String query = "INSERT INTO Person (Email, Name, Password, AccessLevel) VALUES (?,?,?,?)";
            PreparedStatement stmt = dbConnect.prepareStatement(query);

            stmt.setString(1, email);
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setInt(4, access);

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException ex){
            System.err.println("Error in register person sql");
            ex.printStackTrace();
            throw new IllegalQueryException();
        }
    }

    /**
    * Generates Property Id based on current MAX value PID in db
    * @return the new property result
    */
    public int generatePropertyId(){
        int result = 0;
        try{
            String query = "SELECT MAX(pid) as max FROM property";
            Statement stmt =  dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                result = Integer.parseInt(rs.getString("max"));
            }

            stmt.close();
            rs.close();
        } 
        catch(SQLException ex){
            System.err.println("Error in generatePropertyId sql");
            ex.printStackTrace();
        }
        return ++result;
    }

    /**
    * Adds Property based on NULL Start & End Date Values
    * @param pid property identification
    * @param lEmail landlord email associated with property
    * @param type property type
    * @param numBed number of bedrooms in the property
    * @param numBath number of bathrooms in the property
    * @param furnished whether or not the property is furnished
    * @param quadrant city quadrant in which the property is located
    * @param address streed address of property
    * @param isPaid fee paid status
    * @param status property status
    * @param start active date start (for property posting)
    * @param end end active date based on fees
    */
    public void registerProperty(int pid, String lEmail, String type, int numBed, int numBath, String furnished, String quadrant, String address, int feePaid, String status, String start, String end){
        try{
            String query = "INSERT INTO Property (PID, L_Email, Type, Num_Bed, Num_Bath, Furnished, Quadrant, Address, Fee_Paid, Status, Active_Date, End_Date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = dbConnect.prepareStatement(query);
            stmt.setInt(1, pid);
            stmt.setString(2, lEmail);
            stmt.setString(3, type);
            stmt.setInt(4, numBed);
            stmt.setInt(5, numBath);
            stmt.setString(6, furnished);
            stmt.setString(7, quadrant);
            stmt.setString(8, address);
            stmt.setInt(9, feePaid);
            stmt.setString(10, status);
            stmt.setString(11, start);
            stmt.setString(12, end);

            stmt.executeUpdate();
            stmt.close();
        } 
        catch(SQLException ex){
            System.err.println("Error in property sql");
            ex.printStackTrace();
        }
    }

    /**
    * Updates property based on PID with the new inputs
    * @param pid property identification
    * @param lEmail landlord email associated with property
    * @param type property type
    * @param numBed number of bedrooms in the property
    * @param numBath number of bathrooms in the property
    * @param furnished whether or not the property is furnished
    * @param quadrant city quadrant in which the property is located
    * @param address streed address of property
    * @param isPaid fee paid status
    * @param status property status
    * @param start active date start (for property posting)
    * @param end end active date based on fees
    */
    public boolean updateProperty(String pid, String type, int numBed, int numBath, String furnished, String quadrant, String address, int feePaid, String status, String start, String end){
        try{
            String query = "UPDATE Property SET Type = ?,  Num_Bed = ?, Num_Bath = ?, Furnished = ?, Quadrant = ?, Address = ?, Fee_Paid = ?, Status = ?, Active_Date = ?, End_Date = ? WHERE PID = '" + pid + "'";
            PreparedStatement stmt = dbConnect.prepareStatement(query);
            stmt.setString(1, type);
            stmt.setInt(2, numBed);
            stmt.setInt(3, numBath);
            stmt.setString(4, furnished);
            stmt.setString(5, quadrant);
            stmt.setString(6, address);
            stmt.setInt(7, feePaid);
            stmt.setString(8, status);
            stmt.setString(9, start);
            stmt.setString(10, end);
        } catch (SQLException ex){
            System.err.println("Error in 'update property' sql");
            throw new IllegalQueryException();
        }
        return true;
    }

    /**
     * getter function to retrieve all properties
     * @return JSONArray containing all properties with each object being an individual property
     */
    public JSONArray getAllProperties() {
        JSONArray arr = new JSONArray(); // array of properties to be returned
        try {
            String query = "SELECT * FROM Property"; // select all properties query

            Statement stmt =  dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            while(rs.next()){
                JSONObject obj = new JSONObject(); // json object to be returned with all properties
                obj.put("pid", rs.getString("pid"));
                obj.put("l_email", rs.getString("l_email"));
                obj.put("type", rs.getString("type"));
                obj.put("num_bed", rs.getString("num_bed"));
                obj.put("num_bath", rs.getString("num_bath"));
                obj.put("furnished", rs.getString("furnished"));
                obj.put("quadrant", rs.getString("quadrant"));
                obj.put("address", rs.getString("address"));
                obj.put("fee_paid", rs.getString("fee_paid"));
                obj.put("status", rs.getString("status"));
                obj.put("active_date", rs.getString("active_date"));
                obj.put("end_date", rs.getString("end_date"));
                arr.add(obj); // adding the object to the json array
            }

            stmt.close();
            rs.close();
        } catch (SQLException ex){
            System.err.println("Error in getting all properties sql");
            ex.printStackTrace();
        }

        return arr;
    }

    /**
     * function to register a renter
     * @param rEmail renter email
     * @param notifications notifications status for saved searches
     * @param searchID id for saved searches
     */
    public void registerRenter(String rEmail, int notifications, String searchID){
        try{
            String query = "INSERT INTO Renter(R_Email, Notifications_on, SavedSearch_ID) VALUES (?,?,?)";
            PreparedStatement stmt = dbConnect.prepareStatement(query);

            stmt.setString(1, rEmail);
            stmt.setInt(2, notifications);
            stmt.setString(3, searchID);

            stmt.executeUpdate();
            stmt.close();

        } catch(SQLException ex){
            System.err.println("Error in renter sql");
            ex.printStackTrace();
            throw new IllegalQueryException();
        }
    }

    /**
     * save a renter search
     * @param searchID id of saved search
     * @param type property type
     * @param numBed number of bedrooms in property
     * @param numBath number of baths in a property
     * @param furnished whether or not the property is furnished
     * @param quadrant the quadrant of the city the property is registered in
     */
    public void saveSearch(String searchID, String type, int numBed, int numBath, String furnished, String quadrant){
        try{
            String query = "INSERT INTO SavedSearch(SavedSearch_ID, Type, Num_Bed, Num_Bath, Furnished, Quadrant) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = dbConnect.prepareStatement(query);

            stmt.setString(1, searchID);
            stmt.setString(2, type);
            stmt.setInt(3, numBed);
            stmt.setInt(4, numBath);
            stmt.setString(5, furnished);
            stmt.setString(6, quadrant);

            stmt.executeUpdate();
            stmt.close();

        } catch(SQLException ex){
            System.out.println("Error in saved search sql");
            ex.printStackTrace();
        }
    }

    /**
     * setter for fee period in a property
     * @param PID property id
     * @param from start time
     * @param to end time
     * @param cost cost of the fee
     */
    public void setFeePeriod(int PID, String from, String to, int cost){
        try{
            String query = "INSERT INTO Period(PID, From_Date, To_Date, Cost) VALUES (?,?,?,?)";
            PreparedStatement stmt = dbConnect.prepareStatement(query);

            stmt.setInt(1, PID);
            stmt.setString(2, from);
            stmt.setString(3, to);
            stmt.setInt(4, cost);

            stmt.executeUpdate();
            stmt.close();

        } catch(SQLException ex){
            System.out.println("Error in set fee period sql");
            ex.printStackTrace();
        }
    }

    /**
     * updating the period of a property fee
     * @param PID property id
     * @param from start time
     * @param to end time
     * @param cost cost of the fee
     */
    public boolean updatePeriod(int PID, String from, String to, int cost){
        try{
            String query = "UPDATE Period SET From_Date = ?, To_Date = ?, Cost = ? WHERE PID = '" + PID + "'";
            PreparedStatement stmt = dbConnect.prepareStatement(query);

            stmt.setString(1, from);
            stmt.setString(2, to);
            stmt.setInt(3, cost);

            stmt.executeUpdate();
            stmt.close();

        } catch(SQLException ex){
            System.out.println("Error in  set fee period sql");
            ex.printStackTrace();
        }
        return true;
    }

    /**
     * paying the fees for a property
     * @param property_id property id for property fees to be paid
     */
    public void payFee(String property_id) {
        try {
            // get fee days
            String period_query = "SELECT * FROM Fees";
            Statement fee_stmt = dbConnect.createStatement();
            ResultSet fee_rs = fee_stmt.executeQuery(period_query);
            int period = 0;
            if (fee_rs.next()) {
                 period = fee_rs.getInt("Days");
            }

            String query = "UPDATE Property SET Fee_Paid = 1, Active_Date = ?, End_Date = ? WHERE PID = " + property_id + " ";

            PreparedStatement stmt = dbConnect.prepareStatement(query);
            
            LocalDateTime date = LocalDateTime.now(); // retrieve current date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("MMM dd, yyyy");
            String formattedDate = date.format(formatter);
            
            stmt.setString(1, formattedDate);
            
            // increment date by period
            date.plusDays(period);
            formattedDate = date.format(formatter);
            stmt.setString(2, formattedDate);

            // submit query
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println("Error in paying fees");
            ex.printStackTrace();
        }
    }

    /**
     * function to search properties
     * @param type property type
     * @param num_bed number of beds in the property
     * @param num_bath number of baths in the property
     * @param furnished whether or not the property is furnished
     * @param quadrant city quadrant where the property is located
     * @return JSONArray of search results with each object being a property
     */
    public JSONArray search(String type, int num_bed, int num_bath, String furnished, String quadrant){
        String query = "SELECT * FROM Property WHERE ('" + type + "'='NULL' or Type='" + type + "') AND (" + num_bed + "=0 or Num_bed=" + num_bed + ") AND (" + num_bath + "=0 or Num_bath=" + num_bath + ") AND ('" + furnished + "'='NULL' or Furnished='" + furnished + "') AND ('" + quadrant + "'='NULL' or Quadrant='" + quadrant + "')";
        JSONArray arr = new JSONArray ();

        try {
            Statement stmt = dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                JSONObject obj = new JSONObject ();
                obj.put("pid", Integer.toString(rs.getInt("PID")));
                obj.put("l_email", rs.getString("L_Email"));
                obj.put("type", rs.getString("Type"));
                obj.put("num_bed", Integer.toString(rs.getInt("Num_Bed")));
                obj.put("num_bath", Integer.toString(rs.getInt("Num_Bath")));
                obj.put("furnished",rs.getString("Furnished"));
                obj.put("quadrant", rs.getString("Quadrant"));
                obj.put("address", rs.getString("Address"));
                obj.put("fee_paid", rs.getString("Fee_Paid"));
                obj.put("status", rs.getString("Status"));
                obj.put("active_date", rs.getString("Active_Date"));
                obj.put("end_date", rs.getString("End_Date"));
                
                arr.add(obj);
            }
        } catch (SQLException ex) {
            System.err.println("Error in retrieving landlord properties via search.");
            ex.printStackTrace();
        }
        return arr;
    }

    /**
     * function to change the notification status to on or off based on current state
     * @param email renter email to change the notification status of
     */
    public void changeNotificationStatus(String email){
        String query = "SELECT * FROM Renter WHERE R_Email = '" + email + "'";
        int notif=-1;
        try{
            Statement stmt =  dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                notif = rs.getInt("Notifications_on");
            }

            if(notif < 1)
                notif = 1;
            else
                notif = 0;

            query = "UPDATE Renter SET Notifications_on = '" + notif + "' WHERE R_Email = '" + email + "'";
            
            rs = stmt.executeQuery(query);
        
            stmt.close();
            rs.close();

        } catch(SQLException e){
            System.out.println("Notification status error");
        }
    }

    /**
     * saving the search of a renter
     * @param type type of property
     * @param numBed number of beds in property
     * @param numBath number of baths on the property
     * @param furnished whether or not a property is furnished
     * @param quadrant city quadrant
     */
    public String saveSearchCriteria(String type, int numBed, int numBath, String furnished, String quadrant){
        String query = "INSERT INTO SavedSearch (SavedSearch_ID, Type, Num_Bed, Num_Bath, Furnished, Quadrant) VALUES (?,?,?,?,?,?)";
        String searchID ="";
        try{
            PreparedStatement stmt = dbConnect.prepareStatement(query);

            searchID = UUID.randomUUID().toString();
            searchID = searchID.substring(0,5);
            System.out.println(searchID);
            stmt.setString(1, searchID);
            stmt.setString(2, type);
            stmt.setInt(3, numBed);
            stmt.setInt(4, numBath);
            stmt.setString(5, furnished);
            stmt.setString(6, quadrant);

            stmt.executeUpdate();
            stmt.close();

        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error inside save search criteria");
        }
        return searchID;
    }

    /**
     * get the search results from a renter email
     * @param renter_email renter email to check  search results
     */
    public void checkSearchResults(String renter_email) {
        String searchID = "";
        // select appropriate renter
        try {
            String query = "SELECT * FROM Renter WHERE R_Email = " + renter_email + ";";
            Statement statement = dbConnect.createStatement();
            ResultSet set = statement.executeQuery(query);

            int notifications = 0;
            if (set.next()) {
                notifications = set.getInt("Notifications_on");
                searchID = set.getString("SavedSearch_ID");
            }

            // check for notifications turned on
            if (notifications == 0) {
                //  return null if notifications are turned off
                return; 
            }
        } catch (SQLException ex) {
            System.out.println("Error in checking notification search results");
            ex.printStackTrace();
        }
            //  else continue to find search information
        try {
            // use saved search id to find saved search
            String query = "Select * FROM SavedSearch WHERE SavedSearch_ID = '" + searchID + "''";
            Statement statement = dbConnect.createStatement();
            ResultSet rs = statement.executeQuery(query);

        // turn search into a JSON Object
            JSONObject obj = new JSONObject();
            if (rs.next()) {
                obj.put("type", rs.getString("Type").toString());
                obj.put("num_bed", Integer.parseInt(rs.getString("Num_Bed").toString()));
                obj.put("num_bath", Integer.parseInt(rs.getString("num_bath").toString()));
                obj.put("furnished", rs.getString("Furnished").toString());
                obj.put("quadrant", rs.getString("Quadrant").toString());
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in checking notification search results");
            ex.printStackTrace();
        }
    }

    public JSONArray rentersWithSearch(String type, int num_bed, int num_bath, String furnished, String quadrant){
        JSONArray arr = new JSONArray();
        ArrayList<String> savedIds = new ArrayList<>();
        System.out.println("HEYadadd");
        try{
            String query = "SELECT * FROM SavedSearch WHERE ('" + type + "'='NULL' or type='" + type + "') OR (" + num_bed + "=0 or num_bed=" + num_bed + ") OR (" + num_bath + "=0 or num_bath=" + num_bath + ") OR ('" + furnished + "'='NULL' or furnished='" + furnished + "') OR ('" + quadrant + "'='NULL' or quadrant='" + quadrant + "')";
            Statement stmt = dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                savedIds.add(rs.getString("savedsearch_id"));
            }

            stmt.close();
            rs.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }

        //Get all renters with that search ID
        try{
            for(int i =0; i<savedIds.size(); i++){
                String query = "SELECT * FROM RENTER WHERE savedsearch_id='" + savedIds.get(i) +"'";
                Statement stmt = dbConnect.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while(rs.next()) {
                    JSONObject obj = new JSONObject();
                    obj.put("email", rs.getString("r_email"));
                    arr.add(obj);
                }

                stmt.close();
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return arr;
    }

    /**
     * change the fee period
     * @param days number of days for the e
     */
    public void changeFeePeriod(int days){
        try {
            String query = "UPDATE Fees SET Days = ?";

            PreparedStatement stmt = dbConnect.prepareStatement(query);
            stmt.setInt(1, days); // set new days
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error in setting fees period");
            ex.printStackTrace();
        }
    }

    /**
     * function to change the fee amount
     * @param fee int for new fee price
     */
    public void changeFeeAmount(int fee){
        try {
            String query = "UPDATE Fees SET Price = ?";

            PreparedStatement stmt = dbConnect.prepareStatement(query);
            stmt.setInt(1, fee); // set new days
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error in setting price");
            ex.printStackTrace();
        }
    }

    /**
     * changes the status of a listing
     * @param pid String for property ID
     * @param newStatus String for the new status
     */
    public void changeListingStatus(String pid, String newStatus){
        String query = "UPDATE Property SET Status = '" + newStatus + "' WHERE PID = '" + pid + "'";
        try{
            System.out.println(newStatus);
            PreparedStatement stmt =  dbConnect.prepareStatement(query);
            stmt.executeUpdate();
            stmt.close();

        } catch(SQLException e){
            System.out.println("listing status error");
            e.printStackTrace();
        }
    } 

    /**
     * getter for fee table in SQL
     * @return JSONObject with the fee cost and number of days valid
     */
    public JSONObject getFormattedFees(){
        JSONObject obj = new JSONObject();
        try{
            String query = "SELECT * FROM Fees";
    
            Statement stmt =  dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
    
            while(rs.next()){
                System.out.println( rs.getInt("Days"));
                obj.put("Days", rs.getInt("Days"));
                obj.put("price", rs.getInt("price"));
            }
        
            stmt.close();
            rs.close();
    
        } catch (SQLException ex){
            System.err.println("Error in fee retrieval sql");
            ex.printStackTrace();
        }
        return obj;
    }

    /**
     * getter for landlord's properties
     * @param email String for the landlord who we want to retrieve their properties
     * @return JSONArray of properties
     */
    public JSONArray getLandlordProperties (String email) {
        JSONArray arr = new JSONArray ();

        try {
            String query = "SELECT * FROM Property WHERE L_Email = '" + email + "'";
            Statement stmt = dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("pid", Integer.toString(rs.getInt("PID")));
                obj.put("l_email", rs.getString("L_Email"));
                obj.put("type", rs.getString("Type"));
                obj.put("num_bed", Integer.toString(rs.getInt("Num_Bed")));
                obj.put("num_bath", Integer.toString(rs.getInt("Num_Bath")));
                obj.put("furnished",rs.getString("Furnished"));
                obj.put("quadrant", rs.getString("Quadrant"));
                obj.put("address", rs.getString("Address"));
                obj.put("fee_paid", rs.getString("Fee_Paid"));
                obj.put("status", rs.getString("Status"));
                obj.put("active_date", rs.getString("Active_Date"));
                obj.put("end_date", rs.getString("End_Date"));
                
                arr.add(obj);
            }
        } catch (SQLException ex) {
            System.err.println("Error in retrieving landlord properties.");
            ex.printStackTrace();
        }
        return arr;
    }

    /**
     * getter for landlord name matching an email
     * @param email String for landlord's email
     * @return String for landlord's name
     */
    public String getLandlordName (String email) {
        String name = "";

        try {
            String query = "SELECT Name FROM Person WHERE Email = '" + email + "'";

            Statement stmt = dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                name = rs.getString("Name");
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error in retrieving landlord name.");
            e.printStackTrace();
        }

        return name;
    }

    /**
     * updates property's end date to the date it was rented
     * @param pid String for property ID
     */
    public void updateDateRented(String pid){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String query = "UPDATE Property SET End_Date = '" + LocalDate.now().format(formatter).toString() + "' WHERE PID = '" + pid + "'";
            PreparedStatement stmt = dbConnect.prepareStatement(query);

            System.out.println(LocalDate.now().format(formatter));
            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException ex){
            System.err.println("Error in 'update date rented' sql");
            throw new IllegalQueryException();
        }
    }

    public void updateRenterSearch(String email, String searchID){
        try{
            String query = "UPDATE RENTER SET savedsearch_id = '" + searchID +"' WHERE r_email='" + email + "'";
            PreparedStatement stmt = dbConnect.prepareStatement(query);
            stmt.executeUpdate();
            stmt.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * getter for all people in database
     * @return JSONArray of all people
     */
    public JSONArray getAllPeople(){
        JSONArray arr = new JSONArray(); // array of properties to be returned
        try {
            String query = "SELECT * FROM Person"; // select all properties query

            Statement stmt =  dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            while(rs.next()){
                JSONObject obj = new JSONObject(); // json object to be returned with all properties
                obj.put("email", rs.getString("email"));
                obj.put("name", rs.getString("name"));
                obj.put("access_level", rs.getString("accessLevel"));
                arr.add(obj); // adding the object to the json array
            }

            stmt.close();
            rs.close();
        } catch (SQLException ex){
            System.err.println("Error in getting all persons sql");
            ex.printStackTrace();
        }
        return arr;
    }

    /**
     * closes connection to database
     */
    public void close(){
        try{
            result.close();
            dbConnect.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
}