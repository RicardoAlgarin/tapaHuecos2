package com.sine.tapahuevos2;

/**
 * Created by ASUS on 25/11/2016.
 */

public class Blog_photo {

    private String title, desc, image;

    public Blog_photo(){


    }

    public Blog_photo(String title, String desc, String image) {
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
}
