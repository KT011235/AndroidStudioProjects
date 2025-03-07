package com.example.walmartlist;

import org.json.JSONException;
import org.json.JSONObject;

public class Store {
    protected String name;
    protected String address;
    protected String phone;
    protected String city;

    public Store(JSONObject object) throws JSONException {
        this.name = object.getString("name");
        this.address = object.getString("street_address");
        this.city = object.getString("city");
        this.phone = object.getString("phone");
    }
}
