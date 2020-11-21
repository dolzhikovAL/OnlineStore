package com.DA.store.controller.exeption;

public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException(String message){
        super(message);
    }
}
