package com.ifsc.cigerds.Fragmentos;

import android.app.Activity;
import android.content.Context;
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

import com.ifsc.cigerds.Classes.Network;
import com.ifsc.cigerds.DB.BancoController;
import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.R;
import com.ifsc.cigerds.Threads.ConexaoEnvio;
import com.ifsc.cigerds.Vistoria;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ResumoController extends Fragment implements DadosInterface {

    private EditText resumo;
    private Button envio;
    private String user;
    private String pass;
    private Context context;
    private JSONObject jsonEnviar;
    private List<DadosInterface> fragmentList;
    private Boolean encerrar = false;
    private String autor;

    public ResumoController() {
        // Required empty public constructor
    }

    public void setParametros(List<DadosInterface> fragmentList, String user, String pass, Context context, JSONObject jsonEnviar) {
        this.context = context;
        this.jsonEnviar = jsonEnviar;
        this.user = user;
        this.pass = pass;
        this.fragmentList = fragmentList;
        this.jsonEnviar = jsonEnviar;
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

        resumo = (EditText) view.findViewById(R.id.resumo);
        envio = (Button) view.findViewById(R.id.envio);

//        final BancoController bancoController = new BancoController(context);
//
//        if(Network.VerificaConexao(context)) {
//            ConexaoEnvio envio = new ConexaoEnvio(jsonEnviar, user, pass);
//            envio.execute();
//        }else {
//            bancoController.insereDados(jsonEnviar);
//
//        }


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
        return null;
    }

    @Override
    public Boolean verficaDados() {
        return null;
    }



}
