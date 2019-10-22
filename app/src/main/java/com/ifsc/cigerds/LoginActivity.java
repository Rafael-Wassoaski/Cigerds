package com.ifsc.cigerds;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ifsc.cigerds.Interfaces.AsyncInterface;
import com.ifsc.cigerds.Threads.ConexaoLogin;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements AsyncInterface {

    private EditText emailEditText;
    private EditText passEditText;
    private Button loginButton;
    private final String NOME_PREFERENCE = "262114a72D&@5aa!!@FA";
    private SharedPreferences prefs;

    private void finishActivity(){
        Intent returnIntent = new Intent();

        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private boolean autoLogin(){

        String login = prefs.getString("login", null);
        String password = prefs.getString("password", null);

        if(login != null && password != null){
            return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs =  getSharedPreferences(NOME_PREFERENCE, MODE_PRIVATE);
        String login= prefs.getString("login", null);
        String password= prefs.getString("password", null);

        if(autoLogin()){
            setResult(RESULT_OK);
            finishActivity();
        }


        emailEditText = (EditText)findViewById(R.id.email);
        passEditText = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.login);

        if(login!=null){
            emailEditText.setText(login);
            passEditText.setText(password);
        }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexaoLogin  conexao = new ConexaoLogin(emailEditText.getText().toString(), passEditText.getText().toString());
                conexao.resultBoolean = LoginActivity.this;
                conexao.execute();
            }
        });

    }

    @Override
    public void processFinish(JSONObject result) {

        try {
            if(Integer.parseInt(result.get("status").toString()) == 200){


                SharedPreferences.Editor editor = getSharedPreferences(NOME_PREFERENCE, MODE_PRIVATE).edit();
                editor.putString("login", emailEditText.getText().toString());
                editor.putString("password", passEditText.getText().toString());
                editor.putString("userId", result.get("id").toString());
                editor.commit();
                setResult(RESULT_OK);
                finish();




            }else{
                Toast.makeText(this, "Senha ou Email incorretos, tente novamente", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
