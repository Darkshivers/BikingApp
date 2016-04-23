package com.bikeapp.bikingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Trails;

        Trails = (Button) findViewById(R.id.txtTest);
        SearchView sv = (SearchView) findViewById(R.id.searchView);
        Trails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Trials.class);
                startActivity(intent);
                System.out.println("Search Pressed");
            }
        });





    }
}
