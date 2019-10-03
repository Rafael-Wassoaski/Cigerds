package com.ifsc.cigerds.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanosEconomicosController extends Fragment implements DadosInterface {

    private HashMap<CheckBox, EditText> economicos = new HashMap<>();
    private EditText danos_economicos_observacoes;
    private List<String> nameTag;

    public DanosEconomicosController(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.danos_economicos_fragment, container, false);
        nameTag = new ArrayList<>();
        nameTag.add("danos_agricultura");
        nameTag.add("danos_pecuaria");
        nameTag.add("danos_comercio");
        nameTag.add("danos_industria");
        nameTag.add("danos_prestacao_de_servicos ");

        economicos.put((CheckBox)view.findViewById(R.id.danosAgricultura), (EditText)view.findViewById(R.id.danos_agricultura));
        economicos.put((CheckBox)view.findViewById(R.id.danosPecuária), (EditText)view.findViewById(R.id.danos_pecuaria));
        economicos.put((CheckBox)view.findViewById(R.id.danosComercio), (EditText)view.findViewById(R.id.danos_comercio));
        economicos.put((CheckBox)view.findViewById(R.id.danosIndustria), (EditText)view.findViewById(R.id.danos_industria));
        economicos.put((CheckBox)view.findViewById(R.id.danosServicos), (EditText)view.findViewById(R.id.danos_prestacao_de_servicos ));
        danos_economicos_observacoes = (EditText)view.findViewById(R.id.danos_ambientais_observacoes);
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
        Integer count = 0;

        if(!danos_economicos_observacoes.getText().toString().isEmpty()){
            json.put("danos_economicos_observacoes", danos_economicos_observacoes.getText().toString());
        }else{
            json.put("danos_economicos_observacoes", "Sem obeservações");
        }

        for(Map.Entry<CheckBox, EditText> entrada : economicos.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            if(checkBox.isChecked()){
                json.put(nameTag.get(count), editText.getText().toString());

            }else{
                json.put(nameTag.get(count), 0);
            }
            count++;
        }

    }


    @Override
    public Boolean verficaDados() {
        for(Map.Entry<CheckBox, EditText> entrada : economicos.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            if(checkBox.isChecked() && editText.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Você não informou uma quantide para a opção marcada ", Toast.LENGTH_LONG).show();
                editText.requestFocus();
                return  false;
            }
        }

        return true;
    }
}
