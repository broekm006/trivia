package com.uva.triva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // on click go to question page
    public void onButtonClick(View view){
        Intent intent = new Intent(MainActivity.this, Questions.class);
        startActivity(intent);
    }
}
