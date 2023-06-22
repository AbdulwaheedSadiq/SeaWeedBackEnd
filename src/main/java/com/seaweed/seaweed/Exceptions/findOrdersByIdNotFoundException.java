package com.seaweed.seaweed.Exceptions;

public class findOrdersByIdNotFoundException  extends RuntimeException{
    public findOrdersByIdNotFoundException(String message) {
        super(message);
    }
}
