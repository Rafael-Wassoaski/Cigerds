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

public class IAHController extends Fragment implements DadosInterface {


    private HashMap<CheckBox, EditText> iah = new HashMap<>();


    public IAHController() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.iah_fragment, container, false);

        iah.put((CheckBox)view.findViewById(R.id.cestas), (EditText)view.findViewById(R.id.cestasQuant));
        iah.put((CheckBox)view.findViewById(R.id.aguaPotavel), (EditText)view.findViewById(R.id.aguaQuant));
        iah.put((CheckBox)view.findViewById(R.id.colchoes), (EditText)view.findViewById(R.id.colchoesQuant));
        iah.put((CheckBox)view.findViewById(R.id.higiene), (EditText)view.findViewById(R.id.higieneQuant));
        iah.put((CheckBox)view.findViewById(R.id.limpeza), (EditText)view.findViewById(R.id.limpezaQuant));
        iah.put((CheckBox)view.findViewById(R.id.telhas), (EditText)view.findViewById(R.id.telhasQuant));
        iah.put((CheckBox)view.findViewById(R.id.lona), (EditText)view.findViewById(R.id.lonaQuant2));
        iah.put((CheckBox)view.findViewById(R.id.outros), (EditText)view.findViewById(R.id.lonaQuant2));


        for(Map.Entry<CheckBox, EditText> entrada : iah.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            editText.setVisibility(View.INVISIBLE);
        }


        for(Map.Entry<CheckBox, EditText> entrada : iah.entrySet()){

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
