package br.ufrn.ru_ufrn.model.dao;

import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Avaliacao;
import br.ufrn.ru_ufrn.model.ResultadoAvaliacoes;
import br.ufrn.ru_ufrn.model.Usuario;

public class ConcreteAvaliacaoDAO extends SQLiteOpenHelper implements
		AvaliacaoDAO {

	private static final String name = "RU_UFRN";
	private static final int version = 1;
	private static final String createTable = "CREATE TABLE avaliacoes ("
			+ " idAvaliacao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "cardapioCumprido boolean NOT NULL, "
			+ "refeicao varchar(20), "
			+ "data date NOT NULL, "
			+ "avaliacao varchar(10) NOT NULL, "
			+ "idUsuario  Integer NOT NULL );";

	private SQLiteDatabase database;

	public ConcreteAvaliacaoDAO(Context context) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTable);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	@Override
	public void avaliarRefeicao(Avaliacao avaliacao) throws DAOException {
		ContentValues values = new ContentValues(5);

		values.put("cardapioCumprido", avaliacao.isCardapioCumprido());
		values.put("refeicao", avaliacao.getRefeicao());
		values.put("data", avaliacao.getDataFormatoAmericano());
		values.put("avaliacao", avaliacao.getNivelSatisfacao().toString());
		values.put("idUsuario", avaliacao.getIdUsuario());

		database = this.getWritableDatabase();

		database.insert("avaliacoes", null, values);

		database.close();

	}

	@Override
	public Avaliacao getUltimaAvaliacao(Usuario user) throws DAOException {

		return null;
	}

	@Override
	public ResultadoAvaliacoes getResultadoAvaliacoes(Date data)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
