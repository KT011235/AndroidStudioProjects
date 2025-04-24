package com.example.customerlist2;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {
    protected String name;
    protected String address;
    protected String phone;
    protected List<String> comments;

    public Customer(String name, String address, String phone, List<String> comments){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }




}
