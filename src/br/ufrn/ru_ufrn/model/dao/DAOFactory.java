package br.ufrn.ru_ufrn.model.dao;

public abstract class DAOFactory {


    public static DAOFactory instance(Class factory) {
        try {
            return (DAOFactory)factory.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
        }
    }
 
    // Add your DAO interfaces here
    
    public abstract CardapioDAO getCardapioDAO();
}
