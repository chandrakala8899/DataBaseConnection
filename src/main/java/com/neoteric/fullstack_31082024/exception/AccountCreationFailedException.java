package com.neoteric.fullstack_31082024.exception;

public class AccountCreationFailedException extends Exception{
    public String message;
    public  AccountCreationFailedException(String message){
         this.message = message;
    }
}
