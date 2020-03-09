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
import java.util.List;

public class DanosHumanosController extends Fragment implements DadosInterface {

    private List<AdaptadorCheckBox> lista;
    private List<String> nameTags;
    private EditText danos_humanos_observacoes;
    private EditText danos_humanos_quantidade_afetados;
    private EditText danos_humanos_total_habitantes;

    public DanosHumanosController(){}

    private void calcularAfetados(List<AdaptadorCheckBox> lista){

        Integer totalAfetados = 0;

        for(AdaptadorCheckBox adaptadorCheckBox : lista){
            if(adaptadorCheckBox.getCheckBox().isChecked()){
                String valor = adaptadorCheckBox.getEditText().getText().toString();
                if(valor.equals("")){
                    totalAfetados+= 0;
                }else{
                    totalAfetados += Integer.valueOf(valor);
                }

            }
        }
        danos_humanos_quantidade_afetados.setText(totalAfetados.toString());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lista = new ArrayList<>();
        nameTags = new ArrayList<>();
        View view = inflater.inflate(R.layout.danos_humanos_fragment, container, false);
        nameTags.add("quantidadeDesalojados");
        nameTags.add("quantiadeObitos");
        nameTags.add("quantiadePopuIsolada");


        danos_humanos_quantidade_afetados = view.findViewById(R.id.danos_humanos_quantidade_afetados);
        danos_humanos_total_habitantes = view.findViewById(R.id.danos_humnanos_total_habitantes);

        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.desalojados),  (EditText)view.findViewById(R.id.danos_humanos_desalojados)));
        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.obitos), (EditText)view.findViewById(R.id.danos_humanos_obitios)));
        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.isolados), (EditText)view.findViewById(R.id.danos_humanos_isolados)));

        for(AdaptadorCheckBox entrada : lista){

            final EditText editText = entrada.getEditText();

            editText.setVisibility(View.INVISIBLE);
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        calcularAfetados(lista);
                    }
                }
            });
        }


        for(AdaptadorCheckBox entrada : lista){

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

        Integer count = 0;


            if (!danos_humanos_observacoes.getText().toString().isEmpty()) {
                json.put("danos_humanos_observacoes", danos_humanos_observacoes.getText().toString());
            } else {
                json.put("danos_humanos_observacoes", "Sem obeservações");
            }

        for(AdaptadorCheckBox entrada : lista){

                CheckBox checkBox = entrada.getCheckBox();
                final EditText editText = entrada.getEditText();

                if(checkBox.isChecked()){
                    Log.d("NOME", nameTags.get(count) + " "+count);
                    json.put(nameTags.get(count),Integer.parseInt(editText.getText().toString()));

                }else{
                    json.put(nameTags.get(count), 0);
                }
                count++;
            }
        Float populacaoTotal = Float.valueOf(danos_humanos_total_habitantes.getText().toString());
        Float quantidadeAfetados = Float.valueOf((danos_humanos_quantidade_afetados.getText().toString()));
        Float porcentagemAfetados = (quantidadeAfetados * populacaoTotal) / 100;

        json.put("populacaoAfetada", danos_humanos_quantidade_afetados.getText().toString());
        json.put("pocentagemPopulacaoAfetada", porcentagemAfetados);


        }

    @Override
    public String getResumo() {
        String resumo="DANOS HUMANOS\n\n";


        for(AdaptadorCheckBox entrada : lista){

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



        for(AdaptadorCheckBox entrada : lista){

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
