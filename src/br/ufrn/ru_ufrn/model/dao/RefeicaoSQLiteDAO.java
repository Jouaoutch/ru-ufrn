package br.ufrn.ru_ufrn.model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Alimento;
import br.ufrn.ru_ufrn.model.Cardapio;
import br.ufrn.ru_ufrn.model.Refeicao;

public class RefeicaoSQLiteDAO implements RefeicaoDAO{

	private Context context;
	private GenericSQLiteDAO gSqLiteDAO;
	
	public RefeicaoSQLiteDAO(Context context) {
		this.context = context;
	}
	
	
	@Override
	public Refeicao findById(Class<Refeicao> classe, Integer id)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Refeicao> findAll(Class<Refeicao> classe) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Refeicao save(Refeicao refeicao) throws DAOException {
		Refeicao output = null;
		if(refeicao != null){
			if(refeicao.getId() == null){
				gSqLiteDAO = new GenericSQLiteDAO(this.context);
				ContentValues values = new ContentValues(2);
				values.put("nome", refeicao.getNome());
				values.put("tipo", refeicao.getTipo());				
				SQLiteEntity sqLiteEntity = new SQLiteEntity();
				sqLiteEntity.setTableName("Refeicao");
				sqLiteEntity.setValues(values);				
				
				gSqLiteDAO.save(sqLiteEntity);
				
				refeicao.setId(sqLiteEntity.getIdSaved());
				
				AlimentoSQLiteDAO alimentoSQLiteDAO = new AlimentoSQLiteDAO(this.context);
				
				//Salvando os relacionamentos
				for (int i = 0; i < refeicao.getItens().size(); i++) {
					alimentoSQLiteDAO.save(refeicao.getItens().get(i));
					
					ContentValues vtemp = new ContentValues(2);
					vtemp.put("id_refeicao", refeicao.getId());
					vtemp.put("id_alimento", refeicao.getItens().get(i).getId());
					SQLiteEntity etemp = new SQLiteEntity();
					etemp.setTableName("Refeicao_Alimento");
					etemp.setValues(vtemp);
					gSqLiteDAO.save(etemp);
					
				}
			}
			else{
				this.update(refeicao);
			}
		}
		
		
		

		return output;
	}

	@Override
	public Refeicao update(Refeicao entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Refeicao entity) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
