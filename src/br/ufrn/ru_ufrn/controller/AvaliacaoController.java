package br.ufrn.ru_ufrn.controller;

import java.util.Date;

import android.content.Context;
import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.exceptions.ValorInvalidoException;
import br.ufrn.ru_ufrn.exceptions.ValorNuloException;
import br.ufrn.ru_ufrn.model.Avaliacao;
import br.ufrn.ru_ufrn.model.Usuario;
import br.ufrn.ru_ufrn.model.dao.AvaliacaoDAO;
import br.ufrn.ru_ufrn.model.dao.ConcreteAvaliacaoDAO;

public class AvaliacaoController {

	private final AvaliacaoDAO avDao;

	public AvaliacaoController(Context context) {
		avDao = new ConcreteAvaliacaoDAO(context);
	}

	private boolean validarDados(Avaliacao avaliacao)
			throws ValorNuloException, ValorInvalidoException {
		
		if (avaliacao.getData() == null) {
			throw new ValorNuloException("Data is null");
		}

		if (avaliacao.getIdUsuario() < 0) {
			throw new ValorInvalidoException("IdUsuario less than 0");
		}

		if (avaliacao.getIdAvaliacao() == null) {
			throw new ValorNuloException("NivelStisfacao is null");
		}

		if (avaliacao.getIdRefeicao() == null) {
			throw new ValorNuloException("Refeicao is null");
		}
		
		/*if(! (avaliacao.getIdRefeicao().equals(Avaliacao.ALMOCO_CARNIVORO) || avaliacao.getRefeicao().equals(Avaliacao.ALMOCO_VEGETARIANO) 
				|| avaliacao.getRefeicao().equals(Avaliacao.CAFE) || avaliacao.getRefeicao().equals(Avaliacao.JANTAR_CARNIVORO)
				|| avaliacao.getRefeicao().equals(Avaliacao.JANTAR_VEGETARIANO)) ){
			
			throw new ValorInvalidoException("refeição invalid: "+avaliacao.getRefeicao());
		}*/

		return true;
	}

	public void avaliarRefeicao(Avaliacao avaliacao) throws ValorNuloException, ValorInvalidoException, DAOException{
		if( validarDados(avaliacao) ){
			avDao.avaliarRefeicao(avaliacao);
		}
		
	}
	
	private boolean validarUsuario(Usuario usuario) throws ValorInvalidoException, ValorNuloException{
		
		if(usuario.getId() < 0){
			throw new ValorInvalidoException("IdUsuario invalid: "+usuario.getId());
		}
		
		if(usuario.getLogin() == null){
			throw new ValorNuloException("login is null");
		}
		
		return true;
	}
	
	public Avaliacao getUltimaAvaliacao(Usuario user, Date data) throws ValorInvalidoException, ValorNuloException, DAOException{
		Avaliacao av = null;
		if(data != null && validarUsuario(user)){
			av = avDao.getUltimaAvaliacao(user, data);
		}
		
		return av;
	}
	
	public void atualizarAvaliacao(Avaliacao avaliacao) throws ValorNuloException, ValorInvalidoException, DAOException{
		if(validarDados(avaliacao) && avaliacao.getIdAvaliacao() != null){
			avDao.atualizarAvaliação(avaliacao);
		}
	}

}
