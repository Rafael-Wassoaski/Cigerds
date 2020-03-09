package com.ifsc.cigerds.Fragmentos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ifsc.cigerds.Classes.AdaptadorCheckBox;
import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanosMateriaisController extends Fragment implements DadosInterface {

    private  List<AdaptadorCheckBox> materiais = new ArrayList<>();
    private List<String> nameTag;

    public DanosMateriaisController(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.danos_materiais_fragment, container, false);
        nameTag = new ArrayList<>();

        nameTag.add("habitacoesAtingidas");
        nameTag.add("interrupcaoDeServi");
        nameTag.add("infraPublica");
        nameTag.add("economiaPrivada");


        materiais.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.atingidos), (EditText)view.findViewById(R.id.unidades_habitacionais_atingidas)));
        materiais.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.servicos_essenciais), (EditText)view.findViewById(R.id.danos_materiais_servicos_essenciais)));
        materiais.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.obras_destruidas), (EditText)view.findViewById(R.id.danos_materiais_obras_destruidas)));
        materiais.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.danos_economicos), (EditText)view.findViewById(R.id.danos_materiais_privados)));



        for(AdaptadorCheckBox entrada : materiais){

            final EditText editText = entrada.getEditText();

            editText.setVisibility(View.INVISIBLE);
        }


        for(AdaptadorCheckBox entrada : materiais){

            CheckBox checkBox = entrada.getCheckBox();
            final EditText editText = entrada.getEditText();

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

        Integer count1 = 0;

        for(AdaptadorCheckBox entrada : materiais){

            CheckBox checkBox = entrada.getCheckBox();
            final EditText editText = entrada.getEditText();

            if(checkBox.isChecked()){
                json.put(nameTag.get(count1), editText.getText());

            }else{
                json.put(nameTag.get(count1), 0);
            }
            count1++;
        }
    }

    @Override
    public String getResumo() {

        String resumo = "DANOS MATERIAIS\n\n";


        for(AdaptadorCheckBox entrada : materiais){

            CheckBox checkBox = entrada.getCheckBox();
            final EditText editText = entrada.getEditText();

            if(checkBox.isChecked()){
                resumo+= checkBox.getText().toString()+ ": "+ editText.getText()+"\n";

            }else{
                resumo+= checkBox.getText().toString()+ ": 0\n";
            }

        }
        resumo+="\n\n";
        return resumo;

    }

    @Override
    public Boolean verficaDados() {



        for(AdaptadorCheckBox entrada : materiais){

            CheckBox checkBox = entrada.getCheckBox();
            final EditText editText = entrada.getEditText();

            if(checkBox.isChecked() && editText.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Você não informou uma quantide para a opção marcada ", Toast.LENGTH_LONG).show();
                editText.requestFocus();
                return  false;
            }
        }
        return true;
    }
}
