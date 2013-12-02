package br.ufrn.ru_ufrn;

import br.ufrn.ru_ufrn.model.dao.SQLiteCreateDB;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TelaInicial extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_inicial);

		Button btnCardapio = (Button) findViewById(R.id.button_cardapio);
		btnCardapio.setOnClickListener(this);
		Button btnAvaliacoes = (Button) findViewById(R.id.button_avaliacoes);
		btnAvaliacoes.setOnClickListener(this);
		Button btnEstatisticas = (Button) findViewById(R.id.button_estatistica);
		btnEstatisticas.setOnClickListener(this);
		Button btnComentarios = (Button) findViewById(R.id.button_comentarios);
		btnComentarios.setOnClickListener(this);
		
		createDB();

	}

	private void createDB() {
		//new SQLiteCreateDB(this).start();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.geral, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		Button bnt = (Button) v;

		switch (bnt.getId()) {
		case R.id.button_cardapio:
			exibirActivity(Cardapio.class);
			return;
		case R.id.button_avaliacoes:
			exibirActivity(Avaliar.class);
			return;
		case R.id.button_estatistica:
			exibirActivity(EstatisticasCardapio.class);
			return;
		case R.id.button_comentarios:
			exibirActivity(Comentar.class);
			return;
		default:
			return;

		}

	}

	private void exibirActivity(Class classe) {

		Intent intent = new Intent(this, classe);
		startActivity(intent);
	}

}
