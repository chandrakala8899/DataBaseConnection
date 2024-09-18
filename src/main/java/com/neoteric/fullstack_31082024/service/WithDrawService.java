package com.neoteric.fullstack_31082024.service;


import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Service
public class WithDrawService {
    private Connection connection;

    public WithDrawService() {
        this.connection = DBConnection.getConnection(); // Get the connection from DBConnection class
    }

    public String withdrawAmount(String accountnumber, double amount) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Check if card number and pin are valid
            String query = "SELECT balance FROM account WHERE accountnumber = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, accountnumber);

            rs = stmt.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");

                if (amount <= currentBalance) {
                    double remainingBalance = currentBalance - amount;

                    // Update the account with the new balance
                    String updateQuery = "UPDATE account SET balance = ? WHERE accountnumber = ?";
                    PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                    updateStmt.setDouble(1, remainingBalance);
                    updateStmt.setString(2, accountnumber);  // Set the accountnumber in the WHERE clause

                    updateStmt.executeUpdate();
                    System.out.println("Withdrawal successful. Remaining balance: " + remainingBalance );

                    return "Withdrawal successful. Remaining balance: " + remainingBalance;

                } else {
                    return "Insufficient balance.";
                }
            } else {
                return " invalid Account number ";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "An error occurred during withdrawal: " + e.getMessage();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                                            // Close the updateStmt as well
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}
