package br.ufrn.ru_ufrn.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Formatter;
import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.ufrn.ru_ufrn.R;
import br.ufrn.ru_ufrn.model.Comentario;
import br.ufrn.ru_ufrn.model.ItemListView;

//http://diegorubin.com/2012/02/17/desenvolvimento-para-android-utilizando-uma-listview

public class AdapterListView extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Comentario> comentarios;
	private int imagem;
	private View view;

	public AdapterListView(Context context, List<Comentario> comentarios, View view) {
		// Itens do listview
		this.comentarios = comentarios;
		// Objeto responsável por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);
		this.view = view;
	}

	public int getCount() {
		return comentarios.size();
	}

	public Comentario getItem(int position) {
		return comentarios.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		ItemSuporte itemHolder;
		// se a view estiver nula (nunca criada), inflamos o layout nela.
		if (view == null) {
			// infla o layout para podermos pegar as views
			view = mInflater.inflate(R.layout.item_list, null);

			// cria um item de suporte para não precisarmos sempre
			// inflar as mesmas informacoes
			itemHolder = new ItemSuporte();
			itemHolder.txtTitle = ((TextView) view.findViewById(R.id.text));
			itemHolder.imgIcon = ((ImageView) view
					.findViewById(R.id.imagemview));
			itemHolder.user = ((TextView) view.findViewById(R.id.usuario));

			// define os itens na view;
			view.setTag(itemHolder);
		} else {
			// se a view já existe pega os itens.
			itemHolder = (ItemSuporte) view.getTag();
		}

		// pega os dados da lista
		// e define os valores nos itens.
		Comentario comentario = comentarios.get(position);
		itemHolder.txtTitle.setText(comentario.getComentario());
		itemHolder.imgIcon.setImageResource(comentario.getImagem());
		itemHolder.user.setText(comentario.getUsuario());
		
		

		// retorna a view com as informações
		return view;
	}
	
	
	/**
	 * Classe de suporte para os itens do layout.
	 */
	private class ItemSuporte {

		ImageView imgIcon;
		TextView txtTitle;
		TextView user;
	}
	
	
	}

