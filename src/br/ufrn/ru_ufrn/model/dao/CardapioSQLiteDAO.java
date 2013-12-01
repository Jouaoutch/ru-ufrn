package br.ufrn.ru_ufrn.model.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Alimento;
import br.ufrn.ru_ufrn.model.Cardapio;
import br.ufrn.ru_ufrn.model.Refeicao;
import br.ufrn.ru_ufrn.model.dao.CardapioDAO;

@SuppressLint("UseValueOf")
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

		if (entity.getResult() != null) {
			if (entity.getResult().moveToFirst()) {
				do {
					// Cursor = entity.getResult().g
				} while (entity.getResult().moveToNext());
			}
		}

		return null;
	}

	@Override
	public Cardapio save(Cardapio cardapio) throws DAOException {
		Cardapio output = null;
		if (cardapio != null) {
			if (cardapio.getId() == null) {
				gSqLiteDAO = new GenericSQLiteDAO(this.context);
				String date = cardapio.getData();
				Cardapio temp = findByData(date);
				if (temp != null) {
					delete(temp);
				}

				ContentValues values = new ContentValues(1);
				values.put("data", cardapio.getData().toString());
				SQLiteEntity sqLiteEntity = new SQLiteEntity();
				sqLiteEntity.setTableName("Cardapio");
				sqLiteEntity.setValues(values);

				gSqLiteDAO.save(sqLiteEntity);

				cardapio.setId(sqLiteEntity.getIdSaved());

				RefeicaoSQLiteDAO refeicaoSQLiteDAO = new RefeicaoSQLiteDAO(
						this.context);

				refeicaoSQLiteDAO.save(cardapio.getCafeDaManha());
				refeicaoSQLiteDAO.save(cardapio.getAlmocoVegetariano());
				refeicaoSQLiteDAO.save(cardapio.getAlmocoCarnivoro());
				refeicaoSQLiteDAO.save(cardapio.getJantaVegetariana());
				refeicaoSQLiteDAO.save(cardapio.getJantaCarnivora());

				relate(cardapio, cardapio.getCafeDaManha());

			} else {
				this.update(cardapio);
			}
		}

		return output;
	}

	private void relate(Cardapio cardapio, Refeicao refeicao)
			throws DAOException {
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

		if (gSqLiteDAO == null) {
			gSqLiteDAO = new GenericSQLiteDAO(this.context);
		}
		SQLiteEntity sqle = new SQLiteEntity();
		sqle.setIdSaved(entity.getId());
		sqle.setTableName("Cardapio");
		gSqLiteDAO.delete(sqle);

	}

	@Override
	public Cardapio findByData(String data) throws DAOException {
		Cardapio card = null;

		// recuperando o cardapio
		String sqlQuery = "SELECT * FROM Cardapio WHERE data = '" + data
				+ "' ;";
		SQLiteEntity entity = new SQLiteEntity();
		entity.setQuery(sqlQuery);

		if (gSqLiteDAO == null) {
			gSqLiteDAO = new GenericSQLiteDAO(this.context);
		}
		gSqLiteDAO.query(entity);

		Cursor cursor = entity.getResult();

		if (cursor != null) {

			if (cursor.moveToFirst()) {
				do {
					int id_card = Integer.parseInt(cursor.getString(cursor
							.getColumnIndex("id")));
					Integer idCard = new Integer(id_card);
					card.setId(idCard);
					card.setData(cursor.getString(cursor.getColumnIndex("data")));
				} while (cursor.moveToNext());
			}
			cursor.close();

			// recuperando as refeicoes do cardapio
			String sqlQuery2 = "SELECT r.id, r.nome, r.tipo "
					+ "FROM Refeicao r INNER JOIN Cardapio_Refeicao cr "
					+ "ON r.id = cr.id_refeicao " + "INNER JOIN Cardapio c "
					+ "ON cr.id_cardapio = c.id AND c.id = " + card.getId()
					+ " ;";
			entity.setQuery(sqlQuery2);
			gSqLiteDAO.query(entity);

			Cursor cursor2 = entity.getResult();

			if (cursor2 != null) {// possivelmente existem refeicoes
				if (cursor2.moveToFirst()) {
					do {
						int id_ref = Integer.parseInt(cursor2.getString(cursor2
								.getColumnIndex("id")));
						Integer idRef = new Integer(id_ref);
						Refeicao ref = new Refeicao();
						ref.setId(idRef);
						ref.setNome(cursor2.getString(cursor2
								.getColumnIndex("nome")));
						ref.setTipo(Integer.parseInt(cursor2.getString(cursor2
								.getColumnIndex("tipo"))));

						switch (ref.getTipo()) {
						case Cardapio.CAFE_DA_MANHA:
							card.setCafeDaManha(ref);
							break;
						case Cardapio.ALMOCO_CARNIVORO:
							card.setAlmocoCarnivoro(ref);
							break;
						case Cardapio.ALMOCO_VEGETARIANO:
							card.setAlmocoVegetariano(ref);
							break;
						case Cardapio.JANTA_CARNIVORA:
							card.setJantaCarnivora(ref);
							break;
						case Cardapio.JANTA_VEGETARIANA:
							card.setJantaVegetariana(ref);
							break;
						default:
							break;
						}

						// recuperando os alimentos da refeicao

						String sqlQuery3 = "SELECT a.id, a.nome, a.descricao, a.imagem "
								+ "FROM Alimento a INNER JOIN Refeicao_Alimento ra "
								+ "ON a.id = ra.id_alimento "
								+ "INNER JOIN Refeicao r "
								+ "ON ra.id_refeicao = r.id AND r.id = "
								+ ref.getId() + " ;";

						entity.setQuery(sqlQuery3);
						gSqLiteDAO.query(entity);

						Cursor cursor3 = entity.getResult();

						if (cursor3 != null) {// provavelmente existem alimentos
							if (cursor3.moveToFirst()) {
								do {
									int id_ali = Integer.parseInt(cursor3
											.getString(cursor3
													.getColumnIndex("id")));
									Integer idAli = new Integer(id_ali);
									Alimento ali = new Alimento();
									ali.setId(idAli);
									ali.setNome(cursor3.getString(cursor3
											.getColumnIndex("nome")));
									ali.setDescricao(cursor3.getString(cursor3
											.getColumnIndex("descricao")));
									ali.setImagem(cursor3.getString(cursor3
											.getColumnIndex("imagem")));

									ref.adicionarAlimento(ali);
								} while (cursor3.moveToNext());
							}
						}

					} while (cursor2.moveToNext());
				}
				cursor2.close();

			}
			 
			
			
		}

		return card;
	}

	/*
	 * private String carregarArquivoSQL() { InputStream is =
	 * this.context.getResources().openRawResource(
	 * R.raw.create_table_cardapio); InputStreamReader isr = new
	 * InputStreamReader(is); BufferedReader br = new BufferedReader(isr);
	 * StringBuilder sb = new StringBuilder(); String line = null; try { line =
	 * br.readLine();
	 * 
	 * while (line != null) { sb.append(line); line = br.readLine(); } } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } return sb.toString();
	 * 
	 * }
	 */

}
