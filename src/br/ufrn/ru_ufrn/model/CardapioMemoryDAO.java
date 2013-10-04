package br.ufrn.ru_ufrn.model;

import java.util.Date;
import java.util.Iterator;

import br.ufrn.ru_ufrn.model.dao.MemoryDAO;

public class CardapioMemoryDAO extends MemoryDAO<Cardapio, java.util.Date> implements
		CardapioDAO{

	@Override
	public Cardapio findByData(Date data) {
		Cardapio result = null;
		for (Iterator<Cardapio> iterator = getDb().iterator(); iterator.hasNext();) {
			Cardapio c = iterator.next();
			if(c.getData().equals(data)){
				result = c;
			}			
		}
		return result;
	}
	
}
