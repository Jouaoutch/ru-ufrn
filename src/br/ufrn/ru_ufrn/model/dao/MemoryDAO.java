package br.ufrn.ru_ufrn.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ufrn.ru_ufrn.exceptions.DAOException;

public abstract class MemoryDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

	private List<T> db;
	
	public MemoryDAO() {
		setDb(new ArrayList<T>());
	}
	
	@Override
	public T findById(Class<T> classe, ID id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(Class<T> classe) throws DAOException {
	    List<T> result = new ArrayList<T>(getDb());
		return result;
	}

	@Override
	public T save(T entity) throws DAOException {
		getDb().add(entity);
		return null;
	}

	@Override
	public T update(T entity) throws DAOException {
		int updtIndex = exist(entity);		
		
		if(updtIndex != -1){
			getDb().add(updtIndex, entity);
		}
		return null;
	}



	@Override
	public void delete(T entity) throws DAOException {
		int dltIndex = exist(entity);
		if(dltIndex != -1){
			getDb().remove(dltIndex);
		}
		
	}

	public List<T> getDb() {
		return db;
	}

	public void setDb(List<T> db) {
		this.db = db;
	}
	
	private int exist(T entity) {
		int returnIndex = -1;
		int index = 0;		
		for (Iterator<T> iterator = getDb().iterator(); iterator.hasNext();) {
			T type = iterator.next();
			if (type == entity){
				returnIndex = index;
			}
			index++;			
		}
		return returnIndex;
	}

}
