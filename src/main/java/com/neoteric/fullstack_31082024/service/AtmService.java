package com.neoteric.fullstack_31082024.service;

import com.neoteric.fullstack_31082024.exception.AccountNotFoundException;
import com.neoteric.fullstack_31082024.exception.AtmCreationFailedException;
import com.neoteric.fullstack_31082024.model.Account;
import com.neoteric.fullstack_31082024.model.Atm;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class AtmService {
    private Connection connection;

    public AtmService() {
        this.connection = DBConnection.getConnection(); // Get the connection from DBConnection class
    }

    public Atm createAtm(Account account) throws Exception, AccountNotFoundException {
        Atm atm = null;

        try {
            Statement stmt = connection.createStatement();
            String checkAccountQuery = "SELECT * FROM bank.account WHERE accountnumber = '" + account.getAccountnumber() + "'";
            ResultSet rs = stmt.executeQuery(checkAccountQuery);
            if (!rs.next()) {
                throw new AccountNotFoundException("Account Number " + account.getAccountnumber() + " is not found");
            }

            String cardNumber = UUID.randomUUID().toString();
            String pin = String.valueOf((int) (Math.random() * 9000) + 1000);
            String cvv = String.valueOf((int) (Math.random() * 900) + 100);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 3);
            Date expiry = calendar.getTime();

            // Convert java.util.Date to java.sql.Timestamp
            Timestamp expiryTimestamp = new Timestamp(expiry.getTime());

            String insertAtmQuery = "INSERT INTO bank.atm (cardnumber, pin, cvv, expiry, accountnumber) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(insertAtmQuery)) {
                pstmt.setString(1, cardNumber);
                pstmt.setString(2, pin);
                pstmt.setString(3, cvv);
                pstmt.setTimestamp(4, expiryTimestamp);
                pstmt.setString(5, account.getAccountnumber());

                int status = pstmt.executeUpdate();

                if (status == 1) {
                    atm = new Atm(cardNumber, pin,account.getAccountnumber(),cvv, new Date());
                    System.out.println("ATM is Created with card number: " + cardNumber);
                } else {
                    throw new AtmCreationFailedException("ATM creation failed for account number: " + account.getAccountnumber());
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception Occurred: " + e.getMessage());
        } catch (AccountNotFoundException e) {
            System.out.println("Account Not Found: " + e.getMessage());
            throw e;
        }

        return atm;
    }
}
