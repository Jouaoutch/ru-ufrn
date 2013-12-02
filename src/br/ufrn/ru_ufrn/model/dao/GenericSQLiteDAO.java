package br.ufrn.ru_ufrn.model.dao;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GenericSQLiteDAO {

	protected SQLiteDatabase database;
	protected Context context;
	private final DatabaseHelper DBHelper;

	public GenericSQLiteDAO(Context context) {
		this.context = context;
		DBHelper = DatabaseHelper.getInstance(context);
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
			
		}

	}

	@SuppressLint("UseValueOf")
	public Integer findMaxID(String tableName) throws DAOException {

		Integer max = null;

		try {

			database = this.getReadableDatabase();

			Cursor cursor = database.rawQuery("SELECT MAX(id) FROM "
					+ tableName + " ;", null);

			if (cursor.moveToLast()) {
				max = new Integer(cursor.getInt(0));
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		} finally {
		}

		return max;
	}

	public void query(SQLiteEntity entity) throws DAOException {

		try {
			database = this.getReadableDatabase();

			entity.setResult(database.rawQuery(entity.getQuery(), entity.getArgs()));
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		} finally {
		}

	}

	public void delete(SQLiteEntity entity) throws DAOException {
		try {
			database = this.getWritableDatabase();
			long row = database.delete(entity.getTableName(),
					" id = " + entity.getIdSaved(), null);
			if (row == 0) {
				throw new DAOException("Erro ao deletar na tabela "
						+ entity.getTableName());
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		} finally {
		}

	}

	public synchronized SQLiteDatabase getWritableDatabase() {
		return DBHelper.getWritableDatabase();
	}

	public synchronized SQLiteDatabase getReadableDatabase() {
		return DBHelper.getReadableDatabase();
	}

}
