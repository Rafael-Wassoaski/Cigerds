package com.ifsc.cigerds;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.ifsc.cigerds.Fragmentos.DadosOcorrenciaController;
import com.ifsc.cigerds.Fragmentos.DanosAmbientaisController;
import com.ifsc.cigerds.Fragmentos.DanosEconomicosController;
import com.ifsc.cigerds.Fragmentos.DanosHumanosController;
import com.ifsc.cigerds.Fragmentos.DanosMateriaisController;
import com.ifsc.cigerds.Fragmentos.IAHController;
import com.ifsc.cigerds.Fragmentos.ResumoController;
import com.ifsc.cigerds.Interfaces.DadosInterface;
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
                                Toast.makeText(getBaseContext(), "Por favor, ligue sua localização", Toast.LENGTH_LONG).show();
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

        for(int count = 0; count <= 6; count++) {
            sectionsPagerAdapter.instantiateItem(viewPager, count);
        }
        final JSONObject jsonEnviar =  new JSONObject();
        final List<DadosInterface> fragmentList = new ArrayList<>();

        fragmentList.add((DadosOcorrenciaController) sectionsPagerAdapter.getRegisteredFragment(0));
        fragmentList.add((DanosHumanosController) sectionsPagerAdapter.getRegisteredFragment(1));
        fragmentList.add((DanosMateriaisController) sectionsPagerAdapter.getRegisteredFragment(2));
        fragmentList.add((DanosAmbientaisController) sectionsPagerAdapter.getRegisteredFragment(3));
        fragmentList.add((DanosEconomicosController) sectionsPagerAdapter.getRegisteredFragment(4));
        fragmentList.add((IAHController) sectionsPagerAdapter.getRegisteredFragment(5));
        fragmentList.add((ResumoController) sectionsPagerAdapter.getRegisteredFragment(6));
        ResumoController resumoController = (ResumoController) fragmentList.get(6);
        resumoController.setParametros(fragmentList, prefs.getString("login", "0").toString(), prefs.getString("password", "0").toString(), getBaseContext(), jsonEnviar);

        CriaResumo criaResumo = new CriaResumo(fragmentList);
        Thread threadResumo = new Thread(criaResumo);
        threadResumo.start();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getBaseContext().startService(new Intent(getApplicationContext(), EnviarPosCadastro.class));
                find_Location(getBaseContext(), jsonEnviar);
                Log.d("IDUSER", prefs.getString("userId", "0").toString());
                try {



                    for(DadosInterface fragmento : fragmentList){
                        if(!fragmento.verficaDados()){
                            return;
                        }

                    }


                    for(DadosInterface fragmento : fragmentList){

                      fragmento.getDados(jsonEnviar);

                    }

                    jsonEnviar.put("autor", Integer.parseInt(prefs.getString("userId", "1")));




                } catch (Exception e) {

                    Log.d("Exep", e.getLocalizedMessage() + " / " + e.getMessage() + " / " + e.getClass() + " / " + e.getCause());

                }


                Log.d("Json",jsonEnviar.toString());

            }
        });
    }




    public class CriaResumo implements Runnable{

        private Boolean encerrar = false;
        private List<DadosInterface> fragmentList;

        public CriaResumo(List<DadosInterface> fragmentList){
            this.fragmentList = fragmentList;
        }

        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ResumoController resumoController = (ResumoController) fragmentList.get(6);
                    while(!encerrar){
                        String resumoString = "";


                        for (int cont = 0; cont < fragmentList.size() - 1; cont++) {
                            resumoString += fragmentList.get(cont).getResumo();
                        }


                        resumoController.setResumo(resumoString);

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        }
    }



}