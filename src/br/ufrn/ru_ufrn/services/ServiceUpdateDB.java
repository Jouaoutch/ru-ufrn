package br.ufrn.ru_ufrn.services;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.ufrn.ru_ufrn.controller.service.CardapioClientService;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Cardapio;
import br.ufrn.ru_ufrn.model.dao.CardapioSQLiteDAO;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class ServiceUpdateDB extends Service {

	private CardapioClientService cardCliServ;
	
	public ServiceUpdateDB() {
		super();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this,
				"Service UpdateDB iniciado!",
				Toast.LENGTH_LONG).show();
		
	}

	private void updateDB(){
		
		List<Cardapio> cardapios = null;
		
		//if (cardapioDaSemanaAlterado()) {

		System.out.println("UpdateDB: entrou no updateDB");

		cardapios = loadCardapiosDaSemana();
		if (cardapios != null) {
			System.out.println("Cardapios da semana recuperados do servidor com sucesso!");

			CardapioSQLiteDAO dao = new CardapioSQLiteDAO(this);
			for (Iterator<Cardapio> iterator = cardapios.iterator(); iterator
					.hasNext();) {
				Cardapio cardapio = iterator.next();
				try {
					dao.save(cardapio);
				} catch (DAOException e) {
					Toast.makeText(
							this,
							"Erro ao salvar/atualizar o cardapio do dia "
									+ cardapio.getData() + "!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		}
	/*	} else{
			Toast.makeText(this,
					"Cardapio não foi alterado!",
					Toast.LENGTH_LONG).show();
	
		}
		*/
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		updateDB();
		return super.onStartCommand(intent, flags, startId);
	}
	
	

	private boolean cardapioDaSemanaAlterado() {
		String alterado = "";
		
		AsyncTask<Void, Void, String> at = new AsyncTask<Void, Void, String>() {
			
			@Override
			protected String doInBackground(Void... params) {
				if(cardCliServ == null){
					cardCliServ = new CardapioClientService();
				}
				String data_do_atual_cardapio_da_semana = "";
				return cardCliServ.cardapioAlterado(data_do_atual_cardapio_da_semana);
			}
		};
		
		at.execute();
		try {
			alterado = at.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean result = alterado.equals("true")?true:false;
		
		return result;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	

	private List<Cardapio> loadCardapiosDaSemana() {
		List<Cardapio> cardapios = null;
		AsyncTask<Void, Void, List<Cardapio>> at = new AsyncTask<Void, Void, List<Cardapio>>() {

			@Override
			protected List<br.ufrn.ru_ufrn.model.Cardapio> doInBackground(
					Void... params) {
				if(cardCliServ == null){
					cardCliServ = new CardapioClientService();
				}
				return cardCliServ.getCardapiosDaSemana();
			}
		};

		at.execute();
		try {
			cardapios = at.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return cardapios;
	}
	
	
	
}
