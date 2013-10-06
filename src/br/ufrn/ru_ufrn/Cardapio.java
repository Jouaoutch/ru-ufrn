package br.ufrn.ru_ufrn;

import java.util.Calendar;
import java.util.Date;

import br.ufrn.ru_ufrn.adapter.CardapioArrayAdapter;
import br.ufrn.ru_ufrn.model.dao.CardapioDAO;
import br.ufrn.ru_ufrn.model.dao.DAOFactory;
import br.ufrn.ru_ufrn.model.dao.MemoryDAOFactory;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Cardapio extends Activity {

	private TextView dataAtual;
	private TextView cardapioItens;
	private Button mudarData;
	private DAOFactory daofact;
	private CardapioDAO cardapiodao;
	private ArrayAdapter<String> adapter;
	private ListView listview;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardapio);
		
		setCurrentDateOnView();
		addListenerOnButton();
		
		 daofact = new MemoryDAOFactory();
		 cardapiodao = daofact.getCardapioDAO();
		
		MockCardapio.mock(cardapiodao);
		
		setCardapioItens();
	}

	private void setCardapioItens() {
		br.ufrn.ru_ufrn.model.Cardapio temp = cardapiodao.findByData(new Date());
		cardapioItens = (TextView) findViewById(R.id.textView_cardapio_itens);
		cardapioItens.setText(temp.toString());
		
		String[] refeicoes = new String[]{"Café da manhã", 
				"Almoço Vegetariano",
				"Almoço Carnívoro", 
				"Janta Vegetariana",
				"Janta Carnívora" };
		adapter = new CardapioArrayAdapter(this,R.layout.rowlayout, refeicoes);
		listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(adapter);
 		
	}

	private void addListenerOnButton() {
		
		mudarData = (Button) findViewById(R.id.button_mudar_data);
		mudarData.setOnClickListener(new OnClickListener() {
			
			
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
