package com.neoteric.fullstack_31082024.exception;

public class AtmCreationFailedException extends  Exception{
    public String message;
    public  AtmCreationFailedException(String message){
        this.message = message;
    }

}
