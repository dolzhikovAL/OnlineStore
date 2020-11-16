package com.DA.store.controller.exeption;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message){super(message);}
}
