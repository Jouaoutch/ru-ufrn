package br.ufrn.ru_ufrn.model.dao;

import java.io.Serializable;
import java.util.List;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

@SuppressWarnings("hiding")
public class GenericSQLiteDAO extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "RU_UFRN";
	public static final int DATABASE_VERSION = 1;
	public final String createTableRefeicao = "CREATE TABLE Refeicao ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "nome TEXT NOT NULL," + "tipo TEXT NOT NULL);";

	public final String createTableAlimento = "CREATE TABLE Alimento ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "nome TEXT NOT NULL," + "descricao TEXT NOT NULL,"
			+ "imagem TEXT);";

	public final String createTableRefeicao_Alimento = "CREATE TABLE Refeicao_Alimento (	"
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,	"
			+ "id_refeicao INTEGER NOT NULL,	"
			+ "id_alimento INTEGER NOT NULL,	"
			+ " FOREIGN KEY (id_refeicao) REFERENCES Refeicao(id) ON DELETE RESTRICT,	"
			+ " FOREIGN KEY (id_alimento) REFERENCES Alimento(id) ON DELETE RESTRICT );";

	public final String createTableCardapio = "CREATE TABLE Cardapio ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "data DATE NOT NULL);";

	public final String createTableCardapio_Refeicao = "CREATE TABLE Cardapio_Refeicao (	"
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,	"
			+ "id_cardapio INTEGER NOT NULL,	"
			+ "id_refeicao INTEGER NOT NULL, 	"
			+ " FOREIGN KEY (id_cardapio) REFERENCES Cardapio(id) ON DELETE RESTRICT, "
			+ " FOREIGN KEY (id_refeicao) REFERENCES Refeicao(id) ON DELETE RESTRICT );";

	public final String createTableUsuario = "Create table Usuario("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "login TEXT NOT NULL," + "senha TEXT NOT NULL);";

	public final String createTableAvaliacao = "create table Avaliacao("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "avaliacao varchar(10) NOT NULL);";

	public final String createTableAvaliacaoRefeicao = "CREATE TABLE Avaliacao_Cardapio ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "id_refeicao INTEGER NOT NULL, "
			+ "id_avaliacao INTEGER NOT null, "
			+ "id_usuario  Integer NOT NULL,"
			+ "data date NOT NULL, "
			+ "cardapio_cumprido boolean NOT NULL,"
			+ "FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE RESTRICT, "
			+ "FOREIGN KEY (id_refeicao) REFERENCES Refeicao(id) ON DELETE RESTRICT, "
			+ "FOREIGN KEY (id_avaliacao) REFERENCES Avaliacao (id) ON DELETE RESTRICT );";

	public final String insertAvaliacao = "insert into Avaliacao (avaliacao) values  ('Gostei');"
			+ "insert into Avaliacao  (avaliacao) values ('Desgostei');"
			+ "insert into Avaliacao  (avaliacao) values ('Indiferente');";
	protected SQLiteDatabase database;
	protected Context context;

	public GenericSQLiteDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTableRefeicao);
		db.execSQL(createTableAlimento);
		db.execSQL(createTableRefeicao_Alimento);
		db.execSQL(createTableCardapio);
		db.execSQL(createTableCardapio_Refeicao);
		db.execSQL(createTableUsuario);
		db.execSQL(createTableAvaliacao);
		db.execSQL(createTableAvaliacaoRefeicao);
		Toast.makeText(context, "no OnCreate do GenericSQLiteDAO",
				Toast.LENGTH_LONG).show();
	}

	public void bootstrap() {
		database = this.getWritableDatabase();
		database.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void save(SQLiteEntity entity) throws DAOException {

		try {
			database = this.getWritableDatabase();

			long row = database.insert(entity.getTableName(), null,
					entity.getValues());
			if (row == -1) {
				throw new DAOException("Erro ao inserir na tabela "
						+ entity.getTableName());
			}
			Integer idMax = findMaxID(entity.getTableName());
			entity.setIdSaved(idMax);
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}

		finally {

			database.close();
		}

	}

	@SuppressLint("UseValueOf")
	public Integer findMaxID(String tableName) throws DAOException {

		Integer max = null;

		try {
			if (database == null) {
				database = this.getReadableDatabase();
			}

			Cursor cursor = database.rawQuery("SELECT MAX(id) FROM "
					+ tableName + " ;", null);

			if (cursor.moveToLast()) {
				max = new Integer(cursor.getInt(0));
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		} finally {
			//database.close();
		}

		return max;
	}

	public void query(SQLiteEntity entity) throws DAOException {

		try {
			database = this.getReadableDatabase();

			entity.setResult(database.rawQuery(entity.getQuery(), null));
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		} finally {
			database.close();
		}

	}

}
