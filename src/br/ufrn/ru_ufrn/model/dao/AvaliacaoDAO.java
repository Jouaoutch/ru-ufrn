package br.ufrn.ru_ufrn.model.dao;

import java.util.Date;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Avaliacao;
import br.ufrn.ru_ufrn.model.ResultadoAvaliacoes;
import br.ufrn.ru_ufrn.model.Usuario;

public interface AvaliacaoDAO {
	
	void avaliarRefeicao(Avaliacao avaliacao) throws DAOException;
	
	ResultadoAvaliacoes getResultadoAvaliacoes(Date data, String refeicao) throws DAOException;

	Avaliacao getUltimaAvaliacao(Usuario user, Date data) throws DAOException;
	
	void atualizarAvaliação(Avaliacao avaliacao) throws DAOException;
	

}
