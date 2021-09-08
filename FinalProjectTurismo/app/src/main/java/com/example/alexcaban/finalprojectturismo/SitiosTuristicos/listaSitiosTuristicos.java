package com.example.alexcaban.finalprojectturismo.SitiosTuristicos;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.alexcaban.finalprojectturismo.HttpRequest;
import com.example.alexcaban.finalprojectturismo.R;
import com.example.alexcaban.finalprojectturismo.Utilities.Constantes;
import com.example.alexcaban.finalprojectturismo.Utilities.SystemPlay;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class listaSitiosTuristicos extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvListaSitiosTuristicos;
    listaSitiosTuristicos objActivity;
    sitiosTuristicos sitio;
    ArrayList<sitiosTuristicos> enums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sitios_turisticos);

        objActivity = this;
        lvListaSitiosTuristicos = (ListView)findViewById(R.id.lvListaSitiosTuristicos);
        lvListaSitiosTuristicos.setOnItemClickListener(this);
        new consultaSitiosTuristicosAsyncTask().execute("selectsitio");
    }

    public class consultaSitiosTuristicosAsyncTask extends AsyncTask<Object,Object,Object> {
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
                procesarListaSitiosTuristicos(enums);


            }catch (Exception e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void procesarListaSitiosTuristicos(ArrayList arraySitios){
        sitiosTuristicosAdapter objsitiosTuristicosAdapter = new sitiosTuristicosAdapter(this, arraySitios);
        lvListaSitiosTuristicos.setAdapter(objsitiosTuristicosAdapter);
    }

    public void irARegistrar(View v){
        Intent objIntent = new Intent(objActivity, frmSitiosTuristicos.class);
        objIntent.putExtra("transaccion", "0");
        startActivity(objIntent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {

        sitio = enums.get(position);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Sitios Turisticos");
        dialogBuilder.setMessage("Desea eliminar o actualizar este sitio turistico?");
        dialogBuilder.setPositiveButton("actualizar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Intent objIntent = new Intent(objActivity, frmSitiosTuristicos.class);
                objIntent.putExtra("transaccion", "1");
                objIntent.putExtra("codigo", Integer.toString(sitio.getCodigo()));
                objIntent.putExtra("descripcion", sitio.getDescripcion());
                objIntent.putExtra("direccion", sitio.getDireccion());
                objIntent.putExtra("email", sitio.getEmail());
                objIntent.putExtra("sitioweb", sitio.getSitioweb());
                objIntent.putExtra("celular", sitio.getCelular());
                objIntent.putExtra("fijo", sitio.getFijo());
                startActivity(objIntent);
            }
        });
        dialogBuilder.setNegativeButton("eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String strCodigo = Integer.toString(sitio.getCodigo());
                new eliminarSitiosTuristicosAsyncTask().execute("deletesitio",strCodigo);
            }
        });
        dialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public class eliminarSitiosTuristicosAsyncTask extends AsyncTask<Object,Object,Object> {
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
