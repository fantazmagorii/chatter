package com.example.chatter.exception;


public class IllegalMessageTypeException extends RuntimeException{
    public IllegalMessageTypeException(){
        super("Unknown message type");
    }
}
