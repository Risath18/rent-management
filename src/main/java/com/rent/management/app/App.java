package com.rent.management.app;

import java.sql.*;

import com.rent.management.app.Controller.*;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Application Starting");
        ControllerCore cc = new ControllerCore();
        App myApp = new App();
    }

    public App(){
    }
}
