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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DanosEconomicosController extends Fragment implements DadosInterface {

    private HashMap<CheckBox, EditText> economicos = new HashMap<>();
    private EditText danos_economicos_observacoes;


    public DanosEconomicosController(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.danos_economicos_fragment, container, false);

        economicos.put((CheckBox)view.findViewById(R.id.danosAgricultura), (EditText)view.findViewById(R.id.danos_agricultura));
        economicos.put((CheckBox)view.findViewById(R.id.danosPecuária), (EditText)view.findViewById(R.id.danos_pecuaria));
        economicos.put((CheckBox)view.findViewById(R.id.danosComercio), (EditText)view.findViewById(R.id.danos_comercio));
        economicos.put((CheckBox)view.findViewById(R.id.danosIndustria), (EditText)view.findViewById(R.id.danos_industria));
        economicos.put((CheckBox)view.findViewById(R.id.danosServicos), (EditText)view.findViewById(R.id.interrupcoes_servicos_essenciais));
        danos_economicos_observacoes = (EditText)view.findViewById(R.id.danos_humanos_observacoes);
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
    public void getDados(JSONObject json) throws JSONException {


        if(!danos_economicos_observacoes.getText().toString().isEmpty()){
            json.put("danos_ambientais_observacoes", danos_economicos_observacoes.getText().toString());
        }else{
            json.put("danos_ambientais_observacoes", "Sem obeservações");
        }

        for(Map.Entry<CheckBox, EditText> entrada : economicos.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            if(checkBox.isChecked()){
                json.put(editText.getTag().toString(), editText.getText().toString());

            }else{
                json.put(editText.getTag().toString(), 0);
            }
        }

    }


    @Override
    public Boolean verficaDados() {
        for(Map.Entry<CheckBox, EditText> entrada : economicos.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            if(checkBox.isChecked() && editText.getText().toString().isEmpty()){
                editText.requestFocus();
                return  false;
            }
        }

        return true;
    }
}
