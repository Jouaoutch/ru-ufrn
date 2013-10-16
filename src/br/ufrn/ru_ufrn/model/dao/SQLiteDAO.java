package br.ufrn.ru_ufrn.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDAO extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "RU_UFRN";
	private static final int DATABASE_VERSION = 1;

	private final String createTableRefeicao = "CREATE TABLE Refeicao"
			+ " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,nome TEXT NOT NULL,tipo TEXT NOT NULL);";

	private final String createTableAlimento = "CREATE TABLE Alimento ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,nome TEXT NOT NULL,descricao TEXT NOT NULL,imagem TEXT);";

	private final String createTableRefeicao_Alimento = "CREATE TABLE Refeicao_Alimento (id_refeicao INTEGER NOT"
			+ " NULL,id_alimento INTEGER NOT NULL,  PRIMARY KEY(id_refeicao,id_alimento),  FOREIGN KEY "
			+ "(id_refeicao) REFERENCES Refeicao(id) ON DELETE RESTRICT,  FOREIGN KEY (id_alimento) REFERENCES Alimento(id) ON DELETE RESTRICT);";

	private final String createTableCardapio = "CREATE TABLE Cardapio (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,data TEXT NOT NULL);";

	private final String createTableCardapio_Refeicao = "CREATE TABLE Cardapio_Refeicao (id_cardapio INTEGER NOT NULL,id_refeicao INTEGER NOT NULL"
			+ ",  PRIMARY KEY(id_cardapio, id_refeicao),  FOREIGN KEY (id_cardapio) REFERENCES Cardapio(id) ON DELETE RESTRICT,"
			+ "  FOREIGN KEY (id_refeicao) REFERENCES Refeicao(id) ON DELETE RESTRICT);";

	private final String createTableUsuario = "Create table Usuario(idUsuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,login TEXT NOT NULL,senha TEXT NOT NULL);";

	private final String createTableAvaliacao = "create table Avaliacao(idAvaliacao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,avaliacao varchar(10) NOT NULL);";

	private final String createTableAvaliacaoRefeicao = "CREATE TABLE Avaliacao_Cardapio ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,cardapioCumprido "
			+ "boolean NOT NULL,id_refeicao INTEGER NOT NULL, data date NOT NULL, idavaliacao INTEGER NOT null, idUsuario  Integer NOT NULL , FOREIGN KEY (idUsuario)"
			+ " REFERENCES Usuario(idUsuario) ON DELETE RESTRICT,  FOREIGN KEY (id_refeicao) REFERENCES Refeicao(id) ON DELETE RESTRICT,  FOREIGN KEY (idAvaliacao) REFERENCES Avaliacao (idAvaliacao) ON DELETE RESTRICT );";

	private final String createTableComentario = "Create table Comentario (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,idUsuario  NOT NULL,comentario Integer not null,image TEXT,FOREIGN KEY (idUsuario) "
			+ "REFERENCES Usuario(idUsuario) ON DELETE RESTRICT);";

	/*
	 * private final String insertAvaliacao =
	 * "insert into Avaliacao values  (Gostei);" +
	 * "insert into Avaliacao values (Desgostei);" +
	 * "insert into Avaliacao values (Indiferente);";
	 */

	public SQLiteDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

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
		db.execSQL(createTableComentario);
		// db.execSQL(insertAvaliacao);
		// tira isso depois
		db.execSQL("insert into Usuario (login, senha) values ('jorge', 'jorge');");
		db.execSQL("insert into Refeicao (nome, tipo) values ('almoço','almoço vegetariano');");
		db.execSQL("insert into Avaliacao (avaliacao) values('Gostei');");
		db.execSQL("insert into Avaliacao (avaliacao) values('Desgostei');");
		db.execSQL("insert into Avaliacao (avaliacao) values('Indiferente');");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public SQLiteDatabase getDatabaseWrite() {
		return this.getWritableDatabase();
	}

	public SQLiteDatabase getDatabaseRead() {
		return this.getReadableDatabase();
	}

}
