package com.sine.tapahuevos2;

/**
 * Created by ASUS on 25/11/2016.
 */

public class Blog {

    private String title, desc, image,latitud,longitud;

    public Blog(){


    }

    public Blog(String title, String desc, String image,String latitud, String longitud) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.latitud = latitud;
        this.longitud = longitud;



    }


    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
