package br.ufrn.ru_ufrn.model.dao;

import java.sql.Date;
import java.util.Iterator;

import br.ufrn.ru_ufrn.model.Cardapio;

public class CardapioMemoryDAO extends MemoryDAO<Cardapio, Integer> implements
		CardapioDAO{

	@Override
	public Cardapio findByData(String data) {
		Cardapio result = null;
		/*for (Iterator<Cardapio> iterator = getDb().iterator(); iterator.hasNext();) {
			Cardapio c = iterator.next();
			//TODO melhorar o jeito comparar as datas
			if(c.getData().getDay() == data.getDay() &&
					c.getData().getMonth() == data.getMonth() &&
							c.getData().getYear() == data.getYear()
					){
				
				result = c;
			}			
		}*/
		return result;
	}
	
}
