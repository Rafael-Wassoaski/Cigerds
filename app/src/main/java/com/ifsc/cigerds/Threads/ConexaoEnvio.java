package com.ifsc.cigerds.Threads;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.ifsc.cigerds.MainActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import static android.content.Context.MODE_PRIVATE;

public class ConexaoEnvio extends AsyncTask<Void, Void, Void> {


    private JSONObject json;
    private String user;
    private String pass;

    public ConexaoEnvio(JSONObject json, String user, String pass){
        this.json = json;
        this.user = user;
        this.pass = pass;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {

            Log.d("Exep", "entrou");
            URL url = new URL(MainActivity.PROVEDOR+"cadastrovistoria/");
            Log.d("ENVIOO", json.toString());
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,null, new java.security.SecureRandom());
            conn.setSSLSocketFactory(sslContext.getSocketFactory());

            String userpass = user+":"+pass;
            String autorizacao = "Basic "+ Base64.encodeToString(userpass.getBytes(), Base64.DEFAULT);
            conn.setRequestProperty("Authorization", autorizacao);

            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            writer.write(json.toString());
            writer.flush();
            writer.close();
            outputStream.close();

            conn.connect();

            int responseCode=conn.getResponseCode();
            String responseString = "";


            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line = null;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    responseString+=line;

                }
            }
            else {
                responseString="";

            }

            Log.d("Exep", responseString);


        } catch (MalformedURLException e) {
            Log.d("Exep", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("Exep", e.getMessage());
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }


        return null;
    }
}
