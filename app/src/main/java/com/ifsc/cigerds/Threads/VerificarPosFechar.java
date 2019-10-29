package com.ifsc.cigerds.Threads;

import android.content.Context;
import android.os.AsyncTask;

import com.ifsc.cigerds.Classes.Network;
import com.ifsc.cigerds.DB.BancoController;

public class VerificarPosFechar extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String user;
    private String pass;
    public VerificarPosFechar(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {


        while(!Network.VerificaConexao(context)){

            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        BancoController bancoController = new BancoController(context);
        ConexaoEnvio conexaoEnvio = new ConexaoEnvio(bancoController.createJSON(), user, pass);



        return null;
    }
}
