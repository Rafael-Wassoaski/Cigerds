package com.ifsc.cigerds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.core.app.BundleCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ifsc.cigerds.Fragmentos.MapaController;

import java.util.List;

public class MapaPopup extends FragmentActivity {
    private FragmentManager manager;
    public void createMap(){
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_back, new MapaController(), "Mapa");
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_popup);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int largura = metrics.widthPixels;
        int altura = metrics.heightPixels;
        getWindow().setLayout((int)(largura*.8), (int)(altura*.6));
        createMap();
    }

    @Override
    public void finish() {
        List<Fragment> fragments = manager.getFragments();
        MapaController controllerFragment = (MapaController) fragments.get(0);
        Intent intent = new Intent();
        intent.putExtra("Endereco", controllerFragment.getEndereco());
        setResult(Activity.RESULT_OK, intent);
        super.finish();
    }

}
