package com.bikeapp.bikingapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final int ACCESS_FINE_LOCATION = 0;
    private static final int ACCESS_COARSE_LOCATION = 0;


    Double lat = 53.427815;
    Double lng = -2.712940;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Trail;

        Trail = (Button) findViewById(R.id.txtTest);


        Trail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getloc();
            }
        });

        Button Next = (Button) findViewById(R.id.btnNext);
        Button Help = (Button) findViewById(R.id.btnHelp);

        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tutorial.class);
                startActivity(intent);
            }
        });

        assert Next != null;
        Next.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Intent intent = new Intent(MainActivity.this, Trials.class);
                                        startActivity(intent);

                                    }
                                }
        );


    }

    public void getloc() {

        LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

                lat = location.getLatitude();
                lng = location.getLongitude();

                System.out.println("Longitude: " + lng + " Latidude: " + lat);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;

        } else {

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            if (lat != null) {


                Geocoder geocoder = new Geocoder(this, Locale.getDefault());

                try {

                    SearchView sv = (SearchView) findViewById(R.id.searchView);
                    List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
                    String postcodeaddress = addresses.get(0).getPostalCode();
                    sv.setQuery(postcodeaddress, false);

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

        }

    }
}
