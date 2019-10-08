package com.ifsc.cigerds.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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


    public Cursor testeSQL(){
        Cursor cursor;

        String[] campos ={"_id", "autor"};
        dataBase = banco.getReadableDatabase();
        cursor = dataBase.query("vistoria", campos, null, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        dataBase.close();
        return cursor;



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
        dataBase.close();
        return false;
    }
}
