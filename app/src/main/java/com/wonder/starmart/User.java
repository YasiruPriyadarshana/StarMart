package com.wonder.starmart;

public class User {
    private String name;
    private String decription;

    public User(String name,String description){
        this.name=name;
        this.decription=description;
    }
    public String getName(){
        return name;
    }
    public String getDecription(){
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
    public void setName(String name) {
        this.name = name;
    }
}
