package com.ifsc.cigerds.Threads;

import android.os.AsyncTask;

import com.ifsc.cigerds.MainActivity;

import org.json.JSONObject;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConexaoEnvio extends AsyncTask<Void, Void, Void> {


    private JSONObject json;

    public ConexaoEnvio(JSONObject json){
        this.json = json;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL(MainActivity.PROVEDOR+"cadastrovistoria/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
            dataOutputStream.writeBytes(json.toString());

            dataOutputStream.flush();
            dataOutputStream.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
