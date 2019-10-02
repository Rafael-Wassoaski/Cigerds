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

public class IAHController extends Fragment implements DadosInterface {

    private EditText outrosDados;
    private CheckBox viasDesobistruidas;
    private CheckBox servicosEssenciais;

    private HashMap<CheckBox, EditText> iah = new HashMap<>();


    public IAHController() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.iah_fragment, container, false);

        iah.put((CheckBox)view.findViewById(R.id.cestas), (EditText)view.findViewById(R.id.iah_cestas_de_alimentos));
        iah.put((CheckBox)view.findViewById(R.id.aguaPotavel), (EditText)view.findViewById(R.id.iah_agua_potavel));
        iah.put((CheckBox)view.findViewById(R.id.colchoes), (EditText)view.findViewById(R.id.iah_colchoes));
        iah.put((CheckBox)view.findViewById(R.id.higiene), (EditText)view.findViewById(R.id.iah_kit_higiene_pessoal));
        iah.put((CheckBox)view.findViewById(R.id.limpeza), (EditText)view.findViewById(R.id.iah_kit_limpeza));
        iah.put((CheckBox)view.findViewById(R.id.telhas), (EditText)view.findViewById(R.id.iah_telhas));
        iah.put((CheckBox)view.findViewById(R.id.lona), (EditText)view.findViewById(R.id.iah_lona_plastica));
        iah.put((CheckBox)view.findViewById(R.id.outros), (EditText)view.findViewById(R.id.iah_fornecidos_outros_observacoes));
        viasDesobistruidas = (CheckBox)view.findViewById(R.id.iah_vias_publicas_totalmente_desobistruidas);
        servicosEssenciais = (CheckBox)view.findViewById(R.id.iah_reestabelecimento_servicos_essenciais);

        outrosDados = (EditText)view.findViewById(R.id.iah_fornecidos_outros_observacoes);
        outrosDados.setVisibility(View.INVISIBLE);




        for(Map.Entry<CheckBox, EditText> entrada : iah.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            editText.setVisibility(View.INVISIBLE);
        }


        for(Map.Entry<CheckBox, EditText> entrada : iah.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

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

        json.put("iah_vias_publicas_totalmente_desobistruidas", viasDesobistruidas.isChecked());
        json.put("iah_reestabelecimento_servicos_essenciais", servicosEssenciais.isChecked());


        for(Map.Entry<CheckBox, EditText> entrada : iah.entrySet()){

            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();



            if(checkBox.isChecked()){
                json.put(editText.getTag().toString(), editText.getText());
                if(checkBox.getTag().toString().equals("Outros")&& !outrosDados.getText().toString().isEmpty()){
                    json.put("iah_fornecidos_outros_observacoes", outrosDados.getText().toString());
                }else{
                    json.put("iah_fornecidos_outros_observacoes", "Sem Observações");
                }

            }else{
                json.put(editText.getTag().toString(), 0);
            }
        }
    }

    @Override
    public Boolean verficaDados() {




        for(Map.Entry<CheckBox, EditText> entrada : iah.entrySet()){



            CheckBox checkBox = entrada.getKey();
            final EditText editText = entrada.getValue();

            if(checkBox.getTag().toString().equals("Outros") && checkBox.isChecked() && outrosDados.getText().toString().isEmpty()){
                outrosDados.requestFocus();
                return false;
            }

            if(checkBox.isChecked() && editText.getText().toString().isEmpty()){
                editText.requestFocus();
                return  false;
            }
        }
        return true;
    }
}
