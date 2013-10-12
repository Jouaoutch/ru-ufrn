package br.ufrn.ru_ufrn;

import java.util.Date;

import br.ufrn.ru_ufrn.controller.AvaliacaoController;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.exceptions.ValorInvalidoException;
import br.ufrn.ru_ufrn.exceptions.ValorNuloException;
import br.ufrn.ru_ufrn.model.Avaliacao;
import br.ufrn.ru_ufrn.model.NivelSatisfacao;
import br.ufrn.ru_ufrn.model.Usuario;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Avaliar extends Activity {

	private Avaliacao avaliacao = new Avaliacao();
	private Button votar;
	private AvaliacaoController avController = new AvaliacaoController(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_avaliar);
		
		addListenerOnButton();
		
		Usuario user = new Usuario();
		user.setId(1L);
		user.setLogin("qualquer coisa");
		
		try {
			Avaliacao av = avController.getUltimaAvaliacao(user, new Date(System.currentTimeMillis()));
			if(av.getData() != null)
			System.out.println(av.getDataFormatoAmericano());
		} catch (ValorInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValorNuloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.avaliar, menu);
		return true;
	}

	public void exibirActivity(Class classe) {

		Intent intent = new Intent(this, classe);
		startActivity(intent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menuCardapioSemana:
			exibirActivity(CardapioSemana.class);
			return true;
		case R.id.menuAvaliarItem:
			exibirActivity(AvaliarItemIndividual.class);
			return true;
		case R.id.menucomentario:
			exibirActivity(Comentario.class);
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

	public void RbAvaliacaoCliecked(View view) {

		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		NivelSatisfacao nivelSatisfacao = null;

		// Check which radio button was clicked
		switch (view.getId()) {

		case R.id.rbGostei:
			if (checked) {
				nivelSatisfacao = NivelSatisfacao.GOSTEI;
			}

			break;
		case R.id.rbDesgostei:
			if (checked) {
				nivelSatisfacao = NivelSatisfacao.DESGOSTEI;
			}
			break;

		case R.id.rbIndiferente:
			if (checked) {
				nivelSatisfacao = NivelSatisfacao.DESGOSTEI;
			}
			break;

		}

		avaliacao.setNivelSatisfacao(nivelSatisfacao);

	}

	public void RbCardapioCumprido(View view) {

		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		NivelSatisfacao nivelSatisfacao = null;

		// Check which radio button was clicked
		switch (view.getId()) {

		case R.id.rbSim:
			if (checked) {
				avaliacao.setCardapioCumprido(true);
			}
			break;

		case R.id.rbNao:
			if (checked) {
				avaliacao.setCardapioCumprido(false);
			}

		}

	}
	
	private void addListenerOnButton() {

		votar = (Button) findViewById(R.id.btVotar);
		votar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					avaliacao.setData(new Date(System.currentTimeMillis()));
					avaliacao.setIdUsuario(1);
					avaliacao.setRefeicao(avaliacao.ALMOCO_CARNIVORO);
					
					avController.avaliarRefeicao(avaliacao);
					
					avaliacao = new Avaliacao();
					System.out.println("Votado!");
				} catch (ValorNuloException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ValorInvalidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}
}
