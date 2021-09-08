package com.example.alexcaban.finalprojectturismo.SitiosTuristicos;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.alexcaban.finalprojectturismo.R;

import java.util.ArrayList;

/**
 * Created by Alex Caban on 18/06/2017.
 */

public class sitiosTuristicosAdapter  extends BaseAdapter {

    protected Activity objActivity;
    protected ArrayList<sitiosTuristicos> objItems;

    public sitiosTuristicosAdapter(Activity objActivity, ArrayList<sitiosTuristicos> objItems){
        this.objActivity = objActivity;
        this.objItems = objItems;

    }

    @Override
    public int getCount() {
        return this.objItems.size();
    }

    @Override
    public Object getItem(int position) {
        return this.objItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.objItems.get(position).getCodigo();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.objActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sitios_turisticos, null);


        }

        sitiosTuristicos objSitiosTuristicos = this.objItems.get(position);

        TextView descripcion = (TextView)convertView.findViewById(R.id.tvDescripcion);
        TextView direccion = (TextView) convertView.findViewById(R.id.tvDireccion);
        TextView sitioweb = (TextView)convertView.findViewById(R.id.tvSitioweb);
        TextView email = (TextView)convertView.findViewById(R.id.tvEmail);
        TextView fijo = (TextView)convertView.findViewById(R.id.tvFijo);
        TextView celular = (TextView)convertView.findViewById(R.id.tvCelular);

        descripcion.setText(objSitiosTuristicos.getDescripcion());
        direccion.setText(objSitiosTuristicos.getDireccion());
        sitioweb.setText(objSitiosTuristicos.getSitioweb());
        email.setText(objSitiosTuristicos.getEmail());
        fijo.setText(objSitiosTuristicos.getFijo());
        celular.setText(objSitiosTuristicos.getCelular());


        return convertView;
    }
}
