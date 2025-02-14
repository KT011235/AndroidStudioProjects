package com.example.listviewwithimages2;

public class User {
    private String name;
    private String phone;
    private String imageUrl;

    public User(String name, String phone, String imageUrl) {
        this.name = name;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
    public String getImageUrl(){
        return imageUrl;
    }
}