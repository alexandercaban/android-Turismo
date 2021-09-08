package com.example.alexcaban.finalprojectturismo.SitiosTuristicos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alexcaban.finalprojectturismo.HttpRequest;
import com.example.alexcaban.finalprojectturismo.R;
import com.example.alexcaban.finalprojectturismo.Utilities.Constantes;
import com.example.alexcaban.finalprojectturismo.Utilities.SystemPlay;

import java.util.HashMap;
import java.util.Map;

public class frmSitiosTuristicos extends AppCompatActivity implements View.OnClickListener{
    EditText nombre, direccion, sitioweb, email, fijo, celular;
    Button guardar,actualizar;
    frmSitiosTuristicos objActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_sitios_turisticos);
        objActivity = this;
        nombre = (EditText)findViewById(R.id.etNombre);
        direccion = (EditText)findViewById(R.id.etDireccion);
        sitioweb= (EditText)findViewById(R.id.etSitioweb);
        email = (EditText)findViewById(R.id.etEmail);
        fijo = (EditText)findViewById(R.id.etFijo);
        celular = (EditText)findViewById(R.id.etCelular);
        guardar = (Button)findViewById(R.id.btnGuardar);
        actualizar = (Button)findViewById(R.id.btnActualizar);
        llenarFormulario();

        guardar.setOnClickListener(this);
        actualizar.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnGuardar:
                    accionSitioTuristico("insertsitio");
                    break;
                case R.id.btnActualizar:
                    accionSitioTuristico("updatesitio");
                    break;
            }
        } catch (Exception ex) {
            SystemPlay.showMessagePopup(this, ex.getMessage());
        }
    }

    public void accionSitioTuristico(String transaccion){

        String sbDescripcion =  nombre.getText().toString();
        String sbDireccion =  direccion.getText().toString();
        String sbSitioWeb = sitioweb.getText().toString();
        String sbEmail = email.getText().toString();
        String sbFijo = fijo.getText().toString();
        String sbCelular = fijo.getText().toString();

        insertarActualizarAsyncTask hilo = new insertarActualizarAsyncTask();
        hilo.execute(transaccion,sbDescripcion,sbDireccion,sbSitioWeb, sbEmail, sbFijo, sbCelular);
    }



    public class insertarActualizarAsyncTask extends AsyncTask<Object,Object,Object> {

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
        protected Object doInBackground(Object... params) {
        try {
            Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("transaccion", params[0].toString());

            if (params[0].toString().equals("insertsitio")) {
                parameter.put("desc", params[1].toString());
                parameter.put("dirr", params[2].toString());
                parameter.put("webs", params[3].toString());
                parameter.put("emai", params[4].toString());
                parameter.put("fijo", params[5].toString());
                parameter.put("celu", params[6].toString());
            }else if(params[0].toString().equals("updatesitio")){
                String strCodigo = getIntent().getExtras().getString("codigo");
                parameter.put("cod", strCodigo);
                parameter.put("desc", params[1].toString());
                parameter.put("dirr", params[2].toString());
                parameter.put("webs", params[3].toString());
                parameter.put("emai", params[4].toString());
                parameter.put("fijo", params[5].toString());
                parameter.put("celu", params[6].toString());
            }

            //HttpRequest.get("",true,params).body();
            //String cadena = HttpRequest.get("http://192.168.37.1/WebService.php?trans=" + params[0] + "&ced=" + params[1] + "&nom=" + params[2] + "&ape=" + params[3]  + "&pro=" + params[4]  + "&sex=" + params[5]).accept("application/json").body();
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
                Intent objIntent = new Intent(objActivity,listaSitiosTuristicos.class );
                startActivity(objIntent);
            }catch (Exception e){
                SystemPlay.showMessagePopup(getApplicationContext(), e.getMessage());
            }
        }
    }

    public void llenarFormulario(){
        String sbTransaccion = getIntent().getExtras().getString("transaccion");
        String strDescripcion = getIntent().getExtras().getString("descripcion");
        String strDireccion = getIntent().getExtras().getString("direccion");
        String strEmail = getIntent().getExtras().getString("email");
        String strWebsite = getIntent().getExtras().getString("sitioweb");
        String strFijo = getIntent().getExtras().getString("celular");
        String strCelular = getIntent().getExtras().getString("fijo");


        if(sbTransaccion.equals("1")){
            nombre.setText(strDescripcion);
            direccion.setText(strDireccion);
            email.setText(strEmail);
            sitioweb.setText(strWebsite);
            fijo.setText(strFijo);
            celular.setText(strCelular);
            guardar.setVisibility(View.INVISIBLE);
            actualizar.setVisibility(View.VISIBLE);
        }else{
            cleanForm();
            guardar.setVisibility(View.VISIBLE);
            actualizar.setVisibility(View.INVISIBLE);
        }

    }

    public void cleanForm(){
        nombre.setText("");
        direccion.setText("");
        sitioweb.setText("");
        email.setText("");
        fijo.setText("");
        celular.setText("");
    }




}
