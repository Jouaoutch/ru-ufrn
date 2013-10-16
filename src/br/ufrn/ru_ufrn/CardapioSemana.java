package br.ufrn.ru_ufrn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CardapioSemana extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardapio_semana);
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.dias_semana, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cardapio_semana, menu);
		return true;
	}
	
	public void exibirActivity(Class classe){
	
	Intent intent = new Intent(this,
			classe);
	startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menuAvaliarPrato:
	            exibirActivity(Avaliar.class);
	            return true;
	        case R.id.menuAvaliarItem:
	        	exibirActivity(AvaliarItemIndividual.class);
	            return true;
	        case R.id.menucomentario:
	        	exibirActivity(Comentar.class);
	            return true;
	        case R.id.menuVisualizarAvItem:
	        	exibirActivity(EstatisticasItemIndividual.class);
	            return true;
	        case R.id.menuVisualizarAvPrato:
	        	exibirActivity(EstatisticasCardapio.class);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
