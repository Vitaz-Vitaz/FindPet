package com.example.findpet.Map;

import android.media.Image;
import android.widget.ImageView;

import java.sql.Blob;

public class Point2 {
    public double x;
    public double y;
    public long id;
    public String status;
    public String petName;
    public String petColor;
    public String petContact;
    public String description;
    public String photo;

    public Point2() {

    }

    public Point2(String description, String photo , double x, double y) {
        this.x = x;
        this.y = y;
        this.description = description;
        this.photo = photo;
    }

    public Point2(String description, String photo, double x, double y,  String petName, String petColor, String petContact, String status  ) {
        this.description = description;
        this.photo = photo;
        this.petName = petName;
        this.petColor = petColor;
        this.petContact = petContact;
        this.x = x;
        this.y = y;
        this.id = id;
        this.status = status;
    }
    public Point2(long id, String description, String photo, double x, double y,  String petName, String petColor, String petContact, String status  ) {
        this.description = description;
        this.photo = photo;
        this.petName = petName;
        this.petColor = petColor;
        this.petContact = petContact;
        this.x = x;
        this.y = y;
        this.id = id;
        this.status = status;


    }

    public Point2(long id, String description, String photo , double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.description = description;
        this.photo = photo;
    }







}
