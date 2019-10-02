package com.ifsc.cigerds;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ifsc.cigerds.Fragmentos.DadosOcorrenciaController;
import com.ifsc.cigerds.Fragmentos.DanosAmbientaisController;
import com.ifsc.cigerds.Fragmentos.DanosEconomicosController;
import com.ifsc.cigerds.Fragmentos.DanosHumanosController;
import com.ifsc.cigerds.Fragmentos.DanosMateriaisController;
import com.ifsc.cigerds.Fragmentos.IAHController;
import com.ifsc.cigerds.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

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

        for(int count = 0; count < 6; count++)
            sectionsPagerAdapter.instantiateItem(viewPager, count);

        final JSONObject jsonEnviar =  new JSONObject();;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DadosOcorrenciaController dadosOcorrenciaController = (DadosOcorrenciaController) sectionsPagerAdapter.getRegisteredFragment(0);
                DanosHumanosController danosHumanosController = (DanosHumanosController) sectionsPagerAdapter.getRegisteredFragment(1);
                DanosMateriaisController danosMateriaisController = (DanosMateriaisController) sectionsPagerAdapter.getRegisteredFragment(2);
                DanosAmbientaisController danosAmbientaisController = (DanosAmbientaisController) sectionsPagerAdapter.getRegisteredFragment(3);
                DanosEconomicosController danosEconomicosController = (DanosEconomicosController) sectionsPagerAdapter.getRegisteredFragment(4);
                IAHController iahController = (IAHController) sectionsPagerAdapter.getRegisteredFragment(5);

                try {
                    dadosOcorrenciaController.getDados(jsonEnviar);
                    danosHumanosController.getDados(jsonEnviar);
                    danosMateriaisController.getDados(jsonEnviar);
                    danosAmbientaisController.getDados(jsonEnviar);
                    danosEconomicosController.getDados(jsonEnviar);
                    iahController.getDados(jsonEnviar);
                    jsonEnviar.put("autor", 1);

                } catch (Exception e) {
                    Log.d("Exep", e.getLocalizedMessage() + " " + e.getMessage());
                }


                Log.d("Json", jsonEnviar.toString());

            }
        });
    }
}