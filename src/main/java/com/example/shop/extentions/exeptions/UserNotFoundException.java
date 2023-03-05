package com.example.shop.extentions.exeptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String text){
        super(text);
    }
}
