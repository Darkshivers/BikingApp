package com.bikeapp.bikingapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Trials extends AppCompatActivity {

    //Variables
    String databasestate = "";
    Boolean databaseisconnected = true;

    //Conn Strings
    String dbUsername = "user_db_1219284_Diss";
    String dbPassword = "XZ9A7U";
    String connString = "jdbc:jtds:sqlserver://SQL2014.studentwebserver.co.uk";
    Statement stmt;



    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trials);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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

    private void populateListView() {



if (databaseisconnected == true)  {



            //Test Array Of Items
            String[] Trails = {"Widnes", "Chester", "Parkgate","Sherdley", "Whiston","Test1","Test2","Test3","Test4","Test5","Test6","Test7","Test8","Test9","Test10"};

            //Build Adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.lstlayout, R.id.textviewlocation, Trails);


            //Configure the list view
            ListView list = (ListView) findViewById(R.id.lstTrails);
            list.setAdapter(adapter);

        }


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
