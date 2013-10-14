package br.ufrn.ru_ufrn.model.dao;

import android.content.Context;

public class SQLiteDAOFactory extends DAOFactory{

	
	private Context context;
	public SQLiteDAOFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CardapioDAO getCardapioDAO() {
		return new CardapioSQLiteDAO(context);
	}
	
	public CardapioDAO getCardapioDAO(Context context) {
		this.context = context;
		return getCardapioDAO();
	}

}
