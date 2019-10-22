package com.ifsc.cigerds.Threads;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.ifsc.cigerds.Interfaces.AsyncInterface;
import com.ifsc.cigerds.MainActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class ConexaoLogin extends AsyncTask<Boolean, JSONObject, JSONObject> {

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }


    public AsyncInterface resultBoolean = null;
    private HashMap<String, String> postDataParams;
    public ConexaoLogin(String name, String pass) {
              postDataParams = new HashMap<>();
              postDataParams.put("name", name);
              postDataParams.put("password", pass);

    }


    @Override
    protected JSONObject doInBackground(Boolean... booleans) {


        JSONObject resposta = null;
        try {

            URL url = new URL(MainActivity.PROVEDOR+"loginmobile/");
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,null, new java.security.SecureRandom());
            conn.setSSLSocketFactory(sslContext.getSocketFactory());


            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();

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

            resposta = new JSONObject(responseString);
        } catch (Exception e) {
            Log.d("Resposta", e.getMessage());
        }









        return resposta;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        Log.d("Aguardo", result.toString());
        resultBoolean.processFinish(result);
    }


}
