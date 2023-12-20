package com.example.alerteye;

public class CustomException extends Exception{
    public CustomException(){
        super();
    }
    public CustomException(String message){
        super(message);
    }
}
