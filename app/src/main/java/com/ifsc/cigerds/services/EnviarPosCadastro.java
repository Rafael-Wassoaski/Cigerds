package com.ifsc.cigerds.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ifsc.cigerds.Classes.Network;
import com.ifsc.cigerds.DB.BancoController;
import com.ifsc.cigerds.Threads.ConexaoEnvio;

public class EnviarPosCadastro extends Service {
    private final String NOME_PREFERENCE = "262114a72D&@5aa!!@FA";
    private SharedPreferences prefs;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null; }

    @Override
    public void onCreate() {
        BancoController bancoController = new BancoController(getApplicationContext());
        if(bancoController.checkData()!=null){

            while (!Network.VerificaConexao(getApplicationContext())){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ConexaoEnvio conexaoEnvio = new ConexaoEnvio(bancoController.createJSON(), prefs.getString("login", null),  prefs.getString("password", null));
            conexaoEnvio.execute();

        }

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        Log.d("Atividade", "b");
    }
}
