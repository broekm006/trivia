package com.uva.triva;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeaderboardsList extends AppCompatActivity implements LeaderboardsRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards_list);
        SystemClock.sleep(1000);
        LeaderboardsRequest y = new LeaderboardsRequest(this);
        y.getScore(this);
    }


    public void reset(View view){
        startActivity(new Intent(LeaderboardsList.this, MainActivity.class));
    }


    @Override
    public void gotScore(ArrayList<Leaderboard> Score) {
        LeaderboardCustomAdapter adapter = new LeaderboardCustomAdapter(this, Score);
        ListView listView = findViewById(R.id.Leaderboards);
        listView.setAdapter(adapter);

    }

    @Override
    public void gotScoreError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
