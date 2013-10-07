package br.ufrn.ru_ufrn.model.dao;

import java.sql.Date;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Avaliacao;
import br.ufrn.ru_ufrn.model.ResultadoAvaliacoes;

public interface AvaliacaoDAO {
	
	void avaliarRefeicao(Avaliacao avaliacao) throws DAOException;
	
	Avaliacao getUltimaAvaliacao() throws DAOException;
	
	ResultadoAvaliacoes getResultadoAvaliacoes(Date data) throws DAOException;
	

}
