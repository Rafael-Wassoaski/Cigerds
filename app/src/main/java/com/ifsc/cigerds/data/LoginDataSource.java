package com.ifsc.cigerds.data;

import android.util.Log;

import com.ifsc.cigerds.Threads.ConexaoLogin;
import com.ifsc.cigerds.data.model.LoggedInUser;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) throws ExecutionException, InterruptedException {


        // TODO: handle loggedInUser authentication


        ConexaoLogin realizarLogin = new ConexaoLogin(username, password);
        Log.d("Aguardo", "vfoi");

        Boolean resultado = realizarLogin.execute().get();

        LoggedInUser user = null;
        if (resultado) {
            user = new LoggedInUser(java.util.UUID.randomUUID().toString(), username);

        }

        return new Result.Success<>(user);
        //else{
        //   return new Result.Error(new IOException("Error logging in",));
        // }


    }




    public void logout() {
        // TODO: revoke authentication
    }
}
