package com.neoteric.fullstack_31082024.service;

import com.neoteric.fullstack_31082024.exception.AccountCreationFailedException;
import com.neoteric.fullstack_31082024.model.Account;

import java.sql.Connection;
import java.sql.Statement;
import java.util.UUID;


public class AccountService {
    public String creatingAccount(Account account) throws  Exception {
        String accountNumber  = null;
        try {
            Connection connection = DBConnection.getConnection();
            Statement st = connection.createStatement();
            accountNumber= UUID.randomUUID().toString();

            String query="insert into bank.account values("+"'"
                    +accountNumber+"'" +","+"'"
                    +account.getName()+ "'" +","
                    +"'"+account.getPan()+"'"+"," +"'" +account.getMobilenumber()
                    +"'" +"," +account.getBalance()+")";

            int  status = st.executeUpdate(query);
            if(status==1){
                System.out.println("  Account is created " + accountNumber);
            }else  {
                throw  new AccountCreationFailedException(" Account creation failed" +account.getPan());
            }

        } catch (Exception e){
            System.out.println("  Exception occured" + e);
            throw  e;
        }
        return accountNumber;
    }
}
