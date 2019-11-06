package com.ifsc.cigerds;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.ifsc.cigerds.Classes.Network;
import com.ifsc.cigerds.DB.BancoController;
import com.ifsc.cigerds.Fragmentos.DadosOcorrenciaController;
import com.ifsc.cigerds.Fragmentos.DanosAmbientaisController;
import com.ifsc.cigerds.Fragmentos.DanosEconomicosController;
import com.ifsc.cigerds.Fragmentos.DanosHumanosController;
import com.ifsc.cigerds.Fragmentos.DanosMateriaisController;
import com.ifsc.cigerds.Fragmentos.IAHController;
import com.ifsc.cigerds.Fragmentos.ResumoController;
import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.Threads.ConexaoEnvio;
import com.ifsc.cigerds.main.SectionsPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Vistoria extends AppCompatActivity {

    private final String NOME_PREFERENCE = "262114a72D&@5aa!!@FA";
    private SharedPreferences prefs;
    private String latitude, longitude;
    private LocationManager locationManager;

    public void find_Location(Context con, JSONObject json) {
        String location_context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) con.getSystemService(location_context);
        List<String> providers = locationManager.getProviders(true);

        if (ContextCompat.checkSelfPermission(con, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(con,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    101);
        }else {


            for (String provider : providers) {
                locationManager.requestLocationUpdates(provider, 1000, 0,
                        new LocationListener() {

                            public void onLocationChanged(Location location) {
                            }

                            public void onProviderDisabled(String provider) {
                            }

                            public void onProviderEnabled(String provider) {
                            }

                            public void onStatusChanged(String provider, int status,
                                                        Bundle extras) {
                            }
                        });
                Location location = locationManager.getLastKnownLocation(provider);
                if (location != null) {

                    try {
                        json.put("latitude", String.valueOf(location.getLatitude()));
                        json.put("longitude", String.valueOf(location.getLongitude()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistoria2);
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), getResources().getStringArray(R.array.titlesTabs));
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
        prefs = getSharedPreferences(NOME_PREFERENCE, MODE_PRIVATE);

        for(int count = 0; count < 6; count++) {
            sectionsPagerAdapter.instantiateItem(viewPager, count);
        }
        final JSONObject jsonEnviar =  new JSONObject();





        final List<DadosInterface> fragmentList = new ArrayList<>();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                find_Location(getBaseContext(), jsonEnviar);
                Log.d("IDUSER", prefs.getString("userId", "0").toString());
                try {

                    fragmentList.add((DadosOcorrenciaController) sectionsPagerAdapter.getRegisteredFragment(0));
                    fragmentList.add((DanosHumanosController) sectionsPagerAdapter.getRegisteredFragment(1));
                    fragmentList.add((DanosMateriaisController) sectionsPagerAdapter.getRegisteredFragment(2));
                    fragmentList.add((DanosAmbientaisController) sectionsPagerAdapter.getRegisteredFragment(3));
                    fragmentList.add((DanosEconomicosController) sectionsPagerAdapter.getRegisteredFragment(4));
                    fragmentList.add((IAHController) sectionsPagerAdapter.getRegisteredFragment(5));


                    for(DadosInterface fragmento : fragmentList){
                        if(!fragmento.verficaDados()){
                            return;
                        }

                    }


                    for(DadosInterface fragmento : fragmentList){

                      fragmento.getDados(jsonEnviar);

                    }

                    jsonEnviar.put("autor", Integer.parseInt(prefs.getString("userId", "1")));
                    //envio

                    ResumoController resumoController = (ResumoController)sectionsPagerAdapter.instantiateItem(viewPager, 6);
                    resumoController.setResumo("Teste", prefs.getString("login", "0").toString(), prefs.getString("password", "0").toString(), getBaseContext(), jsonEnviar);

                } catch (Exception e) {

                    Log.d("Exep", e.getLocalizedMessage() + " / " + e.getMessage() + " / " + e.getClass() + " / " + e.getCause());

                }


                Log.d("Json",jsonEnviar.toString());

            }
        });
    }



}