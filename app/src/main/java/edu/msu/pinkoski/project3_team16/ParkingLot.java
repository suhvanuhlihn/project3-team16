package edu.msu.pinkoski.project3_team16;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.io.Serializable;

/**
 * Created by Jevin on 4/29/17.
 */

public class ParkingLot implements Serializable {
    private double longitude = 0;
    private double latitude = 0;
    private int lotId = 0;
    private String name = null;
    private int imgID;
    private String freeTime = null;
    private String fee = "0";
    private String tips = null;

    // constructor
    public ParkingLot(int id, int imgID) {
        lotId = id;
        this.imgID = imgID;
    }

    public void SetGPS(double lati, double longi) {
        longitude = longi;
        latitude = lati;
    }

    public void SetName(String n) { name = n;}
    public void SetConfig(String free, String fee, String tips) {
        freeTime = free;
        this.fee = fee;
        this.tips = tips;
    }

    public double getLongitude() { return longitude;}
    public double getLatitude() {return latitude;}

    public int getImgID() {return imgID;}
    public String getName() {return name;}
    public String getFreeTime(){return freeTime;}
    public String getFee() {return fee;}
    public String getTips() { return tips;}

}
