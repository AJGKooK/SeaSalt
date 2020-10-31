package com.example.loginscreen.ui.login;

public class RegisterUser {
    private String username, password, firstName, lastName;

    public RegisterUser(){

    }
    public RegisterUser(String username, String password, String firstName, String lastName){
        username = this.username;
        password = this.password;
        firstName = this.firstName;
        lastName = this.lastName;
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
