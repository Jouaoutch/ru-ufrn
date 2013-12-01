package br.ufrn.ru_ufrn.model.dao;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Cardapio;

public interface CardapioDAO extends GenericDAO<Cardapio, Integer> {
		public Cardapio findByData(String data) throws DAOException;
}
