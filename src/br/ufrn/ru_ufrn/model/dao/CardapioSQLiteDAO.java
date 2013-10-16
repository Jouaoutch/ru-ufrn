package br.ufrn.ru_ufrn.model.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.ufrn.ru_ufrn.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Alimento;
import br.ufrn.ru_ufrn.model.Cardapio;
import br.ufrn.ru_ufrn.model.Refeicao;
import br.ufrn.ru_ufrn.model.dao.CardapioDAO;

public class CardapioSQLiteDAO extends SQLiteOpenHelper implements CardapioDAO {

	private static final String DATABASE_NAME = "RU_UFRN";
	private static final int DATABASE_VERSION = 1;

	private SQLiteDatabase database;
	private Context context;
	private String createTable = "";
	public CardapioSQLiteDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cardapio findById(Class<Cardapio> classe, Integer id)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// createTable = carregarArquivoSQL();
		db.execSQL(createTable);

	}

	private String carregarArquivoSQL() {
		InputStream is = this.context.getResources().openRawResource(
				R.raw.create_table_cardapio);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			line = br.readLine();

			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Cardapio> findAll(Class<Cardapio> classe) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cardapio save(Cardapio cardapio) throws DAOException {

		if (cardapio.getCafeDaManha().getId() == null) {
			save(cardapio.getCafeDaManha());
		}

		return null;
	}

	private void save(Refeicao refeicao) {
		for (Iterator<Alimento> iterator = refeicao.getItens().iterator(); iterator
				.hasNext();) {
			Alimento alimento = iterator.next();
			if (alimento.getId() == null) {
				save(alimento);
			}

		}
	}

	private void save(Alimento alimento) {
		ContentValues values = new ContentValues(3);

		values.put("nome", alimento.getNome());
		values.put("descricao", alimento.getDescricao());
		values.put("imagem", alimento.getImagem());

		this.database = this.getWritableDatabase();

		this.database.insert("Alimento", null, values);

		this.database.close();

	}

	@Override
	public Cardapio update(Cardapio entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Cardapio entity) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Cardapio findByData(Date data) {
		// TODO Auto-generated method stub
		return null;
	}

}
