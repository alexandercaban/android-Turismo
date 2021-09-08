package com.example.alexcaban.finalprojectturismo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.alexcaban.finalprojectturismo.Hoteles.frmHoteles;
import com.example.alexcaban.finalprojectturismo.Hoteles.listaHoteles;
import com.example.alexcaban.finalprojectturismo.SitiosTuristicos.listaSitiosTuristicos;
import com.example.alexcaban.finalprojectturismo.SitiosTuristicos.sitiosTuristicos;
import com.example.alexcaban.finalprojectturismo.Utilities.SystemPlay;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button btnSitiosTuristicos, btnHoteles, btnOperadoresTuristicos;
    MainActivity objActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSitiosTuristicos = (Button)findViewById(R.id.btnSitiosTuristicos);
        btnHoteles = (Button)findViewById(R.id.btnHoteles);
        btnOperadoresTuristicos = (Button)findViewById(R.id.btnOperadoresTuristicos);

        objActivity = this;
        btnSitiosTuristicos.setOnClickListener(this);
        btnHoteles.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnSitiosTuristicos:
                    Intent objIntent = new Intent(this, listaSitiosTuristicos.class);
                    startActivity(objIntent);
                    break;
                case R.id.btnHoteles:
                    Intent objIntentHoteles = new Intent(this, frmHoteles.class);
                    startActivity(objIntentHoteles);
                    break;
            }
        } catch (Exception ex) {
            SystemPlay.showMessagePopup(this, ex.getMessage());
        }
    }


}
