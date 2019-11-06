package com.ifsc.cigerds.Interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface DadosInterface {

    public void getDados(JSONObject json) throws JSONException;

    public String getResumo();

    public Boolean verficaDados();
}
