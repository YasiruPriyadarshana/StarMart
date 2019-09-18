package com.wonder.starmart;



public class User {
    private String name;
    private String decription;
    private String ratingBar ;
    private String image;

    public User(String name,String description,String rating,String image){
        this.name=name;
        this.decription=description;
        this.ratingBar=rating;
        this.image=image;
    }
    public String getName(){
        return name;
    }
    public String getDecription(){
        return decription;
    }
    public String getRatingBar(){
        return ratingBar;
    }
    public String getImage(){
        return image;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRatingBar(String rating) {
        this.ratingBar=rating;
    }
    public void setImage(String image) {
        this.image=image;
    }
}
