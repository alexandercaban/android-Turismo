package com.example.alexcaban.finalprojectturismo.Hoteles;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alexcaban.finalprojectturismo.HttpRequest;
import com.example.alexcaban.finalprojectturismo.R;
import com.example.alexcaban.finalprojectturismo.SitiosTuristicos.frmSitiosTuristicos;
import com.example.alexcaban.finalprojectturismo.SitiosTuristicos.listaSitiosTuristicos;
import com.example.alexcaban.finalprojectturismo.SitiosTuristicos.sitiosTuristicos;
import com.example.alexcaban.finalprojectturismo.SitiosTuristicos.sitiosTuristicosAdapter;
import com.example.alexcaban.finalprojectturismo.Utilities.Constantes;
import com.example.alexcaban.finalprojectturismo.Utilities.SystemPlay;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class listaHoteles extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvListaHoteles;
    listaHoteles objActivity;
    ArrayList<hoteles> enums;
    hoteles hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hoteles);

        lvListaHoteles = (ListView)findViewById(R.id.lvListaHoteles);
        objActivity = this;

        new consultaHotelesAsyncTask().execute("selecthotel");

    }

    public class consultaHotelesAsyncTask extends AsyncTask<Object,Object,Object> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            objActivity.runOnUiThread(new Runnable() {
                public void run() {
                    SystemPlay.showMessageProgress(objActivity, "Consultando...");
                }
            });
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Map<String, String> parameter = new HashMap<String, String>();
                parameter.put("transaccion", params[0].toString());
                return HttpRequest.post(Constantes.URL_PROJECT).form(parameter).body();
            }catch (Exception e){
                return e;
            }
        }

        @Override
        protected void onPostExecute(Object result) {
            try {
                super.onPostExecute(result);
                SystemPlay.miss();
                String json = result.toString();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ArrayList<sitiosTuristicos>>(){}.getType();
                enums = gson.fromJson(json, collectionType);
                procesarListaHoteles(enums);


            }catch (Exception e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void procesarListaHoteles(ArrayList arraySitios){
        hotelesAdapter objHotelesAdapter = new hotelesAdapter(this, arraySitios);
        lvListaHoteles.setAdapter(objHotelesAdapter);
    }

    public void irARegistrar(View v){
        Intent objIntent = new Intent(objActivity, frmHoteles.class);
        objIntent.putExtra("transaccion", "0");
        startActivity(objIntent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position,final long id) {
        hotel = enums.get(position);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Hoteles");
        dialogBuilder.setMessage("Desea eliminar o actualizar este hotel?");
        dialogBuilder.setPositiveButton("actualizar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Intent objIntent = new Intent(objActivity, frmSitiosTuristicos.class);
                objIntent.putExtra("transaccion", "1");
                objIntent.putExtra("codigo", Integer.toString(hotel.getCodigo()));
                objIntent.putExtra("descripcion", hotel.getDescricpion());
                objIntent.putExtra("sitioweb", hotel.getWebsite());
                objIntent.putExtra("latitud", hotel.getLatitud());
                objIntent.putExtra("longitud", hotel.getLongitud());
                objIntent.putExtra("imagen", hotel.getImage());
                startActivity(objIntent);
            }
        });
        dialogBuilder.setNegativeButton("eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String strCodigo = Integer.toString(hotel.getCodigo());
                new eliminarHotelesAsyncTask().execute("deletehotel",strCodigo);
            }
        });
        dialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public class eliminarHotelesAsyncTask extends AsyncTask<Object,Object,Object> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            objActivity.runOnUiThread(new Runnable() {
                public void run() {
                    SystemPlay.showMessageProgress(objActivity, "Eliminando...");
                }
            });
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Map<String, String> parameter = new HashMap<String, String>();
                parameter.put("transaccion", params[0].toString());
                parameter.put("cod", params[1].toString());
                return HttpRequest.post(Constantes.URL_PROJECT).form(parameter).body();
            }catch (Exception e){
                return e;
            }
        }

        @Override
        protected void onPostExecute(Object result) {
            try {
                super.onPostExecute(result);
                SystemPlay.miss();
                Toast.makeText(objActivity, result.toString(),Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }





}
