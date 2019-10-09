package com.ifsc.cigerds.Threads;

import android.icu.util.Output;
import android.os.AsyncTask;
import android.util.Log;

import com.ifsc.cigerds.Interfaces.AsyncInterface;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConexaoLogin extends AsyncTask<Boolean, Boolean, Boolean> {


    public AsyncInterface resultBoolean = null;
    private Map<String, String> postDataParams;
    public ConexaoLogin(String name, String pass) {
              postDataParams = new HashMap<>();
              postDataParams.put("name", name);
              postDataParams.put("password", pass);

    }


    @Override
    protected Boolean doInBackground(Boolean... booleans) {
        try {

            URL url = new URL("http://testestccii.pythonanywhere.com/ws/loginmobile/");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write();



            Socket conexao  = new Socket("192.168.0.107", 6666);

            PrintWriter envio = new PrintWriter(conexao.getOutputStream(), true);
            envio.println(email+":"+pass);

            Scanner scanner = new Scanner(conexao.getInputStream());

            String resposta = scanner.nextLine();



            conexao.close();

            if(resposta.equals("2000")){
                return true;
            }else{
                return false;
            }




        } catch (IOException e) {
            Log.d("Aguardo", e.getClass() + " "+e.getMessage());
            //Toast.makeText(, "Erro ao estabeler conexao", Toast.LENGTH_SHORT );
            e.printStackTrace();
        }


        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.d("Aguardo", result.toString());
        resultBoolean.processFinish(result);
    }


}
