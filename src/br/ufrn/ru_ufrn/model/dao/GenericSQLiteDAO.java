package br.ufrn.ru_ufrn.model.dao;

import java.io.Serializable;
import java.util.List;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressWarnings("hiding")
public class GenericSQLiteDAO
		extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "RU_UFRN";
	public static final int DATABASE_VERSION = 1;
	public static final String CREATE_TABLES = "";
	protected SQLiteDatabase database;
	protected Context context;

	public GenericSQLiteDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	
	public void save(SQLiteEntity entity) throws DAOException {
		
		database = this.getWritableDatabase();

		database.insert(entity.getTableName(), null, entity.getValues());

		database.close();
				
	}
	
	public void query(SQLiteEntity entity) throws DAOException{
		
		database = this.getReadableDatabase();

		entity.setResult(database.rawQuery(entity.getQuery(),null));

		database.close();
		
	}
	

	
	
	

}
