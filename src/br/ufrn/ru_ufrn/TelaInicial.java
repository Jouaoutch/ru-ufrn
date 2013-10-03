package br.ufrn.ru_ufrn;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TelaInicial extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_inicial);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.geral, menu);
		return true;
	}

}
