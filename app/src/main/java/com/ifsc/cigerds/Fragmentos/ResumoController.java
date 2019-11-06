package com.ifsc.cigerds.Fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ifsc.cigerds.Classes.Network;
import com.ifsc.cigerds.DB.BancoController;
import com.ifsc.cigerds.R;
import com.ifsc.cigerds.Threads.ConexaoEnvio;
import com.ifsc.cigerds.Vistoria;

import org.json.JSONObject;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class ResumoController extends Fragment  {

    private EditText resumo;
    private Button envio;
    private String user;
    private String pass;
    private Context context;
    private JSONObject jsonEnviar;

    public ResumoController() {
        // Required empty public constructor
    }

    public void setResumo(String resumoVistoria, String user, String pass, Context context, JSONObject jsonEnviar) {
        this.context = context;
        this.jsonEnviar = jsonEnviar;
        this.user = user;
        this.pass = pass;
        resumo.setText(resumoVistoria);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.resumo_fragment, container, false);

        resumo = (EditText) view.findViewById(R.id.resumo);
        envio = (Button) view.findViewById(R.id.envio);


        final BancoController bancoController = new BancoController(context);

        if(Network.VerificaConexao(context)) {
            ConexaoEnvio envio = new ConexaoEnvio(jsonEnviar, user, pass);
            envio.execute();
        }else {
            bancoController.insereDados(jsonEnviar);

        }



        return view;
    }


}
