package com.sine.tapahuevos2;

/**
 * Created by ASUS on 25/11/2016.
 */

public class Blog {

    private String title, desc, image, latitud, longitud;

    public Blog() {

    }


    public Blog(String desc, String latitud, String longitud, String image, String title) {
        this.desc = desc;
        this.latitud = latitud;
        this.longitud = longitud;
        this.image = image;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}

  /*  public Blog_photo(String title, String desc, String image, String latitud, String longitud) {
        this.title = title;
        this.desc = desc;
        this.image = image;
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
} */
