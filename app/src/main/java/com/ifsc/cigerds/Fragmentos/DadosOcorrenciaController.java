package com.ifsc.cigerds.Fragmentos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ifsc.cigerds.Classes.CheckBoxAdapter;
import com.ifsc.cigerds.Classes.CobradeCheckBoxAdapter;
import com.ifsc.cigerds.Classes.LocalizadorFused;
import com.ifsc.cigerds.Classes.SpinnerCheckBoxCidades;
import com.ifsc.cigerds.Classes.SpinnerCobradeCheckbox;
import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.MainActivity;
import com.ifsc.cigerds.R;

import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DadosOcorrenciaController extends Fragment implements DadosInterface {

    private CheckBoxAdapter municipiosAdapter;
    private CobradeCheckBoxAdapter cobradeAdapter;
    private Spinner municipioSpinner;
    private Spinner cobradesSpinner;
    private TextView dataTextview;
    private TextView dataDesastre;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private EditText endereco;
    private ArrayAdapter<CharSequence> coderecArray;
    private EditText cobrad;
    private TextView cobreadeDesc;
    private EditText descricao;
    private TextView logadoComo;
    private String data = null;
    private TextView hora;
    private String horaDesastre = null;

    public DadosOcorrenciaController(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final String NOME_PREFERENCE = "262114a72D&@5aa!!@FA";
        SharedPreferences prefs;
        prefs = this.getActivity().getSharedPreferences(NOME_PREFERENCE, getContext().MODE_PRIVATE);
        final String[] cidades = getResources().getStringArray(R.array.municipioCadastrados);
        final String[] cobradesProvi = getResources().getStringArray(R.array.cobrade);
        final String [] cobradesDef = new String[cobradesProvi.length];
        for(int cont = 0; cont < cobradesProvi.length; cont++){
            cobradesDef[cont] = cobradesProvi[cont].replaceAll(";", "\n");
        }

        ArrayList<SpinnerCobradeCheckbox> cobradeCheckBox = new ArrayList<>();


        for(int cont = 0; cont < cobradesDef.length; cont++){
            SpinnerCobradeCheckbox spinnerCheckBox = new SpinnerCobradeCheckbox();
            spinnerCheckBox.setCheck(false);
            spinnerCheckBox.setCobrade(cobradesDef[cont]);
            cobradeCheckBox.add(spinnerCheckBox);
        }

        ArrayList<SpinnerCheckBoxCidades> cidadesCheckBox = new ArrayList<>();

        for(int cont = 0; cont < cidades.length; cont++){
            SpinnerCheckBoxCidades spinnerCheckBox = new SpinnerCheckBoxCidades();
            spinnerCheckBox.setCheck(false);
            spinnerCheckBox.setCidade(cidades[cont]);
            cidadesCheckBox.add(spinnerCheckBox);
        }

        View view = inflater.inflate(R.layout.dados_ocorrencia_layout, container, false);
        logadoComo = (TextView)view.findViewById(R.id.logadoComo);
        logadoComo.setText("logado como: "+  prefs.getString("login", "0").toString());
        cobreadeDesc = (TextView) view.findViewById(R.id.descCOBRAD);
        dataTextview = (TextView)view.findViewById(R.id.data);
        dataDesastre = (TextView)view.findViewById(R.id.dataDesastre);
        descricao = (EditText)view.findViewById(R.id.descDesastre);
        endereco = (EditText)view.findViewById(R.id.endereco);
        hora = (TextView)view.findViewById(R.id.hora);
        cobradesSpinner = (Spinner)view.findViewById(R.id.cobrades);
        cobradeAdapter = new CobradeCheckBoxAdapter(getContext(),0, cobradeCheckBox);
        cobradesSpinner.setAdapter(cobradeAdapter);
        municipioSpinner = (Spinner)view.findViewById(R.id.municipios);
        municipiosAdapter = new CheckBoxAdapter(getContext(), 0, cidadesCheckBox);
       // municipiosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        municipioSpinner.setAdapter(municipiosAdapter);

       final Calendar calender = Calendar.getInstance();

       hora.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               int hour = calender.get(Calendar.HOUR_OF_DAY);
               int minutes = calender.get(Calendar.MINUTE);

               TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Widget_DeviceDefault_Light, timeSetListener, hour, minutes,true);
               timePickerDialog.show();
           }
       });

       timeSetListener = new TimePickerDialog.OnTimeSetListener() {
           @Override
           public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
               horaDesastre = hourOfDay + ":" + minute;
               hora.setText(horaDesastre);
           }
       };

        dataDesastre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ano = calender.get(Calendar.YEAR);
                int mes = calender.get(Calendar.MONTH);
                int dia = calender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Widget_DeviceDefault_Light, dateSetListener, ano, mes, dia);
                dialog.show();
            }
        });


        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                data = dayOfMonth + "-" + (month+1) + "-" + year;
                dataDesastre.setText(data);
            }
        };


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
           for(SpinnerCheckBoxCidades spinnerCheckBox : adapter.getList()){

               if(spinnerCheckBox.isCheck()){
                   municipios+=spinnerCheckBox.getCidade()+"\n ";
               }

           }

        CobradeCheckBoxAdapter adapterCidades = (CobradeCheckBoxAdapter)cobradesSpinner.getAdapter();
        String cobrades = "";
        String idsCobrades = "";
        String [] temp;
        for(SpinnerCobradeCheckbox spinnerCheckBox : adapterCidades.getList()){

            if(spinnerCheckBox.isCheck()){
                temp = spinnerCheckBox.getCobrade().split("\n");
                cobrades+= temp[0]+" ";
                idsCobrades += temp[1]+" ";
            }

        }

           json.put("cobrad", idsCobrades);
           json.put("municipios", municipios);
           json.put("descricaoDesastre", cobrades);
           json.put("data", dataTextview.getText().toString());
           json.put("endereco", endereco.getText().toString());
           json.put("descricao", descricao.getText().toString());
           json.put("dataDesastre", data+";"+horaDesastre);
           LocalizadorFused fused = new LocalizadorFused(getActivity());
           fused.getLocation();



    }

    @Override
    public String getResumo() {

        CheckBoxAdapter adapter = (CheckBoxAdapter)municipioSpinner.getAdapter();
        String municipios = "";
        for(SpinnerCheckBoxCidades spinnerCheckBox : adapter.getList()){

            if(spinnerCheckBox.isCheck()){
                municipios+=spinnerCheckBox.getCidade()+"\n";
            }

        }

        CobradeCheckBoxAdapter adapterCidades = (CobradeCheckBoxAdapter)cobradesSpinner.getAdapter();
        String cobrades = "";
        for(SpinnerCobradeCheckbox spinnerCheckBox : adapterCidades.getList()){

            if(spinnerCheckBox.isCheck()){
                cobrades+=spinnerCheckBox.getCobrade()+"\n ";
            }

        }


        String resumo = "";
        resumo+="DADOS DA OCORRENCIA"+"\n\n";
        resumo+="Cobrad: "+cobrades;
        resumo+="Descrição do desastre"+cobreadeDesc.getText() + "\n";
        resumo+="Municipios: "+ municipios + "\n";
        resumo+="Data: "+ data+" : " + horaDesastre +"\n";
        resumo+="Endereco: "+ endereco.getText().toString()+"\n";
        resumo+="Descricao: "+ descricao.getText().toString()+"\n";
        resumo+="\n\n";
        return resumo;
    }

    @Override
    public Boolean verficaDados() {

        CheckBoxAdapter adapter = (CheckBoxAdapter)municipioSpinner.getAdapter();
        String municipios = "";
        for(SpinnerCheckBoxCidades spinnerCheckBox : adapter.getList()){

            if(spinnerCheckBox.isCheck()){
                municipios+=spinnerCheckBox.getCidade()+"\n ";
            }

        }

        CobradeCheckBoxAdapter adapterCidades = (CobradeCheckBoxAdapter)cobradesSpinner.getAdapter();
        String cobrades = "";
        for(SpinnerCobradeCheckbox spinnerCheckBox : adapterCidades.getList()){

            if(spinnerCheckBox.isCheck()){
                cobrades+=spinnerCheckBox.getCobrade()+"\n ";
            }

        }


        if(cobrades.equals("")){
            Toast.makeText(getContext(), "Você não informou o(s) cobrade(s)", Toast.LENGTH_LONG).show();
            municipioSpinner.requestFocus();
            return false;
        }

        if(horaDesastre.equals(null)){
            Toast.makeText(getContext(), "Você não informou a Hora do desastre", Toast.LENGTH_LONG).show();
            hora.requestFocus();
            return false;
        }

        if(municipios.equals("")){
            Toast.makeText(getContext(), "Você não informou o(s) municipio(s)", Toast.LENGTH_LONG).show();
            municipioSpinner.requestFocus();
            return false;
        }


        if(data.equals(null)){
            Toast.makeText(getContext(), "Você não informou a Data do desastre", Toast.LENGTH_LONG).show();
            dataDesastre.requestFocus();
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
