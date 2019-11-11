package com.ifsc.cigerds.Fragmentos;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ifsc.cigerds.Classes.Network;
import com.ifsc.cigerds.DB.BancoController;
import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.R;
import com.ifsc.cigerds.Threads.ConexaoEnvio;
import com.ifsc.cigerds.Vistoria;
import com.ifsc.cigerds.main.SectionsPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ResumoController extends Fragment implements DadosInterface {

    private TextView resumo;
    private Button atualiazar;
    private String user;
    private String pass;
    private Context context;
    private List<DadosInterface> fragmentList = null;
    private SectionsPagerAdapter sectionsPagerAdapter = null;


    public ResumoController() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();

            fragmentList = Vistoria.getFragments();
            setResumo(getResumo());

    }

    @Override
    public void onPause() {
        super.onPause();

            fragmentList = Vistoria.getFragments();
            setResumo(getResumo());

    }

    public void setParametros(SectionsPagerAdapter sectionsPagerAdapter, String user, String pass, Context context) {
        this.context = context;
        this.user = user;
        this.pass = pass;
        this.sectionsPagerAdapter = sectionsPagerAdapter;
    }

    public void setResumo(String resumoString){
        if(resumo!=null){
            resumo.setText(resumoString);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.resumo_fragment, container, false);
        resumo = (TextView) view.findViewById(R.id.resumo);
        atualiazar = (Button) view.findViewById(R.id.atualizar);

        atualiazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResumo(getResumo());
            }
        });
        final BancoController bancoController = new BancoController(context);





        Handler mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {

            }
        };

        return view;
    }


    @Override
    public void getDados(JSONObject json) throws JSONException {

    }

    @Override
    public String getResumo() {
        String resumo = "";

        for(DadosInterface frgamento : fragmentList){
            resumo += frgamento.getResumo();
        }

        return resumo;
    }

    @Override
    public Boolean verficaDados() {
        return true;
    }



}
