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

public class DanosAmbientaisController extends Fragment  implements DadosInterface {

    private CheckBox contamincaoAguaArSolo;
    private EditText AguaArSoloQuant;

    public DanosAmbientaisController() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.danos_ambientais_fragment, container, false);
        contamincaoAguaArSolo = (CheckBox) view.findViewById(R.id.contaminacao);
        AguaArSoloQuant = (EditText)view.findViewById(R.id.contaminacao_agua_ar_terra);



        contamincaoAguaArSolo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    AguaArSoloQuant.setVisibility(View.VISIBLE);
                }else{
                    AguaArSoloQuant.setVisibility(View.INVISIBLE);
                }
            }
        });

        return view;
    }

    @Override
    public void getDados(JSONObject json) throws JSONException {


            if(contamincaoAguaArSolo.isChecked()) {

                json.put("ambiente", AguaArSoloQuant.getText().toString());
            }else{
                json.put("ambiente", "Não afetada");

            }




    }

    @Override
    public String getResumo() {
        String resumo = "";

        resumo+= "DANOS AMBIENTAIS\n\n";

        if(contamincaoAguaArSolo.isChecked()) {

            resumo+="Contaminação do Ar: "+ AguaArSoloQuant.getText()+"\n";
        }else{
            resumo+="Contaminação do Ar: 0\n";

        }

        resumo+="\n\n";
        return resumo;
    }

    @Override
    public Boolean verficaDados(){

        if(contamincaoAguaArSolo.isChecked()){
            if(AguaArSoloQuant.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Você não informou a quantide de pessoas atingidas", Toast.LENGTH_LONG).show();
                AguaArSoloQuant.requestFocus();
                return false;
            }
        }

        return true;

    }
}
