package br.ufrn.ru_ufrn.model.dao;

public class MemoryDAOFactory extends DAOFactory {
	
	private MemoryDAO instantiateDAO(Class daoClass) {
        try {
        	MemoryDAO dao = (MemoryDAO) daoClass.newInstance();            
            return dao;
        } catch (Exception ex) {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
        }
    }
	
	@Override
	public CardapioDAO getCardapioDAO() {		
		return (CardapioDAO) instantiateDAO(CardapioMemoryDAO.class);
	}

}
