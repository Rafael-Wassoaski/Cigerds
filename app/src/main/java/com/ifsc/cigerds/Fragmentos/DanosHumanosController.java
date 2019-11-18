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

public class DanosHumanosController extends Fragment implements DadosInterface {

    private List<AdaptadorCheckBox> lista;
    private List<String> nameTags;
    private EditText danos_humanos_observacoes;

    public DanosHumanosController(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lista = new ArrayList<>();
        nameTags = new ArrayList<>();
        View view = inflater.inflate(R.layout.danos_humanos_fragment, container, false);
        nameTags.add("danos_humanos_desalojados");
        nameTags.add("danos_humanos_desabrigados");
        nameTags.add("danos_humanos_desaparecidos");
        nameTags.add("danos_humanos_feridos");
        nameTags.add("danos_humanos_enfermos");
        nameTags.add("danos_humanos_mortos");
        nameTags.add("danos_humanos_isolados");
        nameTags.add("danos_humanos_atingidos");
        nameTags.add("danos_humanos_afetados");

        danos_humanos_observacoes = view.findViewById(R.id.danos_humanos_observacoes);
        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.desalojados),  (EditText)view.findViewById(R.id.danos_humanos_desalojados)));
        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.desabrigados), (EditText)view.findViewById(R.id.danos_humanos_desabrigados)));
        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.desaparecidos),  (EditText)view.findViewById(R.id.danos_humanos_desaparecidos)));
        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.feridos), (EditText)view.findViewById(R.id.danos_humanos_feridos)));
        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.enfermos), (EditText)view.findViewById(R.id.danos_humanos_enfermos)));
        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.mortos), (EditText)view.findViewById(R.id.danos_humanos_mortos)));
        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.isolados), (EditText)view.findViewById(R.id.danos_humanos_isolados)));
        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.atingidos), (EditText)view.findViewById(R.id.danos_humanos_atingidos)));
        lista.add(new AdaptadorCheckBox((CheckBox)view.findViewById(R.id.afetados), (EditText)view.findViewById(R.id.danos_humanos_afetados)));

        for(AdaptadorCheckBox entrada : lista){

            final EditText editText = entrada.getEditText();

            editText.setVisibility(View.INVISIBLE);
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


        }

    @Override
    public String getResumo() {
        String resumo="DANOS HUMANOS\n\n";

        if(!danos_humanos_observacoes.getText().toString().isEmpty()){
            resumo+="Observações: "+danos_humanos_observacoes.getText().toString()+"\n";
        }else{
            resumo+="Observações: Sem obeservações\n";
        }


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
