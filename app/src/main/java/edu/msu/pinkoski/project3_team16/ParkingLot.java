package edu.msu.pinkoski.project3_team16;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Jevin on 4/29/17.
 */

public class ParkingLot {
    private double longitude = 0;
    private double latitude = 0;
    private int lotId = 0;
    private String name = null;
    private transient Bitmap mapPic;
    private transient Bitmap inPersonPic;
    private String freeTime = null;
    private double fee = 0;
    private String tips = null;

    // constructor
    public ParkingLot(Context context, int id) {
        lotId = id;
    }

    public void SetGPS(double lati, double longi) {
        longitude = longi;
        latitude = lati;
    }

    public void SetName(String n) { name = n;}
    public void SetConfig(String free, double fee, String tips) {
        freeTime = free;
        this.fee = fee;
        this.tips = tips;
    }

    public double getLongitude() { return longitude;}
    public double getLatitude() {return latitude;}

    private void DrawImg(Bitmap img, Canvas canvas, int marginX, int marginY,
                     int gridSize, float scaleFactor) {

        float finalX = 0;
        float finalY = 0;
        float offX = 0;
        float offY = 0;


        canvas.save();

        // Convert x,y to pixels and add the margin, then draw
        canvas.translate(marginX + (finalX+offX) * gridSize, marginY + (finalY+offY) * gridSize * 6 / 7.0f);

        // Scale it to the right size
        canvas.scale(scaleFactor, scaleFactor);

        // This magic code makes the center of the piece at 0, 0
        canvas.translate(-img.getWidth() / 2, -img.getHeight() / 2);

        // Draw the bitmap
        canvas.drawBitmap(img, 0, 0, null);
        canvas.restore();
    }

}
