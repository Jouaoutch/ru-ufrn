package br.ufrn.ru_ufrn.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import br.ufrn.ru_ufrn.MockCardapio;
import br.ufrn.ru_ufrn.controller.service.CardapioClientService;
import br.ufrn.ru_ufrn.model.Cardapio;
import br.ufrn.ru_ufrn.model.dao.CardapioDAO;
import br.ufrn.ru_ufrn.model.dao.CardapioSQLiteDAO;

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
		
		testConexao();
		List<Cardapio> output = null;
		
		switch(conexao_internet){
			case ON:
				output = prepararCardapioDaSemanaOnLine();
				break;
			case OFF:
				output = prepararCardapioDaSemanaOffLine();
				break;
			case LOW:
				break;
			default:
				break;
		}
		
		return output;
	}
	
	public Cardapio getCardapioDoDia(){
		Cardapio output = null;
		cardCliServ = new CardapioClientService();
		output = cardCliServ.getCardapioDaData(new java.sql.Date(System.currentTimeMillis()).toString());
		return output;
	}
	
	private List<Cardapio> prepararCardapioDaSemanaOnLine(){
		String[] datas = {"2013-11-25", 
				"2013-11-26",
				"2013-11-27",
				"2013-11-28",
				"2013-11-29"};
		List<Cardapio> cardapios = new ArrayList<Cardapio>();
		cardCliServ = new CardapioClientService();
		for (int i = 0; i < datas.length; i++) {
			Cardapio temp = cardCliServ.getCardapioDaData(datas[i]);
			cardapios.add(temp);
			System.out.println(temp);
		}
		
		
		
		return cardapios;
		
	} 
	

	private List<Cardapio> prepararCardapioDaSemanaOffLine() {
		
		
		List<Cardapio> cardapioSemana = new ArrayList<Cardapio>();
		cardapiodao = new CardapioSQLiteDAO(this.context);
		java.sql.Date data = new java.sql.Date(new Date(2013,10,14).getTime());		
		Cardapio temp = MockCardapio.mock(cardapiodao, data.toString());
		//cardapioSemana.add(cardapiodao.findByData(data));
		cardapioSemana.add(temp);		
		
		data = new java.sql.Date(new Date(2013,10,14).getTime());		
		temp = MockCardapio.mock(cardapiodao, data.toString());
		//cardapioSemana.add(cardapiodao.findByData(data));
		cardapioSemana.add(temp);		
		
		data = new java.sql.Date(new Date(2013,10,15).getTime());		
		temp = MockCardapio.mock(cardapiodao, data.toString());
		cardapioSemana.add(temp);		
		
		//cardapioSemana.add(cardapiodao.findByData(data));
		data = new java.sql.Date(new Date(2013,10,16).getTime());		
		temp = MockCardapio.mock(cardapiodao, data.toString());
		cardapioSemana.add(temp);		
		
		//cardapioSemana.add(cardapiodao.findByData(data));
		data = new java.sql.Date(new Date(2013,10,17).getTime());		
		temp = MockCardapio.mock(cardapiodao, data.toString());
		cardapioSemana.add(temp);		
		
		//cardapioSemana.add(cardapiodao.findByData(data));
		data = new java.sql.Date(new Date(2013,10,18).getTime());		
		temp = MockCardapio.mock(cardapiodao, data.toString());
		cardapioSemana.add(temp);		
		
		
		
		
		//cardapioSemana.add(cardapiodao.findByData(data));
		return cardapioSemana;
		
		
		
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
