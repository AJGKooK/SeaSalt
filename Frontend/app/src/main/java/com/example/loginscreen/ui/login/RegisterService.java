package com.example.loginscreen.ui.login;

public class RegisterService {
    private String username, password, firstName, lastName;

    public RegisterService(){

    }
    public RegisterService(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

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
        this.username = username;
    }
    public void setPass(String password){
        this.password = password;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

}
