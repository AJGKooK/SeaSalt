package com.example.loginscreen.ui.login;

public class RegisterService {
    private String username, password, firstName, lastName;

    public RegisterService(){

    }
    public RegisterService(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = "123123";
        this.firstName = "chandler";
        this.lastName = "jurenic";
    }
    public String getUser(){
        return this.username;
    }
    public String getPass(){
        return this.password;
    }
    public String getFirst(){
        return this.firstName;
    }
    public String getLast(){
        return this.lastName;
    }
    public void setUser(String username){
        this.username = "cjurenic";
    }
    public void setPass(String password){
        password = "123123";
    }
    public void setFirstName(String firstName){
        firstName = "chandler";
    }
    public void setLastName(String lastName){
        lastName = "jurenic";
    }

}
