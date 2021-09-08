package com.example.alexcaban.finalprojectturismo.Utilities;



import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.alexcaban.finalprojectturismo.R;

public class AlertDialogManager {

	private ProgressDialog pdLoading;
	
	public void showAlertDialog(Context context, String title, String message) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message).setIcon(R.drawable.ic_favorite_star).setTitle(title).setCancelable(false)
        /*.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })*/
		.setPositiveButton("Continuar",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // metodo que se debe implementar
            }
        });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public void showMessageProgress(Context context, String sbTitle, String sbMessage){
		pdLoading = new ProgressDialog(context);
		pdLoading.setIcon(R.drawable.ic_favorite_star);
		pdLoading.setTitle(sbTitle);
		pdLoading.setMessage(sbMessage);
		pdLoading.setIndeterminate(false);
		pdLoading.setCancelable(false);
		pdLoading.show();
	}
	
	public void miss(){
		pdLoading.dismiss();
	}
	
}
