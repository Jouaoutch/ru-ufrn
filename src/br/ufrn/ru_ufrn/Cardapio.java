package br.ufrn.ru_ufrn;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class Cardapio extends Activity {

	private TextView dataAtual;
	private Button mudarData;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardapio);
		
		setCurrentDateOnView();
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		
		mudarData = (Button) findViewById(R.id.button_mudar_data);
		mudarData.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO mudar a data 
				
			}
		});
		
	}

	private void setCurrentDateOnView() {
		
		
		final Calendar c = Calendar.getInstance();
		
		dataAtual = (TextView) findViewById(R.id.textView_data_atual);
		dataAtual.setText(c.getTime().toString());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cardapio, menu);
		return true;
	}

}
