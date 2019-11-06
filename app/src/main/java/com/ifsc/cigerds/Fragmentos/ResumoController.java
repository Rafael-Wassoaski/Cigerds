package com.ifsc.cigerds.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ifsc.cigerds.R;

import androidx.fragment.app.Fragment;

public class ResumoController extends Fragment  {

    private EditText resumo;
    private Button envio;

    public ResumoController() {
        // Required empty public constructor
    }

    public void setResumo(String resumoVistoria) {
        // Required empty public constructor
        resumo.setText(resumoVistoria);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.resumo_fragment, container, false);

        resumo = (EditText) view.findViewById(R.id.resumo);
        envio = (Button) view.findViewById(R.id.envio);





        return view;
    }


}
