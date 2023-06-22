package com.seaweed.seaweed.Exceptions;

public class findUsersByIdNotFoundException extends RuntimeException{
    public findUsersByIdNotFoundException(String message){
        super(message);
    }
}
