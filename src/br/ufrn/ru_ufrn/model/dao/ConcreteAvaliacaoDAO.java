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

public class ConcreteAvaliacaoDAO extends SQLiteOpenHelper implements
		AvaliacaoDAO {

	private static final String name = "RU_UFRN";
	private static final int version = 1;
	private static final String createTable = "CREATE TABLE avaliacoes ("
			+ " idAvaliacao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "cardapioCumprido boolean NOT NULL, "
			+ "refeicao varchar(20), "
			+ "data date NOT NULL, "
			+ "avaliacao varchar(10) NOT NULL, "
			+ "idUsuario  Integer NOT NULL );";

	private SQLiteDatabase database;

	public ConcreteAvaliacaoDAO(Context context) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTable);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


	}

	@Override
	public void avaliarRefeicao(Avaliacao avaliacao) throws DAOException {
		ContentValues values = new ContentValues(5);

		values.put("cardapioCumprido", avaliacao.isCardapioCumprido());
		values.put("refeicao", avaliacao.getRefeicao());
		values.put("data", avaliacao.getDataFormatoAmericano());
		values.put("avaliacao", avaliacao.getNivelSatisfacao().toString());
		values.put("idUsuario", avaliacao.getIdUsuario());

		database = this.getWritableDatabase();

		database.insert("avaliacoes", null, values);

		database.close();

	}
		

	@Override
	public Avaliacao getUltimaAvaliacao(Usuario user, Date data) throws DAOException {

		Avaliacao av = new Avaliacao();
		
		database  = this.getReadableDatabase();
		
		
		//recupera todas as avaliações do usuário durante o dia e 
		Cursor cursor = database.rawQuery("Select * from avaliacoes where idUsuario = '"+user.getId()+
				"' and data = '"+data.getYear()+"-"+ data.getMonth()+"-"+data.getDay()+"' order by idavaliacao" +
						" asc", null);
		
		
		//move o cursor para a ultima avaliação recuperada
		if(cursor.moveToLast()){
			
			av.setIdAvaliacao(cursor.getLong(0));
			av.setCardapioCumprido(cursor.getInt(1) == 1);
			av.setRefeicao(cursor.getString(2));
			String dt[] = cursor.getString(3).split("-");
			av.setData(new Date(Integer.parseInt(dt[0]), Integer.parseInt(dt[1]), Integer.parseInt(dt[2])));
			av.setNivelSatisfacao(cursor.getString(4));
			av.setIdUsuario(cursor.getLong(5));
			
			
		}
		
		database.close();
		
		return av;
	}

	@Override
	public ResultadoAvaliacoes getResultadoAvaliacoes(Date data, String refeicao)
			throws DAOException {
		
		ResultadoAvaliacoes resultAv = new ResultadoAvaliacoes();
		
		database  = this.getReadableDatabase();
		
		
		Cursor cursor = database.rawQuery("select  COUNT(avaliacao) as numAvaliacoes, avaliacao " +
				" from avaliacoes group by avaliacao, refeicao having data = '"
				+data.getYear()+"-"+ data.getMonth()+"-"+data.getDay()+"' and refeicao = '"+refeicao+"'", null);
		
		while(cursor.moveToNext()){
			
			
			int numAv = cursor.getInt(0);
			String av = cursor.getString(1);
			
			if(av.equals(NivelSatisfacao.GOSTEI.toString())){
				resultAv.setGostaram(numAv);
			}
			else if(av.equals(NivelSatisfacao.DESGOSTEI.toString())){
				resultAv.setDesgostaram(numAv);
			}
			else if(av.equals(NivelSatisfacao.INDIFERENTE.toString())){
				resultAv.setIndiferente(numAv);
			}
			
		}
		
		resultAv.setData(data);
		resultAv.setRefeicao(refeicao);
		resultAv.setTotaVotos(resultAv.getDesgostaram()+resultAv.getGostaram()+resultAv.getIndiferente());
		
		database.close();
		return resultAv;
		
	}

	@Override
	public void atualizarAvaliação(Avaliacao avaliacao) throws DAOException {
		
		ContentValues values = new ContentValues(5);

		values.put("cardapioCumprido", avaliacao.isCardapioCumprido());
		values.put("avaliacao", avaliacao.getNivelSatisfacao().toString());

		database = this.getWritableDatabase();

		String args[] = {String.valueOf(avaliacao.getIdAvaliacao()), String.valueOf(avaliacao.getIdUsuario())};
		database.update("avaliacoes", values, "IdAvaliacao = ? and idUsuario = ?", args);

		database.close();
		
	}

}
