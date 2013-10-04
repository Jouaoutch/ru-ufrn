package br.ufrn.ru_ufrn.model.dao;

import java.io.Serializable;
import java.util.List;

import br.ufrn.ru_ufrn.exceptions.DAOException;

public interface GenericDAO<T, ID extends Serializable> {
	
	T findById(Class<T> classe, ID id) throws DAOException;
	 
    List<T> findAll(Class<T> classe)throws DAOException;
 
    T save(T entity)throws DAOException;
 
    T update(T entity)throws DAOException;
        
    void delete(T entity)throws DAOException;
}
