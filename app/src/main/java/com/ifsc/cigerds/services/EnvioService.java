package com.ifsc.cigerds.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ifsc.cigerds.Classes.Network;
import com.ifsc.cigerds.DB.BancoController;
import com.ifsc.cigerds.Threads.ConexaoEnvio;

public class EnvioService extends IntentService {

    private final String NOME_PREFERENCE = "262114a72D&@5aa!!@FA";
    private SharedPreferences prefs;


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public EnvioService() {
        super("EnvioService");
    }

    public EnvioService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super().
        // If a Context object is needed, call getApplicationContext() here.


    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d("SERVICO", "iniciou");
        prefs = getSharedPreferences(NOME_PREFERENCE, MODE_PRIVATE);
        BancoController bancoController = new BancoController(getApplicationContext());
        if(bancoController.checkData()!=null){

            while (!Network.VerificaConexao(getApplicationContext())){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Log.d("SERVICO", "Entrou");
            bancoController.createJSON(prefs.getString("login", null),  prefs.getString("password", null));


        }
    }
}
