package br.ufrn.ru_ufrn;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.sql.Date;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import br.ufrn.ru_ufrn.adapter.CardapioArrayAdapter;
import br.ufrn.ru_ufrn.adapter.MyExpandableListAdapter;
import br.ufrn.ru_ufrn.controller.CardapioController;
import br.ufrn.ru_ufrn.model.Refeicao;
import br.ufrn.ru_ufrn.model.dao.CardapioDAO;
import br.ufrn.ru_ufrn.model.dao.CardapioSQLiteDAO;
import br.ufrn.ru_ufrn.model.dao.DAOFactory;
import br.ufrn.ru_ufrn.model.dao.GenericSQLiteDAO;
import br.ufrn.ru_ufrn.model.dao.MemoryDAOFactory;
import br.ufrn.ru_ufrn.model.dao.SQLiteDAOFactory;

public class Cardapio extends Activity {

	private TextView dataAtual;
	private TextView cardapioItens;
	private Button mudarData;
	private DAOFactory daofact;
	private CardapioDAO cardapiodao;
	private ArrayAdapter<String> adapter;
	private ListView listview;
	private SparseArray<Refeicao> groups = new SparseArray<Refeicao>();
	private br.ufrn.ru_ufrn.model.Cardapio cardapio;
	private RatingBar ratingBar;
	private HashMap<String, br.ufrn.ru_ufrn.model.Cardapio> cardapioDaSemana;
	private CardapioController controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardapio);

		setCurrentDateOnView();
		addListenerOnButton();

		controller = new CardapioController(this);
		String[] semana = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta" };

		List<br.ufrn.ru_ufrn.model.Cardapio> temp = controller
				.getCardapiosDaSemana();

		for (int i = 0; i < semana.length; i++) {
			cardapioDaSemana.put(semana[i], temp.get(i));
		}

		Spinner spinnerDiasSemana = (Spinner) findViewById(R.id.spinner_dias_semana);
		spinnerDiasSemana
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {
						String dia = (String) parent.getItemAtPosition(pos);
						br.ufrn.ru_ufrn.model.Cardapio temp = cardapioDaSemana.get(dia);
						createData(temp);
						setCardapioExpadableItens();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

	}

	private void createData(br.ufrn.ru_ufrn.model.Cardapio cardapio) {
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
		MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
				groups);
		listView.setAdapter(adapter);
	}

	private void setCardapioItens(br.ufrn.ru_ufrn.model.Cardapio cardapio) {
		br.ufrn.ru_ufrn.model.Cardapio temp = cardapio;
		cardapioItens = (TextView) findViewById(R.id.textView_cardapio_itens);
		cardapioItens.setText(temp.toString());

		String[] refeicoes = new String[] { "Café da manhã",
				"Almoço Vegetariano", "Almoço Carnívoro", "Janta Vegetariana",
				"Janta Carnívora" };
		adapter = new CardapioArrayAdapter(this, R.layout.rowlayout, refeicoes);
		// TODO quebra!
		listview = (ListView) findViewById(R.id.listView);
		listview.setAdapter(adapter);

	}

	private void addListenerOnButton() {

		mudarData = (Button) findViewById(R.id.button_mudar_data);
		mudarData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DatePickerFragment newFragment = new DatePickerFragment();
				newFragment
						.setData((TextView) findViewById(R.id.textView_data_atual));
				newFragment.show(getFragmentManager(), "datePicker");

			}
		});

	}

	public void alterarData(Calendar c) {
		dataAtual = (TextView) findViewById(R.id.textView_data_atual);
		dataAtual.setText(c.getTime().toString());
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
