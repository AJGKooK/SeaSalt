package com.example.loginscreen.ui.login;

public class Register {
    private String username, password, firstName, lastName;

    RegisterService service;

    public Register(RegisterService service){
        this.service = service;
    }

    public String gettingUsername(){
        return service.getUser();
    }

    public String gettingPassword(){
        return service.getPass();
    }

    public String gettingFirstName(){
        return service.getFirst();
    }

    public String gettingLastName(){
        return service.getLast();
    }
    public void settingUser(String username){
        service.setUser(username);
    }






}
