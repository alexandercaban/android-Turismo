package com.example.alexcaban.finalprojectturismo.Hoteles;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.alexcaban.finalprojectturismo.HttpRequest;
import com.example.alexcaban.finalprojectturismo.SitiosTuristicos.listaSitiosTuristicos;
import com.example.alexcaban.finalprojectturismo.Utilities.Constantes;
import com.example.alexcaban.finalprojectturismo.Utilities.SystemPlay;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.alexcaban.finalprojectturismo.R;

import java.util.HashMap;
import java.util.Map;

public class frmHoteles extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener,  View.OnClickListener {
    frmHoteles objActivity;
    private GoogleMap mMap;
    EditText descripcion, website, longitud, latitud, imagen;
    Button guardar, actualizar;
    ImageButton btnImagen;
    private static final int ACTION_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_hoteles);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        descripcion = (EditText)findViewById(R.id.EtHotel);
        website = (EditText)findViewById(R.id.EtWebsite);
        longitud = (EditText)findViewById(R.id.EtLongi);
        latitud = (EditText)findViewById(R.id.EtLati);
        guardar = (Button)findViewById(R.id.btnGuardar);
        actualizar = (Button)findViewById(R.id.btnActualizar);
        btnImagen = (ImageButton) findViewById(R.id.btnImage);

        longitud.setEnabled(false);
        latitud.setEnabled(false);
        guardar.setOnClickListener(this);
        actualizar.setOnClickListener(this);
        btnImagen.setOnClickListener(this);
        objActivity = this;


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        MarkerOptions marcadorSydeny = new MarkerOptions();
        marcadorSydeny.position(sydney);
        marcadorSydeny.title("Marker in Sydney");
        marcadorSydeny.draggable(true);
        mMap.addMarker(marcadorSydeny);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                System.out.println(marker.getPosition().latitude+" - "+marker.getPosition().longitude);
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                latitud.setText(""+marker.getPosition().latitude);
                longitud.setText(""+marker.getPosition().longitude);
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                System.out.println(marker.getPosition().latitude+" - "+marker.getPosition().longitude);
            }
        });

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }


    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnGuardar:
                    accionHoteles("inserthotel");
                    break;
                case R.id.btnActualizar:
                    accionHoteles("updatehotel");
                    break;
                case R.id.btnImage:
                    takeAPic(ACTION_TAKE_PHOTO);
                    break;
            }
        } catch (Exception ex) {
            SystemPlay.showMessagePopup(this, ex.getMessage());
        }
    }

    public void accionHoteles(String transaccion){
        String sbDescripcion = descripcion.getText().toString();
        String sbWebSite = website.getText().toString();
        String sbLatitud = latitud.getText().toString();
        String sbLongitud = longitud.getText().toString();

        new insertarActualizarHotelAsyntask().execute(transaccion, sbDescripcion,sbWebSite,sbLatitud,sbLongitud);
    }


    public class insertarActualizarHotelAsyntask extends AsyncTask<Object,Object,Object>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            objActivity.runOnUiThread(new Runnable() {
                public void run() {
                    SystemPlay.showMessageProgress(objActivity, "Registrando...");
                }
            });
        }
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Map<String, String> parameter = new HashMap<String, String>();
                parameter.put("transaccion", params[0].toString());

                if (params[0].toString().equals("inserthotel")) {
                    parameter.put("desc", params[1].toString());
                    parameter.put("webs", params[2].toString());
                    parameter.put("long", params[3].toString());
                    parameter.put("lat", params[4].toString());
                    parameter.put("pic", "");
                }else if(params[0].toString().equals("updatehotel")){
                    String strCodigo = getIntent().getExtras().getString("codigo");
                    parameter.put("cod", strCodigo);
                    parameter.put("desc", params[1].toString());
                    parameter.put("webs", params[2].toString());
                    parameter.put("long", params[3].toString());
                    parameter.put("lat", params[4].toString());
                    parameter.put("pic", "");
                }
                    return HttpRequest.post(Constantes.URL_PROJECT).form(parameter).body();
            }catch (Exception e){
                return e;
            }
        }

        @Override
        protected void onPostExecute(Object result) {
            try{
                SystemPlay.miss();
                Toast.makeText(objActivity, result.toString(),Toast.LENGTH_LONG).show();
                cleanForm();
            }catch (Exception e){
                SystemPlay.showMessagePopup(getApplicationContext(), e.getMessage());
            }
        }
    }

    public void cleanForm(){
        descripcion.setText("");
        website.setText("");
        longitud.setText("");
        latitud.setText("");
    }

    public void takeAPic(int actionCode){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, actionCode);
    }

}
