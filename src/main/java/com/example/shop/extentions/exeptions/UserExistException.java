package com.example.shop.extentions.exeptions;

public class UserExistException extends Exception{
    public UserExistException(String text){
        super(text);
    }
}