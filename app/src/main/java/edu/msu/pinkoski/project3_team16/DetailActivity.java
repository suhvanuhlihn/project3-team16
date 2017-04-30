package edu.msu.pinkoski.project3_team16;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Savanna on 4/29/2017.
 */

public class DetailActivity extends AppCompatActivity{

    ParkingLot selectedLot;
    String curLati;
    String curLongi;
    String toLati;
    String toLongi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();
        selectedLot = (ParkingLot)intent.getSerializableExtra("selectedLot");
        curLati = intent.getExtras().getString("curLati");
        curLongi = intent.getExtras().getString("curLongi");
        toLati = Double.toString(selectedLot.getLatitude());
        toLongi = Double.toString(selectedLot.getLongitude());

        LotImageView lotView = (LotImageView) this.findViewById(R.id.lotPictureView);
        lotView.SetImg(selectedLot.getImgID());

        SetUI();
    }

    public void buttonMap(View view){
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" + "saddr="+ curLati + "," + curLongi + "&daddr=" + toLati + "," + toLongi));
        intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
        startActivity(intent);
    }


    public void SetUI(){
        TextView lotText = (TextView) findViewById(R.id.textLotNumber);
        TextView freeText = (TextView) findViewById(R.id.textFreeHours);
        TextView feeText = (TextView) findViewById(R.id.textFees);
        TextView tipText = (TextView) findViewById(R.id.textTips);

        lotText.setText(selectedLot.getName());
        freeText.setText(selectedLot.getFreeTime());
        feeText.setText(selectedLot.getFee());
        tipText.setText(selectedLot.getTips());

    }

}

