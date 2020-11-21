package com.DA.store.controller.exeption;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(String message){
        super(message);
    }
}
