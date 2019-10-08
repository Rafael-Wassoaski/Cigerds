package com.ifsc.cigerds.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class BancoController {

    private SQLiteDatabase dataBase;
    private Banco banco;

    public  BancoController(Context context){
        banco = new Banco(context);
    }

    public Boolean insereDados(JSONObject json){



        Iterator<String> chaves = json.keys();

        long resultado;
        ContentValues linhasTable;

        dataBase = banco.getWritableDatabase();

        linhasTable = new ContentValues();

        while(chaves.hasNext()){
            String chave = chaves.next();

            try {
                Log.d("JSONDB", chave + " : " + json.get(chave).toString());
                linhasTable.put(chave, json.get(chave).toString());
            } catch (JSONException e) {
                Log.d("JSONDB", e.getClass() + " / " + e.getMessage());
                e.printStackTrace();
            }

        }
        return false;
    }
}
