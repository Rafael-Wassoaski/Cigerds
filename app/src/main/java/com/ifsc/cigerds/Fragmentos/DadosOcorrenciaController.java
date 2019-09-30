package com.ifsc.cigerds.Fragmentos;

import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DadosOcorrenciaController extends Fragment implements DadosInterface {

    private ArrayAdapter<CharSequence> municipiosArray;
    private Spinner municipioSpinner;
    private TextView data;
    private TextView hora;
    private ArrayAdapter<CharSequence> coderecArray;
    private EditText cobrad;
    private EditText descricao;


    public DadosOcorrenciaController(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dados_ocorrencia_layout, container, false);


        cobrad = (EditText)view.findViewById(R.id.COBRAD);
        descricao = (EditText)view.findViewById(R.id.descDesastre);
        data = (TextView)view.findViewById(R.id.data);
        hora = (TextView)view.findViewById(R.id.hora);
        municipioSpinner = (Spinner)view.findViewById(R.id.municipio);
        municipiosArray = ArrayAdapter.createFromResource(getContext(), R.array.municipioCadastrados, android.R.layout.simple_spinner_item);
        municipiosArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        municipioSpinner.setAdapter(municipiosArray);



        Calendar calender = Calendar.getInstance();
        Date data1 = new Date();

        calender.setTime(data1);
        Date dataAtual = calender.getTime();
        SimpleDateFormat horaAtual = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");

        data.setText((String)dataFormat.format(dataAtual));
        hora.setText((String)horaAtual.format(dataAtual));
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
