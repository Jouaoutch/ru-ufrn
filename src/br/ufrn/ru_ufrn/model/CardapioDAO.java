package br.ufrn.ru_ufrn.model;

import br.ufrn.ru_ufrn.model.dao.GenericDAO;

public interface CardapioDAO extends GenericDAO<Cardapio, java.util.Date> {
		public Cardapio findByData(java.util.Date data);
}
