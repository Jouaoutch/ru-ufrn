package br.ufrn.ru_ufrn.controller;

import java.util.List;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.exceptions.ValorInvalidoException;
import br.ufrn.ru_ufrn.exceptions.ValorNuloException;
import br.ufrn.ru_ufrn.model.Comentario;
import br.ufrn.ru_ufrn.model.dao.ComentarioSQLiteDAO;
import android.content.Context;

public class ComentarioController {
	
	private static ComentarioSQLiteDAO comDao;
	
	public ComentarioController(Context context){
		comDao = new ComentarioSQLiteDAO(context);
	}
	
	
	private boolean validarComentario(Comentario comentario) throws ValorInvalidoException, ValorNuloException{
		if(comentario == null){
			throw new ValorInvalidoException("Comentario is null ");
		}
		
		
		if(comentario.getComentario() == null){
			throw new ValorNuloException("Comenatrio is null");
		}
		
		
		return true;
	}
	
	public void salvarComentario(Comentario comentario) throws DAOException, ValorInvalidoException, ValorNuloException{
		if(validarComentario(comentario)){
			comDao.salvarComentario(comentario);
		}
	}
	
	public List<Comentario> getcomentarios() throws DAOException{
		return comDao.getCometarios();
	}

}
