package com.sine.tapahuevos2;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by ASUS on 20/11/2016.
 */

public class FireApp extends Application{

    @Override
    public void onCreate(){

        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
