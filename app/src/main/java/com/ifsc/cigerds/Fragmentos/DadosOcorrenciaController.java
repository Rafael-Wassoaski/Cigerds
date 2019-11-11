package com.ifsc.cigerds.Fragmentos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ifsc.cigerds.Classes.CheckBoxAdapter;
import com.ifsc.cigerds.Classes.Localizador;
import com.ifsc.cigerds.Classes.LocalizadorFused;
import com.ifsc.cigerds.Classes.SpinnerCheckBox;
import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.R;

import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DadosOcorrenciaController extends Fragment implements DadosInterface, TextWatcher {

    private CheckBoxAdapter municipiosAdapter;
    private Spinner municipioSpinner;
    private TextView dataTextview;
    private EditText dataDesastre;
    private EditText endereco;
    private ArrayAdapter<CharSequence> coderecArray;
    private EditText cobrad;
    private TextView cobreadeDesc;
    private EditText descricao;
    private TextView logadoComo;


    public DadosOcorrenciaController(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final String NOME_PREFERENCE = "262114a72D&@5aa!!@FA";
        SharedPreferences prefs;
        prefs = this.getActivity().getSharedPreferences(NOME_PREFERENCE, getContext().MODE_PRIVATE);
        final String[] cidades = getResources().getStringArray(R.array.municipioCadastrados);
        ArrayList<SpinnerCheckBox> cidadesCheckBox = new ArrayList<>();

        for(int cont = 0; cont < cidades.length; cont++){
            SpinnerCheckBox spinnerCheckBox = new SpinnerCheckBox();
            spinnerCheckBox.setCheck(false);
            spinnerCheckBox.setCidade(cidades[cont]);
            cidadesCheckBox.add(spinnerCheckBox);
        }

        View view = inflater.inflate(R.layout.dados_ocorrencia_layout, container, false);
        logadoComo = (TextView)view.findViewById(R.id.logadoComo);
        logadoComo.setText("logado como: "+  prefs.getString("login", "0").toString());
        cobrad = (EditText)view.findViewById(R.id.cobrad);
        cobreadeDesc = (TextView) view.findViewById(R.id.descCOBRAD);
        dataTextview = (TextView)view.findViewById(R.id.data);
        dataDesastre = (EditText)view.findViewById(R.id.dataDesastre);
        descricao = (EditText)view.findViewById(R.id.descDesastre);
        endereco = (EditText)view.findViewById(R.id.endereco);
        municipioSpinner = (Spinner)view.findViewById(R.id.municipio);
        municipiosAdapter = new CheckBoxAdapter(getContext(), 0, cidadesCheckBox);
       // municipiosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        municipioSpinner.setAdapter(municipiosAdapter);


        cobrad.addTextChangedListener(this);

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
          CheckBoxAdapter adapter = (CheckBoxAdapter)municipioSpinner.getAdapter();
          String municipios = "";
           for(SpinnerCheckBox spinnerCheckBox : adapter.getList()){

               if(spinnerCheckBox.isCheck()){
                   municipios+=spinnerCheckBox.getCidade()+"\n ";
               }

           }

           json.put("cobrad", cobrad.getText().toString());
           json.put("municipios", municipios);
           json.put("data", dataTextview.getText().toString());
           json.put("endereco", endereco.getText().toString());
           json.put("descricao", descricao.getText().toString());
           json.put("dataDesastre", dataDesastre.getText().toString());
           LocalizadorFused fused = new LocalizadorFused(getActivity());
           fused.getLocation();



    }

    @Override
    public String getResumo() {

        CheckBoxAdapter adapter = (CheckBoxAdapter)municipioSpinner.getAdapter();
        String municipios = "";
        for(SpinnerCheckBox spinnerCheckBox : adapter.getList()){

            if(spinnerCheckBox.isCheck()){
                municipios+=spinnerCheckBox.getCidade()+"\n";
            }

        }
        String resumo = "";
        resumo+="DADOS DA OCORRENCIA"+"\n\n";
        resumo+="Cobrad: "+cobrad.getText().toString()+ " " + cobreadeDesc.getText().toString() + "\n";
        resumo+="Municipios: "+ municipios + "\n";
        resumo+="Data: "+ dataDesastre.getText().toString()+"\n";
        resumo+="Endereco: "+ endereco.getText().toString()+"\n";
        resumo+="Descricao: "+ descricao.getText().toString()+"\n";
        resumo+="\n\n";
        return resumo;
    }

    @Override
    public Boolean verficaDados() {

        CheckBoxAdapter adapter = (CheckBoxAdapter)municipioSpinner.getAdapter();
        String municipios = "";
        for(SpinnerCheckBox spinnerCheckBox : adapter.getList()){

            if(spinnerCheckBox.isCheck()){
                municipios+=spinnerCheckBox.getCidade()+"\n ";
            }

        }

        if(municipios.equals("")){
            Toast.makeText(getContext(), "Você não informou o(s) municipio(s)", Toast.LENGTH_LONG).show();
            municipioSpinner.requestFocus();
            return false;
        }


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

        String [] arrayCOBRADE = getContext().getResources().getStringArray(R.array.cobrade);
        String [] cobrede = new String[2];
        int cont;
        for(cont = 0; cont < arrayCOBRADE.length; cont++){

            cobrede = arrayCOBRADE[cont].split(";");

            if(cobrad.getText().toString().equals(cobrede[1])){
                break;

            }
        }

        if(cont >= arrayCOBRADE.length){
            Toast.makeText(getContext(), "Cobrad invállido", Toast.LENGTH_LONG).show();
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


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String [] arrayCOBRADE = getContext().getResources().getStringArray(R.array.cobrade);
        String [] cobrede = new String[2];
        for(int cont = 0; cont < arrayCOBRADE.length; cont++){

            cobrede = arrayCOBRADE[cont].split(";");

            if(cobrad.getText().toString().equals(cobrede[1])){
                cobreadeDesc.setText(cobrede[0]);
                return;
            }

        }

        cobrad.setError("COBRADE inválido");

    }
}
