package com.menu.qrbhojan.exception.custom;

public class InvalidPhoneFormat extends RuntimeException{
    public InvalidPhoneFormat(String message){
        super(message);
    }
}
