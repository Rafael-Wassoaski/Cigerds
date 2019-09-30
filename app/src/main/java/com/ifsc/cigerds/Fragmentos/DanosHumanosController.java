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

import java.util.HashMap;
import java.util.Map;

public class DanosHumanosController extends Fragment implements DadosInterface {

    private Map<CheckBox, EditText> lista;

    public DanosHumanosController(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lista = new HashMap<>();
        View view = inflater.inflate(R.layout.danos_humanos_fragment, container, false);

        lista.put((CheckBox)view.findViewById(R.id.desalojados),  (EditText)view.findViewById(R.id.desalojadosQuant));
        lista.put((CheckBox)view.findViewById(R.id.desabrigados), (EditText)view.findViewById(R.id.desabrigadosQuant));
        lista.put((CheckBox)view.findViewById(R.id.desaparecidos),  (EditText)view.findViewById(R.id.desaparecidosQuant));
        lista.put((CheckBox)view.findViewById(R.id.feridos), (EditText)view.findViewById(R.id.feridosQuant));
        lista.put((CheckBox)view.findViewById(R.id.enfermos), (EditText)view.findViewById(R.id.enfermosQuant));
        lista.put((CheckBox)view.findViewById(R.id.mortos), (EditText)view.findViewById(R.id.mortosQuant));
        lista.put((CheckBox)view.findViewById(R.id.isolados), (EditText)view.findViewById(R.id.isoladosQuant));
        lista.put((CheckBox)view.findViewById(R.id.atingidos), (EditText)view.findViewById(R.id.atingidosQuant));
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
    public String getDados() {
        return null;
    }

    @Override
    public Boolean verficaDados() {
        return null;
    }
}
