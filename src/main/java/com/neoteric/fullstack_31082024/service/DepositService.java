package com.neoteric.fullstack_31082024.service;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Service
public class DepositService {
    private Connection connection;

    public DepositService() {
        this.connection = DBConnection.getConnection(); // Get the connection from DBConnection class
    }

    public String depositAmount(String accountnumber, double amount) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Check if the account number is valid
            String query = "SELECT balance FROM account WHERE accountnumber = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, accountnumber);

            rs = stmt.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");

                // Update the balance by adding the deposit amount
                double newBalance = currentBalance + amount;

                String updateQuery = "UPDATE account SET balance = ? WHERE accountnumber = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                updateStmt.setDouble(1, newBalance);
                updateStmt.setString(2, accountnumber);  // Set the account number in the WHERE clause

                updateStmt.executeUpdate();

                return "Deposit successful. New balance: " + newBalance;
            } else {
                return "Invalid account number.";
            }

        } catch (SQLException e) {
            return "An error occurred during deposit: " + e.getMessage();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
