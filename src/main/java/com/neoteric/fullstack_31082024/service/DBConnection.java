package com.neoteric.fullstack_31082024.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {


     public  static Connection connection;

    public static Connection getConnection() {


        try {
            if(connection==null) {
                System.out.println("Getting Connection");
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",
                        "root", "Chandu@0210");
            }else {
                System.out.println(" Retuning Existing Connection");
            }

        } catch (Exception e) {
            //swallowing the exception
            System.out.println(" Exception  Occured in getConnection" + e);
        }
        return connection;
    }
}
