package com.rent.management.app.Controller;

import java.sql.*;

import com.rent.management.app.Exceptions.IllegalQueryException;
import com.rent.management.app.Model.Role.Person;

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

    public int validateLogin(String email, String password) throws IllegalQueryException{
        int accessLevel=0;
        try{
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

    public String findPerson(String email) throws IllegalQueryException{
        String toRet;
        try{
            String query = "SELECT * FROM Person WHERE Email = '" + email + "'";

            Statement stmt =  dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int accessLevel=0;
            String name="";
            
            while(rs.next()){
                name = rs.getString("Name");
                accessLevel = rs.getInt("AccessLevel");
            }
            toRet = email + ":" + name + ":" + accessLevel;

            stmt.close();
            rs.close();

        } catch (SQLException ex){
            System.err.println("Error in 'find person' sql");
            throw new IllegalQueryException();
        }
        return toRet;
    }

    public String findRenter(String email){
        String toRet;
        try{
            String query = "SELECT * FROM Renter WHERE Email = '" + email + "'";
    
            Statement stmt =  dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int notifications = 0;
            String search = "";
                
            while(rs.next()){
                notifications= result.getInt("Notifications_on");
                search = result.getString("SavedSearch_ID");
            }

            toRet = email + ":" + notifications + ":" + search;

            stmt.close();
            rs.close();

        } catch (SQLException ex){
            System.err.println("Error in renter sql");
            throw new IllegalQueryException();
        }
        return toRet;
    }
    
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
            throw new IllegalQueryException();
        }
    }

    public void registerProperty(String pid, String lEmail, String type, int numBed, int numBath, String furnished, String quadrant, String address, int feePaid, String status, String start, String end){
        try{
            String query = "INSERT INTO Property (PID, L_Email, Type, Num_Bed, Num_Bath, Furnished, Quadrant, Address, Fee_Paid, Status, Active_Date, End_Date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = dbConnect.prepareStatement(query);
            stmt.setString(1, pid);
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

    public void registerRenter(String rEmail, int notifications, String searchID){
        try{
            String query = "INSERT INTO Manager(R_Email, Notifications_on, SavedSearch_ID) VALUES (?,?)";
            PreparedStatement stmt = dbConnect.prepareStatement(query);

            stmt.setString(1, rEmail);
            stmt.setInt(2, notifications);
            stmt.setString(3, searchID);

            stmt.executeUpdate();
            stmt.close();

        } catch(SQLException ex){
            System.err.println("Error in renter sql");
            throw new IllegalQueryException();
        }
    }


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
            System.out.println("Error in  manager sql");
            ex.printStackTrace();
        }
    }

    public void close(){
        try{
            result.close();
            dbConnect.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
}