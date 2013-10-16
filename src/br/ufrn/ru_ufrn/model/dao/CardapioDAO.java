package br.ufrn.ru_ufrn.model.dao;

import br.ufrn.ru_ufrn.model.Cardapio;

public interface CardapioDAO extends GenericDAO<Cardapio, Integer> {
		public Cardapio findByData(java.sql.Date data);
}
