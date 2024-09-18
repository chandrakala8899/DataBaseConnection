package com.neoteric.fullstack_31082024.service;

import com.neoteric.fullstack_31082024.model.Login;

import java.sql.*;

public class LoginService {


    private Connection connection;

    public LoginService() {
        this.connection = DBConnection.getConnection(); // Get the connection from DBConnection class
    }
    public  String  login(Login login) {
        try{

            String query = "SELECT COUNT(*) FROM bank.login WHERE cardNumber = ? AND pin = ?";

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1,login.getCarNumber());
            stmt.setString(2,login.getPin());
             ResultSet rs = stmt.executeQuery();
             if(rs.next()){
               return "login Sucessful";
             }else {
                return " login failed";
             }


        }catch (SQLException e){
            System.out.println("  " +e );
            return " failed";
        }

    }

}
