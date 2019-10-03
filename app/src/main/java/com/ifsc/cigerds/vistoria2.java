package com.ifsc.cigerds;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.ifsc.cigerds.Fragmentos.DadosOcorrenciaController;
import com.ifsc.cigerds.Fragmentos.DanosAmbientaisController;
import com.ifsc.cigerds.Fragmentos.DanosEconomicosController;
import com.ifsc.cigerds.Fragmentos.DanosHumanosController;
import com.ifsc.cigerds.Fragmentos.DanosMateriaisController;
import com.ifsc.cigerds.Fragmentos.IAHController;
import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.main.SectionsPagerAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class vistoria2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistoria2);
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), getResources().getStringArray(R.array.titlesTabs));
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        for(int count = 0; count < 6; count++) {
            sectionsPagerAdapter.instantiateItem(viewPager, count);
        }
        final JSONObject jsonEnviar =  new JSONObject();;


        final List<DadosInterface> fragmentList = new ArrayList<>();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    fragmentList.add((DadosOcorrenciaController) sectionsPagerAdapter.getRegisteredFragment(0));
                    fragmentList.add((DanosHumanosController) sectionsPagerAdapter.getRegisteredFragment(1));
                    fragmentList.add((DanosMateriaisController) sectionsPagerAdapter.getRegisteredFragment(2));
                    fragmentList.add((DanosAmbientaisController) sectionsPagerAdapter.getRegisteredFragment(3));
                    fragmentList.add((DanosEconomicosController) sectionsPagerAdapter.getRegisteredFragment(4));
                    fragmentList.add((IAHController) sectionsPagerAdapter.getRegisteredFragment(5));



                    for(DadosInterface fragmento : fragmentList){
                        Log.d("Exep", "1");
                        if(!fragmento.verficaDados()){

                            return;
                        }

                    }

                    for(DadosInterface fragmento : fragmentList){
                        Log.d("Json","entrou");
                      fragmento.getDados(jsonEnviar);

                    }
                    jsonEnviar.put("autor", "1");

                } catch (Exception e) {
                    Log.d("Exep", e.getLocalizedMessage() + " / " + e.getMessage() + " / " + e.getClass() + " / " + e.getCause());

                }


                Log.d("Json",jsonEnviar.toString());

            }
        });
    }
}