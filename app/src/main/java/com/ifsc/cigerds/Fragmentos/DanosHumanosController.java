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

public class DanosHumanosController extends Fragment implements DadosInterface {

    private Map<CheckBox, EditText> lista;

    private EditText danos_humanos_observacoes;

    public DanosHumanosController(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lista = new HashMap<>();
        View view = inflater.inflate(R.layout.danos_humanos_fragment, container, false);

        danos_humanos_observacoes = (EditText)view.findViewById(R.id.danos_humanos_observacoes);
        lista.put((CheckBox)view.findViewById(R.id.desalojados),  (EditText)view.findViewById(R.id.danos_humanos_desalojados));
        lista.put((CheckBox)view.findViewById(R.id.desabrigados), (EditText)view.findViewById(R.id.danos_humanos_desabrigados));
        lista.put((CheckBox)view.findViewById(R.id.desaparecidos),  (EditText)view.findViewById(R.id.danos_humanos_desaparecidos));
        lista.put((CheckBox)view.findViewById(R.id.feridos), (EditText)view.findViewById(R.id.danos_humanos_feridos));
        lista.put((CheckBox)view.findViewById(R.id.enfermos), (EditText)view.findViewById(R.id.danos_humanos_enfermos));
        lista.put((CheckBox)view.findViewById(R.id.mortos), (EditText)view.findViewById(R.id.danos_humanos_mortos));
        lista.put((CheckBox)view.findViewById(R.id.isolados), (EditText)view.findViewById(R.id.danos_humanos_isolados));
        lista.put((CheckBox)view.findViewById(R.id.atingidos), (EditText)view.findViewById(R.id.danos_humanos_atingidos));
        lista.put((CheckBox)view.findViewById(R.id.afetados), (EditText)view.findViewById(R.id.afetadosQuant));

        for(Map.Entry<CheckBox, EditText> entrada : lista.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            editText.setVisibility(View.INVISIBLE);
        }


        for(Map.Entry<CheckBox, EditText> entrada : lista.entrySet()){

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

        if(!danos_humanos_observacoes.getText().toString().isEmpty()){
            json.put("danos_humanos_observacoes", danos_humanos_observacoes.getText().toString());
        }else{
            json.put("danos_humanos_observacoes", "Sem obeservações");
        }

        for(Map.Entry<CheckBox, EditText> entrada : lista.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            if(checkBox.isChecked()){
                json.put(editText.getTag().toString(), editText.getText());

            }else{
                json.put(editText.getTag().toString(), 0);
            }
        }
    }

    @Override
    public Boolean verficaDados() {



        for(Map.Entry<CheckBox, EditText> entrada : lista.entrySet()){

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
