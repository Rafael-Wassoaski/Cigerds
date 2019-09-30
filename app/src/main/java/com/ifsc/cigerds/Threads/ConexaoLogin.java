package com.ifsc.cigerds.Threads;

import android.os.AsyncTask;
import android.util.Log;

import com.ifsc.cigerds.Interfaces.AsyncInterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConexaoLogin extends AsyncTask<Boolean, Boolean, Boolean> {

    private String email;
    private String pass;
    public AsyncInterface resultBoolean = null;

    public ConexaoLogin(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }


    @Override
    protected Boolean doInBackground(Boolean... booleans) {
        try {


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
