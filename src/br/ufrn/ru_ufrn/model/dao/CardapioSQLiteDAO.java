package br.ufrn.ru_ufrn.model.dao;

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
				relate(cardapio, cardapio.getAlmocoVegetariano());
				relate(cardapio, cardapio.getAlmocoCarnivoro());
				relate(cardapio, cardapio.getJantaCarnivora());
				relate(cardapio, cardapio.getJantaVegetariana());

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
	public synchronized Cardapio findByData(String data) throws DAOException {
		Cardapio card = null;

		if (gSqLiteDAO == null) {
			gSqLiteDAO = new GenericSQLiteDAO(this.context);
		}

		// recuperando o cardapio
		String sqlQuerySelectCardapio = "SELECT * FROM Cardapio WHERE data = ? ";
		String[] sqlArgsCardapio = { data };
		SQLiteEntity entityCard = new SQLiteEntity();
		entityCard.setQuery(sqlQuerySelectCardapio);
		entityCard.setArgs(sqlArgsCardapio);

		gSqLiteDAO.query(entityCard);

		Cursor cursorCard = entityCard.getResult();

		//provavelmente existe o cardapio

		if (cursorCard.moveToFirst()) {
			do {
				int id_card = getIdFromCursor(cursorCard);
				Integer idCard = new Integer(id_card);
				card = new Cardapio();
				card.setId(idCard);
				card.setData(cursorCard.getString(cursorCard
						.getColumnIndex("data")));
			} while (cursorCard.moveToNext());
		}
		// recuperando as refeicoes do cardapio
		if (card != null) {// se existia o cardapio
			SQLiteEntity entityRef = new SQLiteEntity();
			String sqlQuerySelectRefeicoesDoCardapio = "SELECT r.id, r.nome, r.tipo "
					+ "FROM Refeicao r INNER JOIN Cardapio_Refeicao cr "
					+ "ON r.id = cr.id_refeicao "
					+ "INNER JOIN Cardapio c "
					+ "ON cr.id_cardapio = c.id AND c.id = " + card.getId();
			entityRef.setQuery(sqlQuerySelectRefeicoesDoCardapio);
			entityRef.setArgs(null);
			gSqLiteDAO.query(entityRef);

			Cursor cursorRef = entityRef.getResult();

			// possivelmente existem refeicoes
			if (cursorRef.moveToFirst()) {
				do {
					Refeicao ref = new Refeicao();
					int id_ref = getIdFromCursor(cursorRef);
					Integer idRef = new Integer(id_ref);

					ref.setId(idRef);
					ref.setNome(cursorRef.getString(cursorRef
							.getColumnIndex("nome")));
					ref.setTipo(Integer.parseInt(cursorRef.getString(cursorRef
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

					SQLiteEntity entityAli = new SQLiteEntity();

					String sqlQuerySelectAlimentosDaRefeicao = "SELECT a.id, a.nome, a.descricao, a.imagem "
							+ "FROM Alimento a INNER JOIN Refeicao_Alimento ra "
							+ "ON a.id = ra.id_alimento "
							+ "INNER JOIN Refeicao r "
							+ "ON ra.id_refeicao = r.id AND r.id = "
							+ ref.getId();

					entityAli.setQuery(sqlQuerySelectAlimentosDaRefeicao);
					entityAli.setArgs(null);
					gSqLiteDAO.query(entityAli);

					Cursor cursorAli = entityAli.getResult();

					// provavelmente existem alimentos
					if (cursorAli.moveToFirst()) {
						do {
							int id_ali = getIdFromCursor(cursorAli);
							Integer idAli = new Integer(id_ali);
							Alimento ali = new Alimento();
							ali.setId(idAli);
							ali.setNome(cursorAli.getString(cursorAli
									.getColumnIndex("nome")));
							ali.setDescricao(cursorAli.getString(cursorAli
									.getColumnIndex("descricao")));
							ali.setImagem(cursorAli.getString(cursorAli
									.getColumnIndex("imagem")));

							ref.adicionarAlimento(ali);
						} while (cursorAli.moveToNext());
					}
				} while (cursorRef.moveToNext());
			}
		}

		// }

		return card;
	}

	private int getIdFromCursor(Cursor cursorCard) {
		return Integer.parseInt(cursorCard.getString(cursorCard
				.getColumnIndex("id")));
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
