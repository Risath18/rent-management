package com.rent.management.app;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App myApp = new App();
        myApp.createConnection();
    }

    public App(){

    }

    public void createConnection(){
        Connection dbConnect = null;
        String DB_HOST = System.getenv("DB_HOST");
        String DB_PORT = System.getenv("DB_PORT");
        String DB_NAME = System.getenv("DB_NAME");
        String DB_USER = System.getenv("DB_USER");
        String DB_PASS = System.getenv("DB_PASS");

        String DBURL = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
        
        try{
            dbConnect = DriverManager.getConnection(DBURL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
