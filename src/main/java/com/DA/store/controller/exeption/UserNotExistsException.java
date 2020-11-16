package com.DA.store.controller.exeption;

public class UserNotExistsException extends RuntimeException{
    public UserNotExistsException(String message){super(message);}
}
