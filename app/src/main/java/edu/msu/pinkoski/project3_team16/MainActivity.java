package edu.msu.pinkoski.project3_team16;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

import static edu.msu.pinkoski.project3_team16.R.color.black;

public class MainActivity extends AppCompatActivity {

    // GPS STUFF
    private class ActiveListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            onLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            registerListeners();
        }
    };

    private ActiveListener activeListener = new ActiveListener();
    private LocationManager locationManager = null;
    private double curLati = 0;
    private double curLongi = 0;

    private void registerListeners() {
        unregisterListeners();

        // Create a Criteria object
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);

        String bestAvailable = locationManager.getBestProvider(criteria, true);

        if(bestAvailable != null) {
            locationManager.requestLocationUpdates(bestAvailable, 500, 1, activeListener);
            //TextView viewProvider = (TextView)findViewById(R.id.textProvider);
            //viewProvider.setText(bestAvailable);
            Location location = locationManager.getLastKnownLocation(bestAvailable);
            onLocation(location);
        }
    }

    private void unregisterListeners() {
        locationManager.removeUpdates(activeListener);
    }

    private void onLocation(Location location) {
        if(location == null) {
            return;
        }

        curLati = location.getLatitude();
        curLongi = location.getLongitude();
    }
    /**
     * Called when this application becomes foreground again.
     */
    @Override
    protected void onResume() {
        super.onResume();

        registerListeners();
    }

    /**
     * Called when this application is no longer the foreground application.
     */
    @Override
    protected void onPause() {
        unregisterListeners();
        super.onPause();
    }




    public ArrayList<ParkingLot> lots = new ArrayList<ParkingLot>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///// GPS SETUP
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

        // Force the screen to say on and bright
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Get the location manager
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //// finish setup GPS

        lots.add(new ParkingLot(9, R.drawable.phillips));
        lots.add(new ParkingLot(43, R.drawable.anthony));
        lots.add(new ParkingLot(73, R.drawable.wilson));
        lots.add(new ParkingLot(79, R.drawable.spartan_stadium));
        lots.add(new ParkingLot(102, R.drawable.com_arts));

        ParkingLot lot9 = lots.get(0);
        lot9.SetGPS(42.7293374,-84.476294);
        lot9.SetName("Lot 9 In front of Phillips Hall");
        lot9.SetConfig("6pm Friday to 6am Monday", "$0.90/half-hour", " Free at specified times unless there is a football game on Saturday.");

        ParkingLot lot43 = lots.get(1);
        lot43.SetGPS(42.724216,-84.4795345);
        lot43.SetName("Lot 43 Behind Anthony/Engineering Building");
        lot43.SetConfig("6pm-2am", "permit required any other time", "The small section between Anthony & the EB where locks the doors are locked, classroom (room#)"
                 + "is a shortcut to the back parking lot of Anthony.");

        ParkingLot lot73 = lots.get(2);
        lot73.SetGPS(42.7224652,-84.4887669);
        lot73.SetName("Lot 73 & 74 Wilson/Holden Halls");
        lot73.SetConfig("6pm-2am", "permit required any other time", "Go to MLC hours.");

        ParkingLot lot79 = lots.get(3);
        lot79.SetGPS(42.7281513,-84.4870413);
        lot79.SetName("Lot 79 Spartan Stadium");
        //.90 per half an hour
        lot79.SetConfig("10pm to 2am", "$0.90/half-hour", "Enter anytime of the day but leave after 10pm to avoid paying!");

        ParkingLot lot102 = lots.get(4);
        lot102.SetGPS(42.7211903,-84.4816108);
        lot102.SetName("Lot 102 Behind Com Arts");
        lot102.SetConfig("6pm-2am and weekends","$0.90/half-hour","Covered Parking Garage");

    }

    public void onSearch(View view) {
        // get the closest result
        ArrayList<ParkingLot> result = rankPark();

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("rankLots", rankPark());
        intent.putExtra("curLati", Double.toString(curLati));
        intent.putExtra("curLongi", Double.toString(curLongi));
        startActivity(intent);
    }

    public void onInfo(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(R.string.infoText);
        dialogBuilder.setTitle("Welcome to MSU Parking Tips!");
        dialogBuilder
                .setCancelable(false)
                .setNegativeButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        alertDialog.getButton(alertDialog.BUTTON_NEGATIVE).setTextColor(black);
    }

    public ParkingLot findNearest(ArrayList<ParkingLot> list) {
        boolean firstTest = true;
        float minD = 0;
        int minId = 0;
        int id = 0;
        for (ParkingLot lot : list) {
            float[] results = {0,0,0};
            Location.distanceBetween(curLati, curLongi, lot.getLatitude(), lot.getLongitude(), results);
            if (firstTest || minD > results[0]){
                firstTest = false;
                minD = results[0];
                minId = id;
            }
            id++;
        }
        return list.get(minId);
    }


    public ArrayList<ParkingLot> rankPark() {
        ArrayList<ParkingLot> restList = (ArrayList<ParkingLot>) lots.clone();
        ArrayList<ParkingLot> rankList = new ArrayList<ParkingLot>();

        for (int i = 0; i < lots.size(); i++) {
            ParkingLot nearLot = findNearest(restList);
            rankList.add(nearLot);
            restList.remove(nearLot);
        }

        return rankList;
    }
}