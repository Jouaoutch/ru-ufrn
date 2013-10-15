package br.ufrn.ru_ufrn.model.dao;

import java.io.Serializable;
import java.util.List;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public abstract class GenericSQLiteDAO2 {
	
	private final SQLiteDAO sqLiteDAO;
	private SQLiteDatabase database;

	
	public GenericSQLiteDAO2( Context context){
		
		this.sqLiteDAO = new SQLiteDAO(context);
	}

	
	public void save(SQLiteEntity entity) throws DAOException {
		
		database = sqLiteDAO.getWritableDatabase();

		database.insert(entity.getTableName(), null, entity.getValues());

		database.close();
				
	}
	
	public void query(SQLiteEntity entity) throws DAOException{
		
		database = sqLiteDAO.getWritableDatabase();

		entity.setResult(database.rawQuery(entity.getQuery(),null));

		database.close();
		
	}
	

	
	
	

}
