package com.ifsc.cigerds.Classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

 public class BitMapConverter {

    public static String BitMapToString(Bitmap bitmap){

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] bytes = outputStream.toByteArray();
        String stringMap = Base64.encodeToString(bytes, Base64.DEFAULT);
        return  stringMap;

    }

    public static Bitmap StringToBitMap(String stringMap){
        try{
            byte[] bytes = Base64.decode(stringMap, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return bitmap;
        }catch (Exception e ){
            Log.d("ExecaoDeBitMap", e.getMessage() + " / " + e.getClass());
            return null;
        }
    }
}
