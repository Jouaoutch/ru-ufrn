package br.ufrn.ru_ufrn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class Comentario extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comentario);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comentario, menu);
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
		        case R.id.menuCardapioSemana:
		        	exibirActivity(CardapioSemana.class);
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
