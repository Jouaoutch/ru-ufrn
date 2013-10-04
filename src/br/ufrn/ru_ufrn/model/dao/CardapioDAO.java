package br.ufrn.ru_ufrn.model.dao;

import br.ufrn.ru_ufrn.model.Cardapio;

public interface CardapioDAO extends GenericDAO<Cardapio, java.util.Date> {
		public Cardapio findByData(java.util.Date data);
}
