package br.ufrn.ru_ufrn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class EstatisticasItemIndividual extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estatisticas_item_individual);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.estatisticas_item_individual, menu);
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
		        case R.id.menuAvPrato:
		            exibirActivity(Avaliar.class);
		            return true;
		        case R.id.menuAvaliarItem:
		        	exibirActivity(AvaliarItemIndividual.class);
		            return true;
		        case R.id.menuCardapioSemana:
		        	exibirActivity(CardapioSemana.class);
		            return true;
		        case R.id.menuVisualizarAvPrato:
		        	exibirActivity(EstatisticasCardapio.class);
		            return true;
		        case R.id.menucomentario:
		        	exibirActivity(Comentario.class);
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
		    }
		}
	

}
