package com.bikeapp.bikingapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.annotation.IntegerRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Trials extends AppCompatActivity {

    //Variables
    String databasestate = "";
    Boolean databaseisconnected = true;

    //Search View
    SearchView sv;


    //Conn Strings
    String dbUsername = "user_db_1219284_Diss";
    String dbPassword = "XZ9A7U";
    String connString = "jdbc:jtds:sqlserver://SQL2014.studentwebserver.co.uk";
    Statement stmt;

    //Array List for List adapter
    public ArrayList<RowItems> TrailsName = new ArrayList<RowItems>();

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trials);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //implement Search View
        sv = (SearchView) findViewById(R.id.searchView2);

        //Run Methods
        onCreate();

    }

    public void onCreate(){

        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            Connection DbConn = DriverManager.getConnection(connString, dbUsername, dbPassword);
            databasestate = ("Database Open");
            databaseisconnected = true;
            System.out.println(databasestate);
            stmt = DbConn.createStatement();

        }

        catch (Exception e) {

            databasestate = ("Connection error " + e.getMessage());
            System.out.println(databasestate);
            databaseisconnected = false;

        }

        populateListView();
        registerClickCallback();

    }

    private void populateListViewAdapter() {


        final ArrayAdapter adapter = new CustomAdapter(this, TrailsName);
        ListView list = (ListView) findViewById(R.id.lstTrails);
        list.setAdapter(adapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                adapter.getFilter().filter(text);
                return false;
            }
        });
    }

    private void populateListView() {

//Conn Succeeded
if (databaseisconnected == true) {


        try {

            //Query DB for Titles
            String sqlTrailsDB;
            sqlTrailsDB = "SELECT * FROM db_1219284_Diss.dbo.Trails";
            String titles = "";
            String BikeType = "";
            String biketypestring = "";
            int length = 0;
            String lengthstring = "";
            String description = "";
            ResultSet rst;
            rst = stmt.executeQuery(sqlTrailsDB);

            while(rst.next()) {

                //Trail table calls
                titles = rst.getString("Trail_Name");
                length = rst.getInt("Trail_Length");
                BikeType = rst.getString("Bike_ID");
                description = rst.getString("Trail_Desc");

                //Convert to support array adapter
                lengthstring = String.valueOf(length);
                biketypestring = String.valueOf(BikeType);
                String result = "";


                //Select Statement; chooses bike type
                if (biketypestring == "1") {
                    TrailsName.add(new RowItems(titles, lengthstring, RowItems.BikeTypes.BMXBike, description ));
                }

                else if (biketypestring == "2"){
                    TrailsName.add(new RowItems(titles, lengthstring, RowItems.BikeTypes.RoadBike, description));
                }

                else if (biketypestring == "3"){
                    TrailsName.add(new RowItems(titles, lengthstring, RowItems.BikeTypes.MountainBike, description));
                }

                populateListViewAdapter();

            }
}

        catch(Exception e){
                System.out.println("connection error " + e.getMessage());

            }

}
//Conn Failed
else if (databaseisconnected == false) {

    new AlertDialog.Builder(context)

            .setTitle("Cannot Connect")
            .setMessage("Cannot connect to server please try again later")
            .setCancelable(false)
            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent = new Intent(Trials.this, MainActivity.class);
                    startActivity(intent);
                }
            })

            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();

        }
    }

    //Add OnClick to individual ListViews
    private void registerClickCallback() {

        ListView list = (ListView) findViewById(R.id.lstTrails);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                TextView txtview = (TextView)viewClicked.findViewById(R.id.textviewlocation);
                String product = txtview.getText().toString();

                Intent intent = new Intent(Trials.this, Map_Activity.class);
                startActivity(intent);
                //Invalid Map File, User prompt


                if (product == null) {

                    Snackbar snackbar;
                    snackbar = Snackbar.make(viewClicked, "No Map Data: " + product, Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();

                    //Custom Colour properties
                    snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.White));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.Black));
                    snackbar.show();
                }
            }
        });
        }
    }
