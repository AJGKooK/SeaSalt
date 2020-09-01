package com.serviceapplication;

public class WebSocketReceiver {
    
    private String name;
    
    public WebSocketReceiver() {
    }

    public WebSocketReceiver(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
