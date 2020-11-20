package com.DA.store.controller.exeption;

public class ManufacturerAlreadyExistError extends RuntimeException {
    public ManufacturerAlreadyExistError(String message){
        super(message);
    }
}
