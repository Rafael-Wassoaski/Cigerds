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

public class DanosMateriaisController extends Fragment implements DadosInterface {

    private HashMap<CheckBox, EditText> materiais = new HashMap<>();
    private EditText danos_materiais_observacoes;
    private List<String> nameTag;

    public DanosMateriaisController(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.danos_materiais_fragment, container, false);
        nameTag = new ArrayList<>();

        nameTag.add("unidades_habitacionais_atingidas");
        nameTag.add("unidades_habitacionais_danificads");
        nameTag.add("unidades_habitacionais_interditadas");
        nameTag.add("unidades_habitacionais_destruidas");

        nameTag.add("instalacoes_publicas_saude_atingidas");
        nameTag.add("instalacoes_comunitarias_atingidas");
        nameTag.add("instalacoes_publicas_ensino_atingidas");
        nameTag.add("obras_atingidas");
        nameTag.add("interrupcoes_servicos_essenciais");


        danos_materiais_observacoes = (EditText)view.findViewById(R.id.danos_materiais_observacoes );

        materiais.put((CheckBox)view.findViewById(R.id.atingidos), (EditText)view.findViewById(R.id.unidades_habitacionais_atingidas));
        materiais.put((CheckBox)view.findViewById(R.id.danificadas), (EditText)view.findViewById(R.id.unidades_habitacionais_danificads));
        materiais.put((CheckBox)view.findViewById(R.id.interditadas), (EditText)view.findViewById(R.id.unidades_habitacionais_interditadas));
        materiais.put((CheckBox)view.findViewById(R.id.destruidas), (EditText)view.findViewById(R.id.unidades_habitacionais_destruidas));
        materiais.put((CheckBox)view.findViewById(R.id.instalacaoSaude), (EditText)view.findViewById(R.id.instalacoes_publicas_saude_atingidas));
        materiais.put((CheckBox)view.findViewById(R.id.instalacoesComunitarias), (EditText)view.findViewById(R.id.instalacoes_comunitarias_atingidas));
        materiais.put((CheckBox)view.findViewById(R.id.instalacoesEnsino), (EditText)view.findViewById(R.id.instalacoes_publicas_ensino_atingidas));
        materiais.put((CheckBox)view.findViewById(R.id.obrasPublicas), (EditText)view.findViewById(R.id.obras_atingidas));
        materiais.put((CheckBox)view.findViewById(R.id.interrupcaoServicos), (EditText)view.findViewById(R.id.interrupcoes_servicos_essenciais));


        for(Map.Entry<CheckBox, EditText> entrada : materiais.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            editText.setVisibility(View.INVISIBLE);
        }


        for(Map.Entry<CheckBox, EditText> entrada : materiais.entrySet()){

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
        if(!danos_materiais_observacoes.getText().toString().isEmpty()){
            json.put("danos_materiais_observacoes", danos_materiais_observacoes.getText().toString());
        }else{
            json.put("danos_materiais_observacoes", "Sem obeservações");
        }

        for(Map.Entry<CheckBox, EditText> entrada : materiais.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            if(checkBox.isChecked()){
                json.put(nameTag.get(count), editText.getText());

            }else{
                json.put(nameTag.get(count), 0);
            }
            count++;
        }
    }

    @Override
    public String getResumo() {
        return null;
    }

    @Override
    public Boolean verficaDados() {



        for(Map.Entry<CheckBox, EditText> entrada : materiais.entrySet()){

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
