package com.ifsc.cigerds.Classes;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ifsc.cigerds.Vistoria;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class LocalizadorFused implements LocationListener {

    private FusedLocationProviderClient mFusedLocator;
    private Activity activity;
    private String latitude, longitude;

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }


    public LocalizadorFused(Activity activity){
        this.activity = activity;
        mFusedLocator = LocationServices.getFusedLocationProviderClient(this.activity);
    }


    public boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    101);

        }

        return false;
    }

    public String getLocation(){
        final String result;
        mFusedLocator.getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {




                }

            }


        });

        return "0";
    }



    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }


    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(activity, "Por favor, ative sua localização", Toast.LENGTH_LONG).show();
    }
}
