package br.ufrn.ru_ufrn.model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Alimento;
import br.ufrn.ru_ufrn.model.Refeicao;

public class AlimentoSQLiteDAO implements AlimentoDAO {

	private Context context;
	private GenericSQLiteDAO gSqLiteDAO;
	
	public AlimentoSQLiteDAO(Context context) {
		this.context = context;
	}
	
	
	@Override
	public Alimento findById(Class<Alimento> classe, Integer id)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alimento> findAll(Class<Alimento> classe) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alimento save(Alimento alimento) throws DAOException {
		Alimento output = null;
		if(alimento != null){
			if(alimento.getId() == null){
				gSqLiteDAO = new GenericSQLiteDAO(this.context);
				ContentValues values = new ContentValues(3);
				values.put("nome", alimento.getNome());
				values.put("descricao", alimento.getDescricao());
				values.put("imagem", alimento.getImagem());				
				SQLiteEntity sqLiteEntity = new SQLiteEntity();
				sqLiteEntity.setTableName("Refeicao");
				sqLiteEntity.setValues(values);				
				
				gSqLiteDAO.save(sqLiteEntity);
				
				alimento.setId(sqLiteEntity.getIdSaved());
				
				output = alimento;
				
			}
			else{
				this.update(alimento);
			}
		}
		
		
		

		return output;
	}

	@Override
	public Alimento update(Alimento entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Alimento entity) throws DAOException {
		// TODO Auto-generated method stub

	}

}
