package br.ufrn.ru_ufrn;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.drawable.Drawable;

public class ImageUtil {
	
	private URL url;
	
	public Drawable getImagem(String src) throws Exception {
	    url = new URL(src);
	 
	    InputStream is = (InputStream) getObjeto(url);
	    Drawable d = Drawable.createFromStream(is, src);
	 
	    return d;
	}
	 
	private Object getObjeto(URL url) throws MalformedURLException, IOException {
	    Object content = url.getContent();
	    return content;
	}
}
