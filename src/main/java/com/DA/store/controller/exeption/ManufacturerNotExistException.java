package com.DA.store.controller.exeption;

public class ManufacturerNotExistException extends RuntimeException {
  public   ManufacturerNotExistException(String message){
        super(message);
    }
}
