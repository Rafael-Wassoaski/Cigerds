package com.ifsc.cigerds.Classes;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ifsc.cigerds.Fragmentos.DadosOcorrenciaController;
import com.ifsc.cigerds.Fragmentos.DanosAmbientaisController;
import com.ifsc.cigerds.Fragmentos.DanosEconomicosController;
import com.ifsc.cigerds.Fragmentos.DanosHumanosController;
import com.ifsc.cigerds.Fragmentos.DanosMateriaisController;
import com.ifsc.cigerds.Fragmentos.IAHController;
import com.ifsc.cigerds.Fragmentos.ResumoController;

import org.json.JSONObject;

public class PagerAdapter extends FragmentPagerAdapter {

    private String[] mTabTiles;

    private SparseArray<Fragment> fragmentos = new SparseArray<>();

    public PagerAdapter(FragmentManager fm, String[]mTabTiles) {
        super(fm);
        this.mTabTiles = mTabTiles;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DadosOcorrenciaController();
            case 1:
                return new DanosHumanosController();
            case 2:
                return new DanosMateriaisController();
            case 3 :
                return  new DanosAmbientaisController();
            case 4:
                return new DanosEconomicosController();
            case 5:
                return new IAHController();
            case 6:
                return new ResumoController();
            default:
                return  null;

        }
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentos.put(position, fragment);
        return fragment;
    }

    public Fragment getRegisteredFragment(int position) {
        return fragmentos.get(position);
    }
}
