package Controller;

import java.sql.*;

public class DBCore {
    private final String DBURL;
    private final String USERNAME;
    private final String PASSWORD;
    public Connection dbConnect;
    private ResultSet result;

    /**
     * Constructor for DBCore. Stores the username, password, and database URL
     * @param url String argument for database url
     * @param username String argument for username
     * @param password String argument for password
     */
    public DBCore(String url, String username, String password){
        this.DBURL = url;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    /**
     * creates connection to the database. Will catch an exception if the connection is not successful.
     */
    public void createConnection(){
        try{
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
