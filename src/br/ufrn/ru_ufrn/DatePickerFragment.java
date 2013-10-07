package br.ufrn.ru_ufrn;

import java.util.Calendar;


import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;



public class DatePickerFragment extends DialogFragment implements
		OnDateSetListener {
	
	private TextView data;

	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void setData(TextView data){
		this.data = data;
	}
	

	@Override
	public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
		
		Calendar cl = Calendar.getInstance();
		cl.set(arg1, arg2, arg3);
		
		if(data != null)
		data.setText(cl.getTime().toString());
		
	}
}