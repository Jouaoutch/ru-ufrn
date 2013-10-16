package br.ufrn.ru_ufrn.model.dao;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Avaliacao;
import br.ufrn.ru_ufrn.model.NivelSatisfacao;
import br.ufrn.ru_ufrn.model.ResultadoAvaliacoes;
import br.ufrn.ru_ufrn.model.Usuario;

public class ConcreteAvaliacaoDAO extends GenericSQLiteDAO2 implements AvaliacaoDAO{

	
	public ConcreteAvaliacaoDAO(Context context) {
		super(context);
	}

	
	public void avaliarRefeicao(Avaliacao avaliacao) throws DAOException {
		ContentValues values = new ContentValues(5);

		values.put("cardapioCumprido", avaliacao.isCardapioCumprido());
		values.put("id_refeicao", avaliacao.getIdRefeicao());
		values.put("data", avaliacao.getDataFormatoAmericano());
		values.put("idAvaliacao", avaliacao.getIdAvaliacao());
		values.put("idUsuario", avaliacao.getIdUsuario());

		database = sqLiteDAO.getDatabaseWrite();

		database.insert("Avaliacao_Cardapio", null, values);

		database.close();

	}


	public Avaliacao getUltimaAvaliacao(Usuario user, Date data)
			throws DAOException {

		Avaliacao av = null;

		database = sqLiteDAO.getReadableDatabase();

		try {
			// recupera todas as avaliações do usuário durante o dia e
			Cursor cursor = database.rawQuery(
					"Select * from Avaliacao_Cardapio where idUsuario = '"
							+ user.getId() + "' and data = '" + data.getYear()
							+ "-" + data.getMonth() + "-" + data.getDay()
							+ "' order by id" + " asc", null);

			// move o cursor para a ultima avaliação recuperada
			if (cursor.moveToLast()) {
				av = new Avaliacao();
				av.setId(cursor.getInt(0));
				av.setCardapioCumprido(cursor.getInt(1) == 1);
				av.setIdRefeicao(cursor.getInt(2));
				String dt[] = cursor.getString(3).split("-");
				av.setData(new Date(Integer.parseInt(dt[0]), Integer
						.parseInt(dt[1]), Integer.parseInt(dt[2])));
				av.setIdAvaliacao(cursor.getInt(4));
				av.setIdUsuario(cursor.getLong(5));

			}

			database.close();
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}

		return av;
	}

	
	public ResultadoAvaliacoes getResultadoAvaliacoes(Date data, int idRefeicao)
			throws DAOException {

		ResultadoAvaliacoes resultAv = new ResultadoAvaliacoes();

		database = sqLiteDAO.getReadableDatabase();

		Cursor cursor = database
				.rawQuery(
						"select  COUNT(avaliacao) as numAvaliacoes, avaliacao "
								+ " from Avaliacao_Cardapio group by avaliacao, refeicao having data = '"
								+ data.getYear() + "-" + data.getMonth() + "-"
								+ data.getDay() + "' and idRefeicao = '"
								+ idRefeicao + "'", null);

		while (cursor.moveToNext()) {

			int numAv = cursor.getInt(0);
			String av = cursor.getString(1);

			if (av.equals(NivelSatisfacao.GOSTEI.toString())) {
				resultAv.setGostaram(numAv);
			} else if (av.equals(NivelSatisfacao.DESGOSTEI.toString())) {
				resultAv.setDesgostaram(numAv);
			} else if (av.equals(NivelSatisfacao.INDIFERENTE.toString())) {
				resultAv.setIndiferente(numAv);
			}

		}

		resultAv.setData(data);
		resultAv.setiDrefeicao(idRefeicao);
		resultAv.setTotaVotos(resultAv.getDesgostaram()
				+ resultAv.getGostaram() + resultAv.getIndiferente());

		database.close();
		return resultAv;

	}


	public void atualizarAvaliação(Avaliacao avaliacao) throws DAOException {

		ContentValues values = new ContentValues(2);

		values.put("cardapioCumprido", avaliacao.isCardapioCumprido());
		values.put("idAvaliacao", avaliacao.getIdAvaliacao());

		database = sqLiteDAO.getReadableDatabase();

		String args[] = { String.valueOf(avaliacao.getId()),
				String.valueOf(avaliacao.getIdUsuario()) };
		
		database.update("Avaliacao_Cardapio", values,
				"id = ? and idUsuario = ?", args);

		database.close();

	}

}
