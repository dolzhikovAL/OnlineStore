package com.DA.store.controller.exeption;

public class ManufacturerAlreadyExistException extends RuntimeException {
    public ManufacturerAlreadyExistException(String message){
        super(message);
    }
}
