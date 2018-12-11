package com.uva.triva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Leaderboards extends AppCompatActivity implements LeaderboardsRequest.Callback{

    LeaderboardsRequest q;
    int points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        Intent intent = getIntent();
        points = intent.getIntExtra("points", 0);
        TextView total_points = findViewById(R.id.total_points_view);
        total_points.setText("Congratulations you got \"" + points + "\" points.");
        q = new LeaderboardsRequest(this);
    }

    public void reset(View view){
        startActivity(new Intent(Leaderboards.this, MainActivity.class));
    }

    public void postToLeaderboards(View view){
        TextView getName = findViewById(R.id.name);
        String name = getName.getText().toString();

        // post to flask server String + points
        q.postData(name, "" + points + "");

        // switch to leaderboard screen
        startActivity(new Intent(Leaderboards.this, LeaderboardsList.class));

    }

    @Override
    public void gotScore(ArrayList<String> Score) {

    }

    @Override
    public void gotScoreError(String message) {

    }
}
