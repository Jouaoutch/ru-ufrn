package br.ufrn.ru_ufrn;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.ru_ufrn.adapter.AdapterListView;
import br.ufrn.ru_ufrn.model.ItemListView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Comentario extends Activity {
	
	private ListView listView;
	private AdapterListView adapter;
	private List<ItemListView> itens;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comentario);
		
		itens = new ArrayList<ItemListView>();
		
		ItemListView item = new ItemListView("comentario 1","", "Jorge");
		ItemListView item2 = new ItemListView("comentario 2", "", "Jorge");
		ItemListView item3 = new ItemListView("comentario 1", "", "Jorge");

		itens.add(item);
		itens.add(item2);
		itens.add(item3);
		
		adapter = new AdapterListView(this, itens, this.findViewById(R.layout.activity_comentario));
		
		listView = (ListView) findViewById(R.id.list);
		
		listView.setAdapter(adapter);
		
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
