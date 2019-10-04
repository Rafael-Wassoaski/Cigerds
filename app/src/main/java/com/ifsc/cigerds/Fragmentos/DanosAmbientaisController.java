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

    private CheckBox contamincaoAgua;
    private CheckBox contamincaoSolo;
    private CheckBox contamincaoAr;
    private EditText AguaQuant;
    private EditText SoloQuant;
    private EditText ArQuant;
    private EditText danos_ambientais_observacoes;


    public DanosAmbientaisController() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.danos_ambientais_fragment, container, false);
        contamincaoAgua = (CheckBox) view.findViewById(R.id.contaminacaoAgua);
        contamincaoSolo = (CheckBox) view.findViewById(R.id.ContaminacaoSolo);
        contamincaoAr = (CheckBox) view.findViewById(R.id.contaminacaoAr);
        AguaQuant = (EditText)view.findViewById(R.id.contaminacao_agua);
        ArQuant = (EditText)view.findViewById(R.id.contaminacao_ar);
        SoloQuant = (EditText)view.findViewById(R.id.contaminacao_solo);
        danos_ambientais_observacoes = (EditText)view.findViewById(R.id.danos_ambientais_observacoes);

        contamincaoAr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    ArQuant.setVisibility(View.VISIBLE);
                }else{
                    ArQuant.setVisibility(View.INVISIBLE);
                }
            }
        });

        contamincaoSolo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SoloQuant.setVisibility(View.VISIBLE);
                }else{

                    SoloQuant.setVisibility(View.INVISIBLE);
                }
            }
        });

        contamincaoAgua.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    AguaQuant.setVisibility(View.VISIBLE);
                }else{
                    AguaQuant.setVisibility(View.INVISIBLE);
                }
            }
        });

        return view;
    }

    @Override
    public void getDados(JSONObject json) throws JSONException {








            if(!danos_ambientais_observacoes.getText().toString().isEmpty()){
                json.put("danos_ambientais_observacoes", danos_ambientais_observacoes.getText().toString());
            }else{
                json.put("danos_ambientais_observacoes", "Sem obeservações");
            }


            if(contamincaoAgua.isChecked()) {
                json.put("contaminacao_agua", AguaQuant.getText());
            }else{
                json.put("contaminacao_agua", 0);
            }




            if(contamincaoSolo.isChecked()) {

                json.put("contaminacao_solo", SoloQuant.getText());
            }else{
                json.put("contaminacao_solo", 0);
            }





            if(contamincaoAr.isChecked()) {

                json.put("contaminacao_ar", ArQuant.getText());
            }else{
                json.put("contaminacao_ar", 0);

            }




    }

    @Override
    public Boolean verficaDados(){

        if(contamincaoSolo.isChecked()){
            if(SoloQuant.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Você não informou a quantide de pessoas atingidas", Toast.LENGTH_LONG);

                SoloQuant.requestFocus();
                return false;
            }
        }

        if(contamincaoAr.isChecked()){
            if(ArQuant.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Você não informou a quantide de pessoas atingidas", Toast.LENGTH_LONG).show();

                ArQuant.requestFocus();
                return false;
            }
        }


        if(contamincaoAgua.isChecked()){
            if(AguaQuant.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Você não informou a quantide de pessoas atingidas", Toast.LENGTH_LONG).show();
                AguaQuant.requestFocus();
                return false;
            }
        }


        return true;

    }
}
