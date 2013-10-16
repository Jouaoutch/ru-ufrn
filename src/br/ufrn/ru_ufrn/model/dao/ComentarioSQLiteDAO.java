package br.ufrn.ru_ufrn.model.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Comentario;

public class ComentarioSQLiteDAO  {
	
	private GenericSQLiteDAO sqLiteDAO;
	private SQLiteDatabase database;
	

	public ComentarioSQLiteDAO(Context context) {
		sqLiteDAO = new GenericSQLiteDAO(context);
	}

	public void salvarComentario(Comentario comentario) throws DAOException {
		ContentValues values = new ContentValues(3);

		values.put("id_usuario", comentario.getIdUsuario());
		values.put("comentario", comentario.getComentario());
		values.put("image", comentario.getImagem());

		database = sqLiteDAO.getWritableDatabase();

		database.insert("Comentario", null, values);

		database.close();

	}

	public List<Comentario> getCometarios() throws DAOException {

		List<Comentario> comentarios = new ArrayList<Comentario>();

		database = sqLiteDAO.getReadableDatabase();

		try {

			Cursor cursor = database
					.rawQuery(
							"select c.*, u.login from Comentario As c Inner Join Usuario "
									+ "As u using(id_usuario) order by id desc limit 5;",
							null);

			Comentario comentario;
			if (cursor.moveToLast()) {
				comentario = new Comentario();

				comentario.setIdComentario(cursor.getInt(1));
				comentario.setComentario(cursor.getString(2));
				comentario.setImagem(cursor.getInt(3));
				comentario.setUsuario(cursor.getString(4));

				comentarios.add(comentario);

				while (cursor.moveToPrevious()) {
					comentario = new Comentario();

					comentario.setIdComentario(cursor.getInt(1));
					comentario.setComentario(cursor.getString(2));
					comentario.setImagem(cursor.getInt(3));
					comentario.setUsuario(cursor.getString(4));

					comentarios.add(comentario);
				}
			}

			database.close();
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}

		return comentarios;

	}

}
