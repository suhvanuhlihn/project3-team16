package edu.msu.pinkoski.project3_team16;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Savanna on 4/29/2017.
 */

public class ResultActivity extends AppCompatActivity {

    ArrayList<ParkingLot> rankLots;
    String curLati;
    String curLongi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        Intent intent = getIntent();
        rankLots = (ArrayList<ParkingLot>)intent.getSerializableExtra("rankLots");
        curLati = intent.getExtras().getString("curLati");
        curLongi = intent.getExtras().getString("curLongi");
    }

    public void onDetail(View view, int option) {
        ParkingLot lot = rankLots.get(option-1);
        Intent intent = new Intent(ResultActivity.this, DetailActivity.class);
        intent.putExtra("selectedLot", lot);
        intent.putExtra("curLati", curLati);
        intent.putExtra("curLongi", curLongi);
        startActivity(intent);
    }

    public void onOption1(View view){
        onDetail(view, 1);
    }

    public void onOption2(View view){ onDetail(view, 2);}

    public void onOption3(View view){
        onDetail(view, 3);
    }

    public void onOption4(View view){
        onDetail(view, 4);
    }

    public void onOption5(View view){onDetail(view, 5);}
}
