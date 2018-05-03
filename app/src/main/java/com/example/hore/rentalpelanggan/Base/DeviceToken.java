package com.example.hore.rentalpelanggan.Base;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;



public class DeviceToken {
    private static final String TAG = "DeviceToken";
    private static DeviceToken instance;

    //constructor
    private DeviceToken() {

    }

    public static DeviceToken getInstance() {
        if (instance == null) {
            instance = new DeviceToken();
        }
        return instance;
    }

    public void addDeviceToken(DatabaseReference mDatabase, String uid, String token) {
        Map<String, Object> myMap = new HashMap<>();
        myMap.put("deviceToken", token);
        mDatabase.child("pelanggan").child(uid).updateChildren(myMap);
    }
}
