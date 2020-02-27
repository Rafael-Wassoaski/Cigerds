package com.ifsc.cigerds.Threads;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.ifsc.cigerds.Interfaces.AsyncInterface;
import com.ifsc.cigerds.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class ConexaoBuscaEndereco extends AsyncTask<String, String, JSONObject> {

    public AsyncInterface caller = null;

    public void setAsynkInterface(AsyncInterface caller){
        this.caller = caller;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        try{
        URL url = new URL("https://reverse.geocoder.ls.hereapi.com/6.2/reversegeocode.json?prox=" + strings[0] + "%2C" + strings[1] + "%2C250&mode=retrieveAddresses&maxresults=1&gen=9&apiKey=rEkWhE7t8QW8POlBaMhRYNo0ClZCjdQ59ob0EbhhoXA");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setDoOutput(true);
        conexao.setReadTimeout(5000);
        conexao.setConnectTimeout(5000);
        conexao.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String linha;
        while((linha =  reader.readLine()) != null){
            stringBuffer.append(linha);
        }

        return new JSONObject(stringBuffer.toString());
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onPostExecute(JSONObject json){
        try {
            caller.processFinish(json);
            Log.d("JSONENDERECO", json.getJSONObject("Response").getJSONArray("View").getJSONObject(0).
                    getJSONArray("Result").getJSONObject(0).getJSONObject("Location").getJSONObject("Address").getString("Label"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        caller.processFinish(json);
    }
}
