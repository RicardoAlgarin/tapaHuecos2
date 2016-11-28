package com.sine.tapahuevos2;

import android.app.Application;
import android.content.Intent;

import com.firebase.client.Firebase;
import com.google.firebase.database.FirebaseDatabase;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.OkHttpDownloader;

import okhttp3.OkHttpClient;

/**
 * Created by ASUS on 20/11/2016.
 */

public class FireApp extends Application{

    @Override
    public void onCreate(){

        super.onCreate();
        Firebase.setAndroidContext(this);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

      //  Picasso.Builder builder = new Picasso.Builder(this);
      //  builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));

     //   OkHttpClient client = new OkHttpClient();

        // Picasso built = new Picasso.Builder(this)
          //      .downloader(new OkHttp3Downloader(client)).build();

     //   built.setIndicatorsEnabled(false);
     //   built.setLoggingEnabled(true);
     //   Picasso.setSingletonInstance(built);




    }
}
