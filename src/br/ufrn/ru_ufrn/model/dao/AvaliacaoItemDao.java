package br.ufrn.ru_ufrn.model.dao;

import java.util.Date;
import java.util.List;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.AvaliacaoItem;

public interface AvaliacaoItemDao {
	
	void avaliarItem(AvaliacaoItem avItem) throws DAOException;
	
	void atualizarAvItem(AvaliacaoItem avItem) throws DAOException;
	
	List<AvaliacaoItem> getResultadoAvItens(Date data, String refeicao) throws DAOException;

}
