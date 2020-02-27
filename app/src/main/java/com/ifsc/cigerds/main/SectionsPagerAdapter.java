package com.ifsc.cigerds.main;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ifsc.cigerds.Fragmentos.DadosOcorrenciaController;
import com.ifsc.cigerds.Fragmentos.DanosAmbientaisController;
import com.ifsc.cigerds.Fragmentos.DanosEconomicosController;
import com.ifsc.cigerds.Fragmentos.DanosHumanosController;
import com.ifsc.cigerds.Fragmentos.DanosMateriaisController;
import com.ifsc.cigerds.Fragmentos.IAHController;
import com.ifsc.cigerds.Fragmentos.MapaController;
import com.ifsc.cigerds.Fragmentos.ResumoController;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private String[] mTabTiles;
    private final Context mContext;

    private SparseArray<Fragment> fragmentos = new SparseArray<>();

    public SectionsPagerAdapter(Context context, FragmentManager fm, String[]mTabTiles ) {
        super(fm);
        mContext = context;
        this.mTabTiles = mTabTiles;

    }


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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (mTabTiles[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 7;
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