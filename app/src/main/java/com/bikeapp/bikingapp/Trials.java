package com.bikeapp.bikingapp;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.Task;

public class Trials extends AppCompatActivity {

    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trials);

        //Run Methods
        populateListView();
        registerClickCallback();

    }

    private void populateListView() {

        //Test Array Of Items
        String[] Trails = {"Widnes", "Chester", "Parkgate","Sherdley", "Whiston","Test1","Test2","Test3","Test4","Test5","Test6","Test7","Test8","Test9","Test10"};

        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.lstlayout, R.id.textviewlocation, Trails);


        //Configure the list view
        ListView list = (ListView) findViewById(R.id.lstTrails);
        list.setAdapter(adapter);

    }

    private void registerClickCallback() {

        ListView list = (ListView) findViewById(R.id.lstTrails);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                TextView txtview = (TextView)viewClicked.findViewById(R.id.textviewlocation);
                String product = txtview.getText().toString();


                //Invalid Map File, User prompt
                Snackbar snackbar;
                snackbar = Snackbar.make(viewClicked, "No Map Data: " + product, Snackbar.LENGTH_SHORT);
                View snackBarView = snackbar.getView();

                //Custom Colour properties
                snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.White));
                TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(ContextCompat.getColor(context, R.color.Black));
                snackbar.show();


            }
        });
        }
}
