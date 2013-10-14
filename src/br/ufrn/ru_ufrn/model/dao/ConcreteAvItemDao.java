package br.ufrn.ru_ufrn.model.dao;

import java.util.Date;
import java.util.List;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.AvaliacaoItem;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConcreteAvItemDao extends SQLiteOpenHelper implements AvaliacaoItemDao{

	private Context context;
	private static final String banco = "RU_UFRN";
	private SQLiteDatabase database;
	
	private static final String createTable = "create table avaliacoes_item(" +
			"idAvItem integer primary key autoincrement not null," +
			"item varchar(20) not null," +
			"avaliacao varchar(10) not null," +
			"data Date not null," +
			"refericao  varchar(20) );";
	
	public ConcreteAvItemDao(Context context){
	super(context, banco, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTable);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avaliarItem(AvaliacaoItem avItem) throws DAOException {
		ContentValues values = new ContentValues(5);

		values.put("item", avItem.getItem());
		values.put("refeicao", avItem.getRefeicao());
		values.put("data", avItem.getDataFormatoAmericano());
		values.put("avaliacao", avItem.getNivelSatisfacao().toString());
		values.put("idUsuario", avItem.getIdUsuario());

		database = this.getWritableDatabase();

		database.insert("avaliacoes_item", null, values);

		database.close();
		
	}

	@Override
	public void atualizarAvItem(AvaliacaoItem avItem) throws DAOException {
		ContentValues values = new ContentValues(5);

		values.put("valiacao", avItem.getNivelSatisfacao().toString());

		database = this.getWritableDatabase();

		String args[] = {String.valueOf(avItem.getIdAvaliacao()), String.valueOf(avItem.getIdUsuario())};
		database.update("avaliacoes_item", values, "IdAvaliacao = ? and idUsuario = ?", args);

		database.close();
		
	}

	@Override
	public List<AvaliacaoItem> getResultadoAvItens(Date data, String refeicao)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
