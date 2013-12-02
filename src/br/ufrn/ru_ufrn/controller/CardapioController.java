package br.ufrn.ru_ufrn.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import br.ufrn.ru_ufrn.MockCardapio;
import br.ufrn.ru_ufrn.controller.service.CardapioClientService;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Cardapio;
import br.ufrn.ru_ufrn.model.dao.CardapioDAO;
import br.ufrn.ru_ufrn.model.dao.CardapioSQLiteDAO;
import br.ufrn.ru_ufrn.util.CalendarioUtil;

@SuppressLint("ShowToast")
public class CardapioController {
	
	
	private static final int ON = 1;
	private static final int OFF = 2;
	private static final int LOW = 3;

	
	CardapioDAO cardapiodao ;
	private int conexao_internet;
	private Context context;
	CardapioClientService cardCliServ ;
	
	public CardapioController(Context context) {
		this.context = context;
	}
	
	public List<Cardapio>getCardapiosDaSemana(){
		
		List<Cardapio> cardapios = new ArrayList<Cardapio>();
		
		String[] dias_da_semana = CalendarioUtil.getDiasDaSemana();
		
		if(cardapiodao == null){
			cardapiodao = new CardapioSQLiteDAO(this.context);
		}
		for (int i = 0; i < dias_da_semana.length; i++) {
			Cardapio card = null;
			try {
				card = cardapiodao.findByData(dias_da_semana[i]);
			} catch (DAOException e) {
				System.out.println("Erro DB: Erro ao recuperar o cardapio do dia "+dias_da_semana[i]);
				Toast.makeText(this.context, "Erro ao recuperar o cardapio do dia "+dias_da_semana[i], Toast.LENGTH_LONG);
				e.printStackTrace();
			}
			cardapios.add(card);
		}
		
		
		return cardapios;
	}
	
	public Cardapio getCardapioDoDia(){
		Cardapio cardapio = null;
		cardCliServ = new CardapioClientService();
		cardapio = cardCliServ.getCardapioDaData(new java.sql.Date(System.currentTimeMillis()).toString());
		return cardapio;
	}
	

	

	private void testConexao() {
		
		URL url = null;
		try {
			url = new URL("http://www.google.com");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		try {
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			this.conexao_internet = OFF;
		}
		this.conexao_internet = ON;
		
	}
}
