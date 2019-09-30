package com.ifsc.cigerds.Classes;

import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ifsc.cigerds.Fragmentos.DanosAmbientaisController;
import com.ifsc.cigerds.Fragmentos.IAHController;

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

                return new ocorrenciaCadastro();
            case 1:
                return new danosHumanos();
            case 2:
                return new danosMateriais();
            case 3 :
                return  new DanosAmbientaisController();
            case 4:
                return new danosEconomicos();
            case 5:
                return new IAHController();

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
