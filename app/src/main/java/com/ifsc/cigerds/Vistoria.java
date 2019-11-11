package com.ifsc.cigerds;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.ifsc.cigerds.Interfaces.AsyncInterface;
import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.Threads.ConexaoEnvio;
import com.ifsc.cigerds.main.SectionsPagerAdapter;
import com.ifsc.cigerds.services.EnvioService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Vistoria extends AppCompatActivity implements AsyncInterface {

    private final String NOME_PREFERENCE = "262114a72D&@5aa!!@FA";
    private SharedPreferences prefs;
    private String latitude, longitude;
    private LocationManager locationManager;
    static private SectionsPagerAdapter sectionsPagerAdapter;

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

    public static List getFragments(){
        List<DadosInterface> fragmentList = new ArrayList<>();
        fragmentList.add((DadosOcorrenciaController) sectionsPagerAdapter.getRegisteredFragment(0));
        fragmentList.add((DanosHumanosController) sectionsPagerAdapter.getRegisteredFragment(1));
        fragmentList.add((DanosMateriaisController) sectionsPagerAdapter.getRegisteredFragment(2));
        fragmentList.add((DanosAmbientaisController) sectionsPagerAdapter.getRegisteredFragment(3));
        fragmentList.add((DanosEconomicosController) sectionsPagerAdapter.getRegisteredFragment(4));
        fragmentList.add((IAHController) sectionsPagerAdapter.getRegisteredFragment(5));
        //fragmentList.add((ResumoController) sectionsPagerAdapter.getRegisteredFragment(6));

        return fragmentList;

    }





    public void sendData(JSONObject jsonEnviar, SectionsPagerAdapter sectionsPagerAdapter,  BancoController bancoController){

        find_Location(this, jsonEnviar);
        List<DadosInterface> fragmentList = getFragments();
        //getBaseContext().startService(new Intent(getApplicationContext(), EnviarPosCadastro.class));
        Log.d("IDUSER", prefs.getString("userId", "0").toString());

        find_Location(getBaseContext(), jsonEnviar);

        for(DadosInterface fragmento : fragmentList){
            if(!fragmento.verficaDados()){
                return;
            }

        }


        for(DadosInterface fragmento : fragmentList){

            try {
                fragmento.getDados(jsonEnviar);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        if(jsonEnviar.has("latitude") && jsonEnviar.has("longitude")) {

            if (Network.VerificaConexao(getBaseContext())) {
                ConexaoEnvio envio = new ConexaoEnvio(jsonEnviar, prefs.getString("login", "0"), prefs.getString("password", "0"));
                envio.asyncInterface = this;
                envio.execute();

            } else {
                bancoController.insereDados(jsonEnviar);
                Toast.makeText(getBaseContext(), "Vistoria salva no banco de dados!", Toast.LENGTH_LONG).show();
                startService(new Intent(this, EnvioService.class).putExtra("name", "EnvioService"));
            }
        }else{
            Toast.makeText(this, "Ative sua localização ou conseda permissão do localizador", Toast.LENGTH_LONG).show();
        }

        Log.d("Json",jsonEnviar.toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistoria);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), getResources().getStringArray(R.array.titlesTabs));
        final ViewPager viewPager = findViewById(R.id.view_pager);
        final BancoController bancoController = new BancoController(getBaseContext());
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
        prefs = getSharedPreferences(NOME_PREFERENCE, MODE_PRIVATE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("SISGERDS");
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorToolBar));
        setSupportActionBar(toolbar);


        for(int count = 0; count <= 6; count++) {
            sectionsPagerAdapter.instantiateItem(viewPager, count);
        }

        final JSONObject jsonEnviar =  new JSONObject();

        ResumoController resumoController = (ResumoController) sectionsPagerAdapter.getRegisteredFragment(6);
        resumoController.setParametros(sectionsPagerAdapter, prefs.getString("login", "0").toString(), prefs.getString("password", "0").toString(), getBaseContext());

        try {
            find_Location(this, jsonEnviar);
            jsonEnviar.put("autor",  prefs.getString("userId", "0").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }



        Log.d("ATIVIDADE", jsonEnviar.toString());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendData(jsonEnviar, sectionsPagerAdapter, bancoController);


            }
        });
       // getActionBar().setTitle("SISGERDS (logado como" + prefs.getString("login", "0").toString()+")");
        viewPager.setOffscreenPageLimit(6);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void processFinish(JSONObject result) {
        try {
            if(result.getString("status").equals("200}")){
                Toast.makeText(getBaseContext(), "Vistoria enviada!", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}