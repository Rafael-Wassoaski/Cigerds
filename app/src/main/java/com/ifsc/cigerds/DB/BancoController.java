package com.ifsc.cigerds.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.ifsc.cigerds.Threads.ConexaoEnvio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class BancoController {

    private SQLiteDatabase dataBase;
    private Banco banco;
    private Context context;

    public  BancoController(Context context){
        banco = new Banco(context);
        this.context = context;
    }


    public void createJSON(String user, String pass){
        JSONObject principal;



        dataBase = banco.getReadableDatabase();
        Cursor cursor = dataBase.rawQuery("SELECT * FROM vistoria", null);
        try {
        for(int vistoria = 0; vistoria < cursor.getCount(); vistoria++){
            cursor.moveToNext();
            principal = new JSONObject();
            for(int collumName = 0; collumName < cursor.getColumnCount(); collumName++){
                principal.put(cursor.getColumnName(collumName), cursor.getString(collumName));
            }
            ConexaoEnvio envio = new ConexaoEnvio(principal, user, pass);
            envio.execute();

        }
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    public void delete(){
        dataBase = banco.getReadableDatabase();
        dataBase.execSQL("DELETE FROM vistoria");
    }
    public Cursor checkData(){
        Cursor cursor = null;

        dataBase = banco.getReadableDatabase();
        cursor = dataBase.rawQuery("SELECT * FROM vistoria", null);

        return cursor;
    }


    public void testeSQL(){
        Cursor cursor = null;
    try {


        String query = "SELECT * FROM vistoria";
        dataBase = banco.getReadableDatabase();
        cursor = dataBase.rawQuery(query, null);


        while (cursor.moveToNext()) {
            String string = cursor.getString(1);
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
