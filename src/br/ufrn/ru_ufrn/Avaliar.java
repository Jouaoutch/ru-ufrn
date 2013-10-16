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
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class Avaliar extends Activity {

	private Avaliacao avaliacao = new Avaliacao();
	private Button votar;
	private AvaliacaoController avController = new AvaliacaoController(this);
	private DialogAtualizarAvaliacao dialogAtualizarAvaliacao = new DialogAtualizarAvaliacao(
			this);
	private static final int cafe = 0, almocoCarnivoro = 1, AlmocoVegetariano = 2,
			jantarCarnivoro = 3, jantarVegetariano = 4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_avaliar);

		addListenerOnButton();

		verificarAvaliacoes();
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner_refeicoes);
		spinner.setOnItemSelectedListener(new SpinnerActivity());
		
	}

	// verifica se ja exixte uma valiação pra refeição em questão, se exixtir
	// seta os dados
	private void verificarAvaliacoes() {

		Usuario user = new Usuario();
		user.setId(1L);
		user.setLogin("qualquer coisa");

		try {
			avaliacao = avController.getUltimaAvaliacao(user,
					new Date(System.currentTimeMillis()));
			if (avaliacao != null) {

				RadioButton ns = null;
				if (avaliacao.getIdAvaliacao() == 2) {
					ns = (RadioButton) findViewById(R.id.rbDesgostei);
					ns.setChecked(true);
				} else if (avaliacao.getIdAvaliacao() == 1) {
					ns = (RadioButton) findViewById(R.id.rbGostei);
					ns.setChecked(true);
				} else if (avaliacao.getIdAvaliacao() == 3) {
					ns = (RadioButton) findViewById(R.id.rbIndiferente);
					ns.setChecked(true);
				}

				RadioButton cc = null;

				if (avaliacao.isCardapioCumprido()) {
					cc = (RadioButton) findViewById(R.id.rbSim);
					cc.setChecked(true);
				} else if (!avaliacao.isCardapioCumprido()) {
					cc = (RadioButton) findViewById(R.id.rbNao);
					cc.setChecked(true);
				}

			}else{
				avaliacao = new Avaliacao();
			}
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

	public void RbAvaliacaoCliecked(View view) {

		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		int idAvaliacao = -1 ;

		// Check which radio button was clicked
		switch (view.getId()) {

		case R.id.rbGostei:
			if (checked) {
				idAvaliacao = 1;
			}

			break;
		case R.id.rbDesgostei:
			if (checked) {
				idAvaliacao = 2;
			}
			break;

		case R.id.rbIndiferente:
			if (checked) {
				idAvaliacao = 3;
			}
			break;

		}

		avaliacao.setIdAvaliacao(idAvaliacao);

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
					if (avaliacao.getIdAvaliacao() == -1) {
						realizarAvaliacao();
					} else {
						atualizarAvaliacao();
						System.out.println(avaliacao.getIdAvaliacao()+"  "+avaliacao.getId()+"  "+avaliacao.getCardapioCumprido()); 
					}

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

			private void atualizarAvaliacao() throws ValorNuloException,
					ValorInvalidoException, DAOException {
				avController.atualizarAvaliacao(avaliacao);

				dialogAtualizarAvaliacao.show(getFragmentManager(),
						"Atualizar Avaliação");

			}

			private void realizarAvaliacao() throws ValorNuloException,
					ValorInvalidoException, DAOException {

				avaliacao.setData(new Date(System.currentTimeMillis()));
				System.out.println(avaliacao.getDataFormatoAmericano());
				avaliacao.setIdUsuario(1L);
				avaliacao.setIdRefeicao(0);

				avController.avaliarRefeicao(avaliacao);

				Usuario user = new Usuario();
				user.setId(1L);
				user.setLogin("qualquer coisaq");
				avaliacao = avController.getUltimaAvaliacao(user,
						avaliacao.getData());

				exibirMesssageToast("Avaliação realizada com sucesso!");

			}

			private void exibirMesssageToast(String message) {
				Context context = getApplicationContext();
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, message, duration);
				toast.show();
			}
		});

	}

	private class DialogAtualizarAvaliacao extends DialogFragment {

		private Context context;

		public DialogAtualizarAvaliacao(Context context) {
			this.context = context;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the Builder class for convenient dialog construction
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage("Deseja atualizar sua avaliação?")
					.setPositiveButton("Sim",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									try {
										avController
												.atualizarAvaliacao(avaliacao);

										int duration = Toast.LENGTH_SHORT;

										Toast toast = Toast
												.makeText(
														context,
														"Avaliação atualizada com sucesso!",
														duration);
										toast.show();

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
							})
					.setNegativeButton("Não",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// Do nothing
								}
							});
			// Create the AlertDialog object and return it
			return builder.create();
		}

	}
	
	private void setarDados(int refeicao){
		//aqui setar os alimentos da refeicao quando o dao estiver pronto
	}
	
	private class SpinnerActivity extends Activity implements OnItemSelectedListener {
	  

		

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			setarDados(arg2);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
