package edu.msu.pinkoski.project3_team16;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Savanna on 4/29/2017.
 */

public class ResultActivity extends AppCompatActivity {

    ArrayList<ParkingLot> rankLots;
    String curLati;
    String curLongi;
    String minDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        Intent intent = getIntent();
        rankLots = (ArrayList<ParkingLot>)intent.getSerializableExtra("rankLots");
        curLati = intent.getExtras().getString("curLati");
        curLongi = intent.getExtras().getString("curLongi");
        minDistance = intent.getExtras().getString("minDistance");

        SetUI();
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

    public void SetUI() {
        TextView disText = (TextView) findViewById(R.id.textDistance);
        TextView text1 = (TextView) findViewById(R.id.textPlace);
        TextView text2 = (TextView) findViewById(R.id.textSecondLot);
        TextView text3 = (TextView) findViewById(R.id.textThirdLot);
        TextView text4 = (TextView) findViewById(R.id.textFourthLot);
        TextView text5 = (TextView) findViewById(R.id.textFifthLot);

        disText.setText(minDistance);
        text1.setText(rankLots.get(0).getLotNum());
        text2.setText("2. " + rankLots.get(1).getLotNum());
        text3.setText("3. " + rankLots.get(2).getLotNum());
        text4.setText("4. " + rankLots.get(3).getLotNum());
        text5.setText("5. " + rankLots.get(4).getLotNum());
    }
}
