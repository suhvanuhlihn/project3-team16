package edu.msu.pinkoski.project3_team16;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Savanna on 4/29/2017.
 */

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
    }

    public void onDetail(View view) {
        Intent intent = new Intent(ResultActivity.this, DetailActivity.class);
        startActivity(intent);
    }
}
