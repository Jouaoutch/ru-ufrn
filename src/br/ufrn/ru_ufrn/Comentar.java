package br.ufrn.ru_ufrn;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.ru_ufrn.adapter.AdapterListView;
import br.ufrn.ru_ufrn.controller.ComentarioController;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.exceptions.ValorInvalidoException;
import br.ufrn.ru_ufrn.exceptions.ValorNuloException;
import br.ufrn.ru_ufrn.model.Comentario;
import br.ufrn.ru_ufrn.model.ItemListView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Comentar extends Activity {

	private ListView listView;
	private AdapterListView adapter;
	private List<Comentario> comentarios;
	private ComentarioController comController;
	private Button enviar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comentario);

		comController = new ComentarioController(this);

		exibirComsntarios();
		
		addListenerOnButton();

	}

	private void exibirComsntarios() {
		try {
			comentarios = comController.getcomentarios();

			listView = (ListView) findViewById(R.id.list);

			adapter = new AdapterListView(this, comentarios,
					this.findViewById(R.layout.activity_comentario));

			listView.setAdapter(adapter);

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comentario, menu);
		return true;
	}

	private void addListenerOnButton() {

		enviar = (Button) findViewById(R.id.btEnviar);
		enviar.setOnClickListener(new OnClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				Comentario com = new Comentario();
				EditText comentario = (EditText) findViewById(R.id.comentario);

				com.setComentario(comentario.getText().toString());
				com.setIdUsuario(1);
				com.setImagem(R.drawable.jorge);
				com.setUsuario("Jorge Pereira");
				
				

				try {
					comController.salvarComentario(com);
					
					exibirComsntarios();
					
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ValorInvalidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ValorNuloException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	public void exibirActivity(Class classe) {

		Intent intent = new Intent(this, classe);
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
