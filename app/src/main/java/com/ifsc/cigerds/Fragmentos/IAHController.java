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

import com.ifsc.cigerds.Classes.AdaptadorCheckBox;
import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IAHController extends Fragment implements DadosInterface {

    private EditText outrosDados;
    private CheckBox viasDesobistruidas;
    private CheckBox servicosEssenciais;


    private List<AdaptadorCheckBox> iah = new ArrayList<>();
    private List<String> nameTag;

    public IAHController() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.iah_fragment, container, false);
        nameTag = new ArrayList<>();
        nameTag.add("iah");
        nameTag.add("desobistrucaoVias");
        nameTag.add("reestabelecimentoServicos");

        iah.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.iah_fornecidos), (EditText)view.findViewById(R.id.iah_iah_fornecidos)));
        iah.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.desobstrucao), (EditText)view.findViewById(R.id.iah_vias_desobistruidas)));
        iah.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.servicos_essenciais), (EditText)view.findViewById(R.id.iah_reestabelecimento_servicos)));

        for(AdaptadorCheckBox entrada : iah){

            final EditText editText = entrada.getEditText();

            editText.setVisibility(View.INVISIBLE);
        }


        for(AdaptadorCheckBox entrada : iah){

            CheckBox checkBox = entrada.getCheckBox();
            final EditText editText = entrada.getEditText();

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {



                    if(compoundButton.isChecked()){
                        editText.setVisibility(View.VISIBLE);


                        if(compoundButton.getText().toString().equals("Outros")){
                            outrosDados.setVisibility(View.VISIBLE);
                       }
                    }else{
                        editText.setVisibility(View.INVISIBLE);
                        if(compoundButton.getText().toString().equals("Outros")){
                            outrosDados.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            });
        }


        return view;
    }


    @Override
    public void getDados(JSONObject json) throws JSONException {


        Integer count = 0;
        for(AdaptadorCheckBox entrada : iah){

            CheckBox checkBox = entrada.getCheckBox();
            final EditText editText = entrada.getEditText();



            if(checkBox.isChecked()){
                json.put(nameTag.get(count), editText.getText().toString());

            }else{
                json.put(nameTag.get(count), 0);
            }

            count++;
        }
    }

    @Override
    public String getResumo() {

        String resumo = "IAH\n\n";

        for(AdaptadorCheckBox entrada : iah){

            CheckBox checkBox = entrada.getCheckBox();
            final EditText editText = entrada.getEditText();

            if(checkBox.isChecked()){
                resumo+= checkBox.getText().toString()+ ": "+ editText.getText()+"\n";

            }else{
                resumo+= checkBox.getText().toString()+ ": 0\n";
            }

        }
        return resumo;
    }

    @Override
    public Boolean verficaDados() {

        for(AdaptadorCheckBox entrada : iah){

            CheckBox checkBox = entrada.getCheckBox();
            final EditText editText = entrada.getEditText();


            if(checkBox.isChecked() && editText.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Você não informou uma opção marcada ", Toast.LENGTH_LONG).show();
                editText.requestFocus();
                return  false;
            }
        }
        return true;
    }
}
