package br.ufrn.ru_ufrn;

import java.util.Date;

import br.ufrn.ru_ufrn.adapter.CardapioArrayAdapter;
import br.ufrn.ru_ufrn.adapter.MyExpandableListAdapter;
import br.ufrn.ru_ufrn.adapter.MyExpandableListAdapter2;
import br.ufrn.ru_ufrn.model.Cardapio;
import br.ufrn.ru_ufrn.model.Refeicao;
import br.ufrn.ru_ufrn.model.dao.CardapioDAO;
import br.ufrn.ru_ufrn.model.dao.DAOFactory;
import br.ufrn.ru_ufrn.model.dao.MemoryDAOFactory;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

public class EstatisticasItemIndividual extends Activity {
	
	private ArrayAdapter<String> adapter;
	//private ListView listview;
	private SparseArray<Refeicao> groups = new SparseArray<Refeicao>();
	private Cardapio cardapio;
	private DAOFactory daofact;
	private CardapioDAO cardapiodao;
	//private TextView cardapioItens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estatisticas_item_individual);
		
		daofact = new MemoryDAOFactory();
		cardapiodao = daofact.getCardapioDAO();

		new MockCardapio().mock(cardapiodao);
		cardapio = cardapiodao.findByData(new Date(System.currentTimeMillis()));
		
		createData();
		setCardapioExpadableItens();
	}

	private void createData() {
		groups.append(br.ufrn.ru_ufrn.model.Cardapio.CAFE_DA_MANHA,
				cardapio.getCafeDaManha());
		groups.append(br.ufrn.ru_ufrn.model.Cardapio.ALMOCO_VEGETARIANO,
				cardapio.getAlmocoVegetariano());
		groups.append(br.ufrn.ru_ufrn.model.Cardapio.ALMOCO_CARNIVORO,
				cardapio.getAlmocoCarnivoro());
		groups.append(br.ufrn.ru_ufrn.model.Cardapio.JANTA_VEGETARIANA,
				cardapio.getJantaVegetariana());
		groups.append(br.ufrn.ru_ufrn.model.Cardapio.JANTA_CARNIVORA,
				cardapio.getJantaCarnivora());

	}
	

	private void setCardapioExpadableItens() {
		ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
		MyExpandableListAdapter2 adapter = new MyExpandableListAdapter2(this,
				groups);
		listView.setAdapter(adapter);
	}
	
	/*
	private void setCardapioItens() {
		br.ufrn.ru_ufrn.model.Cardapio temp = cardapiodao
				.findByData(new Date());
		cardapioItens = (TextView) findViewById(R.id.textView_cardapio_itens);
		cardapioItens.setText(temp.toString());

		String[] refeicoes = new String[] { "Café da manhã",
				"Almoço Vegetariano", "Almoço Carnívoro", "Janta Vegetariana",
				"Janta Carnívora" };
		adapter = new CardapioArrayAdapter(this, R.layout.rowlayout, refeicoes);
		// TODO quebra!
		listview = (ListView) findViewById(R.id.listView);
		listview.setAdapter(adapter);

	}*/
	
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
		        	exibirActivity(Comentar.class);
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
		    }
		}
	

}
