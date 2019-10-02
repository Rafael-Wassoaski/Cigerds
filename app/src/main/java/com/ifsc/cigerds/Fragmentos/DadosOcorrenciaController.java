package com.ifsc.cigerds.Fragmentos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DadosOcorrenciaController extends Fragment implements DadosInterface {

    private ArrayAdapter<CharSequence> municipiosArray;
    private Spinner municipioSpinner;
    private TextView dataTextview;
    private EditText endereco;
    private ArrayAdapter<CharSequence> coderecArray;
    private EditText cobrad;
    private EditText descricao;


    public DadosOcorrenciaController(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dados_ocorrencia_layout, container, false);


        cobrad = (EditText)view.findViewById(R.id.cobrad);
        dataTextview = (TextView)view.findViewById(R.id.data);
        descricao = (EditText)view.findViewById(R.id.descDesastre);
        endereco = (EditText)view.findViewById(R.id.endereco);
        municipioSpinner = (Spinner)view.findViewById(R.id.municipio);
        municipiosArray = ArrayAdapter.createFromResource(getContext(), R.array.municipioCadastrados, android.R.layout.simple_spinner_item);
        municipiosArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        municipioSpinner.setAdapter(municipiosArray);



        Calendar calender = Calendar.getInstance();
        Date data = new Date();

        calender.setTime(data);
        Date dataAtual = calender.getTime();
        SimpleDateFormat horaAtual = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");

        dataTextview.setText((String)dataFormat.format(dataAtual) +" : " + (String)horaAtual.format(dataAtual));

        return view;
    }

    @Override
    public void getDados(JSONObject json) throws JSONException {

        Log.d("Json", "a");

        json.put("cobrad", cobrad.getText().toString());
        json.put("municipio", municipioSpinner.getSelectedItem().toString());
        json.put("data", dataTextview.getText().toString());
        json.put("endereco", endereco.getText().toString());


        if(descricao.getText().toString().isEmpty()){
            json.put("descricao", descricao.getText().toString());
        }


    }

    @Override
    public Boolean verficaDados() {


        if(cobrad.getText().toString().isEmpty()){
            cobrad.requestFocus();
            return false;
        }

        if(endereco.getText().toString().isEmpty()){
            endereco.requestFocus();
            return false;
        }




        return true;
    }
}
