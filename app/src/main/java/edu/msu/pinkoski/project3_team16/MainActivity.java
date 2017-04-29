package edu.msu.pinkoski.project3_team16;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import static edu.msu.pinkoski.project3_team16.R.color.black;

public class MainActivity extends AppCompatActivity {

    public ArrayList<ParkingLot> lots = new ArrayList<ParkingLot>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lots.add(new ParkingLot(9));
        lots.add(new ParkingLot(43));
        lots.add(new ParkingLot(73));
        lots.add(new ParkingLot(79));
        lots.add(new ParkingLot(102));

        ParkingLot lot9 = lots.get(0);
        lot9.SetGPS(42.7293374,-84.476294);
        lot9.SetName("Lot 9 In front of Phillips Hall");
        lot9.SetConfig("6pm Friday to 6am Monday", "$0.90/half-hour", " Free at specified times unless there is a football game on Saturday.");

        ParkingLot lot43 = lots.get(1);
        lot43.SetGPS(42.724216,-84.4795345);
        lot43.SetName("Lot 43 Behind Anthony/Engineering Building");
        lot43.SetConfig("6pm-2am", "permit required any other time", "If going from the Engineering Building to this lot you can" +
                "cut through Anthony even if the doors are locked by going through the classes " +
                "room in the vestibule between the building doors.");

        ParkingLot lot73 = lots.get(2);
        lot73.SetGPS(42.7224652,-84.4887669);
        lot73.SetName("Lot 73 & 74 Wilson/Holden Halls");
        lot73.SetConfig("6pm-2am", "permit required any other time", " ");

        ParkingLot lot79 = lots.get(3);
        lot79.SetGPS(42.7281513,-84.4870413);
        lot79.SetName("Lot 79 Spartan Stadium");
        //.90 per half an hour
        lot79.SetConfig("10pm to 2am", "$0.90/half-hour", "Enter anytime of the day but leave after 10pm and avoid paying!");

        ParkingLot lot102 = lots.get(4);
        lot102.SetGPS(42.7211903,-84.4816108);
        lot102.SetName("Lot 102 Behind Com Arts");
        lot102.SetConfig("6pm-2am and weekends","$0.90/half-hour"," ");

    }

    public void onSearch(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    public void onInfo(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogHowTo = inflater.inflate(R.layout.dialogue_info, null);
        dialogBuilder.setView(dialogHowTo);
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
}