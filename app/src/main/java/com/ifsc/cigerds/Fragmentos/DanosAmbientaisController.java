package com.ifsc.cigerds.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.R;

public class DanosAmbientaisController extends Fragment  implements DadosInterface {

    private CheckBox contamincaoAgua;
    private CheckBox contamincaoSolo;
    private CheckBox contamincaoAr;
    private EditText AguaQuant;
    private EditText SoloQuant;
    private EditText ArQuant;


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
        AguaQuant = (EditText)view.findViewById(R.id.AguaQuantidade);
        ArQuant = (EditText)view.findViewById(R.id.quantidadeAr);
        SoloQuant = (EditText)view.findViewById(R.id.quantidadeSolo);

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
    public String getDados() {
        return null;
    }

    @Override
    public Boolean verficaDados() {
        return null;
    }
}
