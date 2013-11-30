package br.ufrn.ru_ufrn.controller.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.RuntimeDelegate;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.service.ServiceFinder;

import br.ufrn.ru_ufrn.model.Cardapio;
 

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
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
