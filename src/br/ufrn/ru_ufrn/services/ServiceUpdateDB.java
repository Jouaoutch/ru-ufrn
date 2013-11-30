package br.ufrn.ru_ufrn.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.ufrn.ru_ufrn.controller.service.CardapioClientService;
import br.ufrn.ru_ufrn.model.Cardapio;
import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

public class ServiceUpdateDB extends IntentService {

	private CardapioClientService cardCliServ;
	
	public ServiceUpdateDB(String name) {
		super(name);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}

	private void updateDB(){
		
		List<Cardapio> cardapios = null;
		cardapios = loadCardapiosDaSemana();
		
		
		
		
	}
	
	
	
	

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
	}

	private List<Cardapio> loadCardapiosDaSemana() {
		List<Cardapio> output = null;
		AsyncTask<Void, Void, List<Cardapio>> at = new AsyncTask<Void, Void, List<Cardapio>>() {

			@Override
			protected List<br.ufrn.ru_ufrn.model.Cardapio> doInBackground(
					Void... params) {
				return cardCliServ.getCardapiosDaSemana();
			}
		};

		at.execute();
		try {
			output = at.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	
	
}
