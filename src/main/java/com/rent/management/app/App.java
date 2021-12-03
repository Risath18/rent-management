package com.rent.management.app;

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

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

        Dotenv env = Dotenv.load();
        String DB_HOST = env.get("DB_HOST");
        String DB_PORT = env.get("DB_PORT");
        String DB_NAME = env.get("DB_NAME");
        String DB_USER = env.get("DB_USER");
        String DB_PASS = env.get("DB_PASS");

        String DBURL = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
        
        try{
            dbConnect = DriverManager.getConnection(DBURL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
