package br.ufrn.ru_ufrn.controller.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
 
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import br.ufrn.ru_ufrn.model.Cardapio;
 

public class CardapioClientService {
	
	
	public CardapioClientService() {
	}

	public Cardapio getCardapioDoDia(){
	    Cardapio output = null;
	    
	    String path_resource = "/get_by_date";
	    
        DefaultHttpClient client = new DefaultHttpClient();
        HttpUriRequest req = 
        		new HttpGet(ServiceResources.URL+
        				ServiceResources.RESOURCE+
        				path_resource+
        				"/"+(new java.sql.Date(System.currentTimeMillis()).toString()));
        HttpResponse res = null;
        InputStream in = null;
		try {
			res = client.execute(req);
	        in = res.getEntity().getContent();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        Serializer ser = new Persister();
        try {
			output = ser.read(Cardapio.class, in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
	    
	    return output;
		
	}
	
	
}
