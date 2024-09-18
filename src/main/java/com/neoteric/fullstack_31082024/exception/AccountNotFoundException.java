package com.neoteric.fullstack_31082024.exception;

public class AccountNotFoundException extends Throwable {
    public String message;
    public  AccountNotFoundException(String message){
        this.message = message;
    }
}
