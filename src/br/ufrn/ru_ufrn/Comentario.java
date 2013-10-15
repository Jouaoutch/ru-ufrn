package br.ufrn.ru_ufrn;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.ru_ufrn.adapter.AdapterListView;
import br.ufrn.ru_ufrn.model.ItemListView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
		
		listView = (ListView) findViewById(R.id.list);
		 
		// Creating a button - Load More
		/*Button btenviar = new Button(this);
		btenviar.setText("Enviar");
		btenviar.setGravity(Gravity.RIGHT);

		 
		// Adding button to listview at footer
		listView.addFooterView(btenviar);*/
		
		ItemListView item = new ItemListView("comentario 1",R.drawable.jorge, "Jorge");
		ItemListView item2 = new ItemListView("comentario 2", R.drawable.jorge, "Jorge");
		ItemListView item3 = new ItemListView("comentario 1", R.drawable.jorge, "Jorge");

		itens.add(item);
		itens.add(item2);
		itens.add(item3);
		
		adapter = new AdapterListView(this, itens, this.findViewById(R.layout.activity_comentario));
		
		
		listView.setAdapter(adapter);
		

		/*EditText editText = (EditText) findViewById(R.id.comentario);
		
		listView.addFooterView(editText);
		
		Button btEnviar = (Button) findViewById(R.id.btEnviar);
		listView.addFooterView(btEnviar);*/
		
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
