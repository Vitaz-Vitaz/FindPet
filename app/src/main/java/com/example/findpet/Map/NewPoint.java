package com.example.findpet.Map;

import android.media.Image;
import android.widget.ImageView;

import java.sql.Blob;

public class NewPoint {
    public double x;
    public double y;
    public long id;
    public String status;
    public String petName;
    public String petColor;
    public String petContact;
    public String description;
    public byte[] photo;

    public NewPoint() {

    }

    public NewPoint(String description, byte[] photo , double x, double y) {
        this.x = x;
        this.y = y;
        this.description = description;
        this.photo = photo;
    }

    public NewPoint(String description, byte[] photo, double x, double y,  String petName, String petColor, String petContact, String status  ) {
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
    public NewPoint(long id, String description, byte[] photo, double x, double y,  String petName, String petColor, String petContact, String status  ) {
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

    public NewPoint(long id, String description, byte[] photo , double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.description = description;
        this.photo = photo;
    }


    public NewPoint(String description, ImageView im) {
        this.description = description;
    }





}
