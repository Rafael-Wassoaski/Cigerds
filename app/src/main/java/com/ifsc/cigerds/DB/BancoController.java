package com.ifsc.cigerds.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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


    public void testeSQL(){
        Cursor cursor = null;
    try {


        String query = "SELECT * FROM vistoria";
        dataBase = banco.getReadableDatabase();
        cursor = dataBase.rawQuery(query, null);


        while (cursor.moveToNext()) {
            String string = cursor.getString(2);
            Log.d("Resposta", string + " a");
        }

        dataBase.close();

    }catch (Exception e){
        Log.d("Exep", e.getMessage().toString());
    }



    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(Banco.BancoEntry.NOME_BANCO, null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }
        return checkDB != null;
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

                if(chave.equals("idControle")){
                    linhasTable.put("idControle", Integer .parseInt(json.get(chave).toString()));
                }else {
                    linhasTable.put(chave, json.get(chave).toString());
                }
            } catch (JSONException e) {
                Log.d("JSONDB", e.getClass() + " / " + e.getMessage());
                e.printStackTrace();
            }

        }
        long newRowId = dataBase.insert("vistoria", null, linhasTable);
        dataBase.close();
        return false;
    }
}
