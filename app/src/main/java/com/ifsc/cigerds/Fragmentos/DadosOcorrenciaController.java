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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ifsc.cigerds.Classes.Localizador;
import com.ifsc.cigerds.Classes.LocalizadorFused;
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
    private EditText dataDesastre;
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
        dataDesastre = (EditText)view.findViewById(R.id.dataDesastre);
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
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");

        dataTextview.setText((String)dataFormat.format(dataAtual) +" " + (String)horaAtual.format(dataAtual));

        return view;
    }

    @Override
    public void getDados(JSONObject json) throws JSONException {



           json.put("cobrad", cobrad.getText().toString());
           json.put("municipio", municipioSpinner.getSelectedItem().toString());
           json.put("data", dataTextview.getText().toString());
           json.put("endereco", endereco.getText().toString());
           json.put("descricao", descricao.getText().toString());
           json.put("dataDesastre", dataDesastre.getText().toString());
           LocalizadorFused fused = new LocalizadorFused(getActivity());
           fused.getLocation();



    }

    @Override
    public String getResumo() {
        String resumo = "";
        resumo+="DADOS DA OCORRENCIA"+"\n\n";
        resumo+="Cobrad: "+cobrad.getText().toString()+"\n";
        resumo+="Municipio: "+ municipioSpinner.getSelectedItem().toString()+"\n";
        resumo+="Data: "+ dataDesastre.getText().toString()+"\n";
        resumo+="Endereco: "+ endereco.getText().toString()+"\n";
        resumo+="Descricao: "+ descricao.getText().toString()+"\n";
        resumo+="\n\n";
        return resumo;
    }

    @Override
    public Boolean verficaDados() {


        if(dataDesastre.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Você não informou a Data do desastre", Toast.LENGTH_LONG).show();
            dataDesastre.requestFocus();
            return false;
        }
        if(cobrad.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Você não informou o COBRAD", Toast.LENGTH_LONG).show();
            cobrad.requestFocus();
            return false;
        }

        if(endereco.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Você não informou o endereço da vistoria", Toast.LENGTH_LONG).show();
            endereco.requestFocus();
            return false;
        }

        if(descricao.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Você não informou a descrição da vistoria", Toast.LENGTH_LONG).show();
            descricao.requestFocus();
            return false;
        }

        return true;
    }



}
