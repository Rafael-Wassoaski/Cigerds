package com.ifsc.cigerds.Fragmentos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ifsc.cigerds.Classes.Network;
import com.ifsc.cigerds.Interfaces.AsyncInterface;
import com.ifsc.cigerds.Interfaces.DadosInterface;
import com.ifsc.cigerds.R;
import com.ifsc.cigerds.Threads.ConexaoBuscaEndereco;

import org.json.JSONException;
import org.json.JSONObject;

public class MapaController extends Fragment implements DadosInterface, AsyncInterface {

    private Double latitude = -16.57095, longitude = -52.3771006;
    private GoogleMap mapa;
    private View view;
    private AsyncInterface caller = this;
    private String endereco;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.map_layout, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mapa = googleMap;
                mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mapa.clear();
                CameraPosition googlePlex = CameraPosition.builder().target(new LatLng(latitude, longitude))
                        .zoom(3).bearing(0).tilt(15).build();
                mapa.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

                mapa.setOnMapClickListener(new GoogleMap.OnMapClickListener() {



                    @Override
                    public void onMapClick(LatLng latLng) {
                        latitude = latLng.latitude;
                        longitude = latLng.longitude;
                        Log.d("Mapa", "Click");
                        mapa.clear();
                        LatLng localDesastre = new LatLng(latitude, longitude);
                        mapa.addMarker(new MarkerOptions().position(localDesastre).title("Local do Desastre"));
                        mapa.moveCamera(CameraUpdateFactory.newLatLng(localDesastre));

                        if(Network.VerificaConexao(getContext())) {

                            Toast.makeText(getContext(), "Obtendo endereço, aguarde!", Toast.LENGTH_LONG).show();;

                            String latLongi[] = {String.valueOf(latitude), String.valueOf(longitude)};
                            ConexaoBuscaEndereco conexaoBuscaEndereco = new ConexaoBuscaEndereco();
                            conexaoBuscaEndereco.setAsynkInterface(caller);
                            conexaoBuscaEndereco.execute(latLongi);

                        }else{
                            Toast.makeText(getContext(), "Sem conexão, impossível obter o endereço", Toast.LENGTH_LONG).show();;
                        }
                    }
                });

            }
        });

        final ScrollView scrollViewParent = (ScrollView) view.findViewById(R.id.ScrollViewDados);


        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        scrollViewParent.requestDisallowInterceptTouchEvent(true);

                        return false;

                    case MotionEvent.ACTION_UP:

                        scrollViewParent.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        scrollViewParent.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;

                }

            }
        });

        return view;
    }

    @Override
    public void getDados(JSONObject json) throws JSONException {

    }

    @Override
    public String getResumo() {

        String endereco = "Endereco: rua a \n";
        return endereco;
    }

    @Override
    public Boolean verficaDados() {


        return true;
    }


    @Override
    public void processFinish(JSONObject result) {
        try {


             setEndereco(result.getJSONObject("Response").getJSONArray("View").getJSONObject(0).
                    getJSONArray("Result").getJSONObject(0).getJSONObject("Location").getJSONObject("Address").getString("Label"));

            Toast.makeText(getContext(), "Endereco localizado: "+ getEndereco(), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
