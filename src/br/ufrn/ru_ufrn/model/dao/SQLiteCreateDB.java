package br.ufrn.ru_ufrn.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SQLiteCreateDB extends SQLiteOpenHelper {



	public static final String DATABASE_NAME = "RU_UFRN";
	public static final int DATABASE_VERSION = 2;
	public final String createTableRefeicao = "CREATE TABLE Refeicao ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "nome TEXT NOT NULL," 
			+ "tipo TEXT NOT NULL);";

	public final String createTableAlimento = "CREATE TABLE Alimento ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "nome TEXT NOT NULL," 
			+ "descricao TEXT NOT NULL,"
			+ "imagem TEXT);";

	public final String createTableRefeicao_Alimento = "CREATE TABLE Refeicao_Alimento (	"
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,	"
			+ "id_refeicao INTEGER NOT NULL,	"
			+ "id_alimento INTEGER NOT NULL,	"
			+ " FOREIGN KEY (id_refeicao) REFERENCES Refeicao(id) ON DELETE CASCADE,	"
			+ " FOREIGN KEY (id_alimento) REFERENCES Alimento(id) ON DELETE CASCADE );";

	public final String createTableCardapio = "CREATE TABLE Cardapio ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "data DATE NOT NULL);";

	public final String createTableCardapio_Refeicao = "CREATE TABLE Cardapio_Refeicao (	"
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,	"
			+ "id_cardapio INTEGER NOT NULL,	"
			+ "id_refeicao INTEGER NOT NULL, 	"
			+ " FOREIGN KEY (id_cardapio) REFERENCES Cardapio(id) ON DELETE CASCADE, "
			+ " FOREIGN KEY (id_refeicao) REFERENCES Refeicao(id) ON DELETE CASCADE );";

	public final String createTableUsuario = "Create table Usuario("
			+ "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
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
			+ "FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE, "
			+ "FOREIGN KEY (id_refeicao) REFERENCES Refeicao(id) ON DELETE CASCADE, "
			+ "FOREIGN KEY (id_avaliacao) REFERENCES Avaliacao (id) ON DELETE CASCADE );";
	
	private final String createTableComentario = "Create table Comentario (" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"id_usuario NOT NULL, " +
			"comentario INTEGER NOT NULL," +
			"image TEXT," +
			"FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario) ON DELETE CASCADE);";

	public final String insertAvaliacao = "insert into Avaliacao (avaliacao) values  ('Gostei');"
			+ "insert into Avaliacao  (avaliacao) values ('Desgostei');"
			+ "insert into Avaliacao  (avaliacao) values ('Indiferente');";
	
	protected SQLiteDatabase database;
	protected Context context;
	
	
	public SQLiteCreateDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {

		executeCreateDB(db);
		
	}


	private void executeCreateDB(SQLiteDatabase db) {
		db.execSQL(createTableRefeicao);
		db.execSQL(createTableAlimento);
		db.execSQL(createTableRefeicao_Alimento);
		db.execSQL(createTableCardapio);
		db.execSQL(createTableCardapio_Refeicao);
		db.execSQL(createTableUsuario);
		db.execSQL(createTableAvaliacao);
		db.execSQL(createTableAvaliacaoRefeicao);
		db.execSQL(createTableComentario);
		db.execSQL(insertAvaliacao);
		db.execSQL("insert into Usuario (login, senha) values ('jorge', 'jorge');");
		Toast.makeText(context, "Banco de dados criado com sucesso",
				Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void start() {
		database = this.getWritableDatabase();
		database.close();
	}

}
