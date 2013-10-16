package br.ufrn.ru_ufrn.model.dao;

import java.sql.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Cardapio;
import br.ufrn.ru_ufrn.model.Refeicao;
import br.ufrn.ru_ufrn.model.dao.CardapioDAO;

public class CardapioSQLiteDAO implements CardapioDAO {


	private Context context;
	private GenericSQLiteDAO gSqLiteDAO;
	
	public CardapioSQLiteDAO(Context context) {
		this.context = context;
	}


	@Override
	public Cardapio findById(Class<Cardapio> classe, Integer id)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cardapio> findAll(Class<Cardapio> classe) throws DAOException {
		String query = "SELECT * FROM Cardapio;";
		SQLiteEntity entity = new SQLiteEntity();
		entity.setQuery(query);
		gSqLiteDAO = new GenericSQLiteDAO(this.context);
		gSqLiteDAO.query(entity);
		
		if(entity.getResult() != null){
			if(entity.getResult().moveToFirst()){
				do{
					//Cursor = entity.getResult().g
				}
					while(entity.getResult().moveToNext());			}
		}
		
		return null;
	}

	@Override
	public Cardapio save(Cardapio cardapio) throws DAOException {
		Cardapio output = null;
		if(cardapio != null){
			if(cardapio.getId() == null){
				gSqLiteDAO = new GenericSQLiteDAO(this.context);
				ContentValues values = new ContentValues(1);
				values.put("data", cardapio.getData().toString());
				SQLiteEntity sqLiteEntity = new SQLiteEntity();
				sqLiteEntity.setTableName("Cardapio");
				sqLiteEntity.setValues(values);				
				
				gSqLiteDAO.save(sqLiteEntity);
				
				cardapio.setId(sqLiteEntity.getIdSaved());
				
				RefeicaoSQLiteDAO refeicaoSQLiteDAO = new RefeicaoSQLiteDAO(this.context);				
				
				refeicaoSQLiteDAO.save(cardapio.getCafeDaManha());
				refeicaoSQLiteDAO.save(cardapio.getAlmocoVegetariano());
				refeicaoSQLiteDAO.save(cardapio.getAlmocoCarnivoro());
				refeicaoSQLiteDAO.save(cardapio.getJantaVegetariana());
				refeicaoSQLiteDAO.save(cardapio.getJantaCarnivora());
				
				relate(cardapio,cardapio.getCafeDaManha());
				
				
			}
			else{
				this.update(cardapio);
			}
		}
		
		
		

		return output;
	}

	private void relate(Cardapio cardapio, Refeicao refeicao) throws DAOException {
		ContentValues vtemp = new ContentValues(2);
		vtemp.put("id_cardapio", cardapio.getId());
		vtemp.put("id_refeicao", refeicao.getId());
		SQLiteEntity etemp = new SQLiteEntity();
		etemp.setTableName("Cardapio_Refeicao");
		etemp.setValues(vtemp);
		gSqLiteDAO.save(etemp);
		
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
	

/*	private String carregarArquivoSQL() {
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
*/
	
	

}
