package com.example.alexcaban.finalprojectturismo.Utilities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.alexcaban.finalprojectturismo.R;

import java.util.ArrayList;

public class DefaultItemAdapter extends BaseAdapter {
	public static final String TAG = "TAG.MenuAdapter";
	
	protected Activity objActivity;
	protected ArrayList<DefaultItemADT> objItems;
	
	public DefaultItemAdapter(Activity objActivity, ArrayList<DefaultItemADT> objItems) {
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

		return this.objItems.get(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) this.objActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.default_item_list, null);
		}
		
		DefaultItemADT objMenuADT = this.objItems.get(position);
		//TextView label = (TextView) view.findViewById(R.id.txtLabel);
		//label.setText(objMenuADT.getLabel());
		
		view.setTag(objMenuADT);
		
		return view;
	}

}
