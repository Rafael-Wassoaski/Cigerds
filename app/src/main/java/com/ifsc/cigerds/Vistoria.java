package com.ifsc.cigerds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.ifsc.cigerds.Classes.Localizador;
import com.ifsc.cigerds.Classes.PagerAdapter;
import com.ifsc.cigerds.Interfaces.DadosInterface;

import java.util.ArrayList;

public class Vistoria extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<DadosInterface> fragmentos = new ArrayList<DadosInterface>();
    private FusedLocationProviderClient fusedLocationClient;
    private Localizador localizador = new Localizador(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistoria);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter pager = new PagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.titlesTabs));

        final FloatingActionButton fab = findViewById(R.id.fab);


    }
}
