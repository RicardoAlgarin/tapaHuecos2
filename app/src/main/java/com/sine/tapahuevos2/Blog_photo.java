package com.sine.tapahuevos2;

/**
 * Created by ASUS on 25/11/2016.
 */

public class Blog_photo {

    private String title, desc, image, latitud, longitud;

    public Blog_photo(){

    }


    public Blog_photo(String title, String desc, String image, String latitud, String longitud) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.latitud = latitud;
        this.longitud = longitud;
    }


    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud1) {
        this.latitud = latitud1;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud1) {
        this.longitud = longitud1;
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
