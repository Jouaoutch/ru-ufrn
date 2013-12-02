package br.ufrn.ru_ufrn.controller.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.RuntimeDelegate;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import android.annotation.SuppressLint;
import br.ufrn.ru_ufrn.model.Cardapio;
import br.ufrn.ru_ufrn.util.CalendarioUtil;
 

@SuppressLint("SimpleDateFormat")
public class CardapioClientService {
	
	
	public CardapioClientService() {
	}

	public Cardapio getCardapioDoDia(){
		
		Cardapio cardapio = null;
		RuntimeDelegate.setInstance(new com.sun.ws.rs.ext.RuntimeDelegateImpl());
		//ServiceFinder.setIteratorProvider(new AndroidServiceIteratorProvider());
	    Client client = Client.create(); 
	    WebResource resource = client.resource(ServiceResources.URL_RESOURCE+"/cardapio/hoje"); 
	    if(resource != null)
	    cardapio = resource.type(MediaType.APPLICATION_JSON).get(Cardapio.class);  
	    
	    return cardapio;
		
	}
	
	
	
	public Cardapio getCardapioDaData(String date){
		Cardapio cardapio = null;
		URL serverAddress;
		try {
			serverAddress = new URL(ServiceResources.URL_RESOURCE+"/cardapio/data/"+date);
			HttpURLConnection connection = (HttpURLConnection) serverAddress.openConnection(); 
			connection.setRequestMethod("GET"); 
			connection.connect(); 
			BufferedReader rd = new BufferedReader(new InputStreamReader( connection.getInputStream())); 			
			StringBuilder sb = new StringBuilder();
			String temp = rd.readLine();
			while(temp != null){
				sb.append(temp);
				temp = rd.readLine();
			}
			Gson gson = new Gson();			
			cardapio = gson.fromJson(sb.toString(), Cardapio.class);
			System.out.println("chamada realizada as: "+new java.util.Date(System.currentTimeMillis()));
			//System.out.println(gson.toJson(cardapio));
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return cardapio;
		
	}

	public List<Cardapio> getCardapiosDaSemana() {
		String[] dias_da_semana = CalendarioUtil.getDiasDaSemana();
		List<Cardapio> lista = new ArrayList<Cardapio>();
		
		for (int i = 0; i < dias_da_semana.length; i++) {
			Cardapio temp = getCardapioDaData(dias_da_semana[i]);
			lista.add(temp);
		}
		return lista;
	}
	
	



	public String cardapioAlterado(String date) {
		URL serverAddress;
		String result = "";
		try {
			serverAddress = new URL(ServiceResources.URL_RESOURCE+"/cardapio/alterado/"+date);
			HttpURLConnection connection = (HttpURLConnection) serverAddress.openConnection(); 
			connection.setRequestMethod("GET"); 
			connection.connect(); 
			BufferedReader rd = new BufferedReader(new InputStreamReader( connection.getInputStream())); 			
			StringBuilder sb = new StringBuilder();
			String temp = rd.readLine();
			while(temp != null){
				sb.append(temp);
				temp = rd.readLine();
			}
			Gson gson = new Gson();			
			result = gson.fromJson(sb.toString(), String.class);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; 
	}
	
	
	
}
