package br.ufrn.ru_ufrn;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		Button button = (Button) findViewById(R.id.btEntrar);
		button.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	Intent intent = new Intent(Login.this,
						Avaliar.class);
				startActivity(intent);
		    }
		});
		
	
	}
	
	/*public void nextScreem(View view){
		Intent intent = new Intent(Login.this,
				Avaliar.class);
		startActivity(intent);
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
