package br.ufrn.ru_ufrn.model.dao;

import java.sql.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Avaliacao;
import br.ufrn.ru_ufrn.model.ResultadoAvaliacoes;

public class ConcreteAvaliacaoDAO extends SQLiteOpenHelper implements
		AvaliacaoDAO {

	private static final String name = "RU_UFRN";
	private static final int version = 1;
	private static final String createTable = "CREATE TABLE IF NOT EXISTS `avaliacoes` ("
			+ "`idAvaliacao` bigint(20) NOT NULL AUTO_INCREMENT,"
			+ "`cardapioCumprido` boolean NOT NULL, "
			+ "`refeicao` enum('CAFE','ALMOCO','JANTAR') NOT NULL, "
			+ "`data` date NOT NULL, "
			+ "`avaliacao` enum('GOSTEI','DESGOSTEI','INDIFERENTE') NOT NULL, "
			+ "`idUsuario` bigint(20) NOT NULL,"
			+ "PRIMARY KEY (`idAvaliacao`)" + ");";

	public ConcreteAvaliacaoDAO(Context context) {
		super(context, name, null, version);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(createTable);

	}

	@Override
	public void avaliarRefeicao(Avaliacao avaliacao) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Avaliacao getUltimaAvaliacao() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultadoAvaliacoes getResultadoAvaliacoes(Date data)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

}
