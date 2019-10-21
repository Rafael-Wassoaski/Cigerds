package com.ifsc.cigerds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    private Intent intent;
    final public static String PROVEDOR = "https://testestccii.pythonanywhere.com/ws/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // Verfica se o requestCode é o mesmo que foi passado
        if(requestCode==1 && resultCode == RESULT_OK){
            intent = new Intent(this, Vistoria.class);
            startActivity(intent);
        }
    }
}
