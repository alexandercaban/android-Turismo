package com.example.alexcaban.finalprojectturismo.Hoteles;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexcaban.finalprojectturismo.R;
import com.example.alexcaban.finalprojectturismo.SitiosTuristicos.sitiosTuristicos;

import java.util.ArrayList;

/**
 * Created by Alex Caban on 18/06/2017.
 */

public class hotelesAdapter extends BaseAdapter {

    protected Activity objActivity;
    protected ArrayList<hoteles> objItems;

    public hotelesAdapter(Activity objActivity, ArrayList<hoteles> objItems){
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
            convertView = inflater.inflate(R.layout.item_hoteles, null);


        }

        hoteles objHoteles = this.objItems.get(position);

        TextView descripcion = (TextView)convertView.findViewById(R.id.tvDescripcion);
        TextView sitioweb = (TextView) convertView.findViewById(R.id.tvSitioweb);
        TextView longitud = (TextView)convertView.findViewById(R.id.tvLongitud);
        TextView latitud = (TextView)convertView.findViewById(R.id.tvLatitud);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgImagen);


        descripcion.setText(objHoteles.getDescricpion());
        sitioweb.setText(objHoteles.getWebsite());
        longitud.setText(objHoteles.getLongitud());
        latitud.setText(objHoteles.getLatitud());


        return convertView;
    }
}
