package com.example.alexcaban.finalprojectturismo.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.telephony.TelephonyManager;
import com.example.alexcaban.finalprojectturismo.R;


@SuppressLint("SimpleDateFormat")
public class SystemPlay {

	private static ProgressDialog pdLoading;

	public static String getImei(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) ((Context) context)
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

	public static boolean validateChain(String sbCadena) {
		// expresion regular que revisa si tiene alguno de los siguientes caracteres
		String REG_EXP = "\\@+|\\�+|\\�+|\\|+|\\&+|\\�+|\\~+|\\[+|\\]+|\\{+|\\}+|\\^+|\\<+|\\>+|\\\"+ ";
		Pattern pattern = Pattern.compile(REG_EXP);
		Matcher matcher = pattern.matcher(sbCadena);
		if (matcher.find())
			return false;
		return true;
	}

	public static boolean esStrig(final String cadena) {
		boolean blState = false;
		for (int i = 0; i < cadena.length(); ++i) {
			char caracter = cadena.charAt(i);
			if (Character.isLetter(caracter)) {
				blState = true;
				break;
			}
			if (Character.isDigit(caracter))
				blState = false;
		}
		return blState;
	}

	public static String getDate() {
		String sbFecha = "";
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		// int dow = cal.get(Calendar.DAY_OF_WEEK);
		// int dom = cal.get(Calendar.DAY_OF_MONTH);
        String sbMes = String.valueOf(month).length() == 1 ? "0"+month : String.valueOf(month);
        String sbDia = String.valueOf(day).length() == 1 ? "0"+day : String.valueOf(day);

        sbFecha = year + "-" + sbMes  + "-" + sbDia;
		return sbFecha;
	}

	public static String sumarDiasFecha(int day) throws ParseException {
		String untildate = getDate();// can take any date in current format
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFormat.parse(untildate));
		cal.add(Calendar.DATE, day);
		String convertedDate = dateFormat.format(cal.getTime());
		// System.out.println("Date increase by one.."+convertedDate);
		return convertedDate;
	}

	public static String getDay() {
		String sbFecha = "";
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		sbFecha += day;
		return sbFecha;
	}

	public static String getMonth() {
		String sbFecha = "";
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		sbFecha += month;
		return sbFecha;
	}

	public static String getYear() {
		String sbFecha = "";
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.YEAR);
		sbFecha += month;
		return sbFecha;
	}

	public static String getNameMonthByNumber(int mes) {
		String sbName = "";
		// int mes = Integer.parseInt(getMonth());
		switch (mes) {
		case 1:
			sbName = "Enero";
			break;
		case 2:
			sbName = "Febrero";
			break;
		case 3:
			sbName = "Marzo";
			break;
		case 4:
			sbName = "Abril";
			break;
		case 5:
			sbName = "Mayo";
			break;
		case 6:
			sbName = "Junio";
			break;
		case 7:
			sbName = "Julio";
			break;
		case 8:
			sbName = "Agosto";
			break;
		case 9:
			sbName = "Septiembre";
			break;
		case 10:
			sbName = "Octubre";
			break;
		case 11:
			sbName = "Noviembre";
			break;
		case 12:
			sbName = "Diciembre";
			break;
		}
		return sbName;
	}

	public static String getHour() {
		String sbHour = "";
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		sbHour = hour + ":" + min + ":" + second;
		return sbHour;
	}

	/**
	 * @param sbFechaParametro
	 * @return 0 fecha son iguales 1 sbFechaParametro es mayor -1
	 *         sbFechaParametro es menor
	 * @throws ParseException
	 */
	public static int compararFechas(String sbFechaParametro)
			throws ParseException {
		int reult;
		Calendar fechaActual = Calendar.getInstance();

		SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date dateObj = curFormater.parse(sbFechaParametro);
		Calendar fecha2 = Calendar.getInstance();
		fecha2.setTime(dateObj);
		System.out.println("ACTUAL:::" + fechaActual.getTime());
		System.out.println("SERVER:::" + fecha2.getTime());
		reult = fecha2.compareTo(fechaActual);
		return reult;
	}
	
	/**
	 * @param sbFechaParametro
	 * @return 0 fecha son iguales 1 sbFechaParametro es mayor -1
	 *         sbFechaParametro es menor
	 * @throws ParseException
	 */
	public static int compararSoloFechas(String sbFechaParametro)
			throws ParseException {
		int reult;
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(Calendar.HOUR_OF_DAY,0);
		fechaActual.set(Calendar.MINUTE,0);
		fechaActual.set(Calendar.SECOND,0);
		fechaActual.set(Calendar.MILLISECOND,0);

		SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date dateObj = curFormater.parse(sbFechaParametro);
		Calendar fecha2 = Calendar.getInstance();
		fecha2.setTime(dateObj);
		System.out.println("ACTUAL:::" + fechaActual.getTime());
		System.out.println("SERVER:::" + fecha2.getTime());
		reult = fecha2.compareTo(fechaActual);
		return reult;
	}
	
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}

	public static String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}


	public static void showMessageProgress(Context context, String sbMessage){
		pdLoading = new ProgressDialog(context);
		pdLoading.setIcon(R.drawable.ic_favorite_star);
		pdLoading.setTitle("Procesando");
		pdLoading.setMessage(sbMessage);
		pdLoading.setIndeterminate(false);
		pdLoading.setCancelable(false);
		pdLoading.show();
	}

	public static void miss(){
		if(pdLoading!= null){
			pdLoading.dismiss();
		}
	}

	public static void showMessagePopup(Context context,  String message) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message).setIcon(R.drawable.ic_favorite_star).setTitle(context.getString(R.string.app_name)).setCancelable(false)
		.setNegativeButton("Cancelar",
		new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int id) {
		dialog.cancel();
		}
		})
		.setPositiveButton("Continuar",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
		// metodo que se debe implementar
					}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	//FUNCIÓN ENCRIPTAR CALVE_DEFAULT:

	public static String encriptarClaveDeafaul(){

		String[] sbCadena = getDate().split("-");
		int year = Integer.parseInt(sbCadena[0]);
		int month = Integer.parseInt(sbCadena[1]);
		int day = Integer.parseInt(sbCadena[2]);
		int sbRespuesta = year * day + month;

		return String.valueOf(sbRespuesta);
	}

}
