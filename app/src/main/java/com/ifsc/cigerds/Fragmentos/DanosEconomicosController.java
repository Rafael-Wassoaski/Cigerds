package com.ifsc.cigerds.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.R;

import java.util.HashMap;
import java.util.Map;

public class DanosEconomicosController extends Fragment implements DadosInterface {

    private HashMap<CheckBox, EditText> economicos = new HashMap<>();


    public DanosEconomicosController(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_danos_economicos, container, false);

        economicos.put((CheckBox)view.findViewById(R.id.danosAgricultura), (EditText)view.findViewById(R.id.agriculturaQuant));
        economicos.put((CheckBox)view.findViewById(R.id.danosPecuária), (EditText)view.findViewById(R.id.pecuariaQuant));
        economicos.put((CheckBox)view.findViewById(R.id.danosComercio), (EditText)view.findViewById(R.id.comercioQuant));
        economicos.put((CheckBox)view.findViewById(R.id.danosIndustria), (EditText)view.findViewById(R.id.industriaQuant));
        economicos.put((CheckBox)view.findViewById(R.id.danosServicos), (EditText)view.findViewById(R.id.servicosQuant));

        for(Map.Entry<CheckBox, EditText> entrada : economicos.entrySet()){


            final EditText editText = entrada.getValue();

            editText.setVisibility(View.INVISIBLE);
        }


        for(Map.Entry<CheckBox, EditText> entrada : economicos.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(compoundButton.isChecked()){
                        editText.setVisibility(View.VISIBLE);
                    }else{
                        editText.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }


        return view;
    }


    @Override
    public String getDados() {
        return null;
    }

    @Override
    public Boolean verficaDados() {
        return null;
    }
}
