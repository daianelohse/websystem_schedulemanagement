
package controller.dao;

import javax.persistence.EntityManager;
import util.ConexaoUtil;

public class GenericDAO<T> {
    
    private ConexaoUtil conexaoUtil;

    public GenericDAO() {
        conexaoUtil = new ConexaoUtil();
    }
    
    public boolean insert(T obj){
        try{
            conexaoUtil.getEntityManager().getTransaction().begin();
            conexaoUtil.getEntityManager().persist(obj);
            conexaoUtil.getEntityManager().getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            //conexaoUtil.getEntityManager().getTransaction().rollback();
            return false;
        }
    }
    
    public boolean update(T obj){
        try{
            conexaoUtil.getEntityManager().getTransaction().begin();
            conexaoUtil.getEntityManager().merge(obj);
            conexaoUtil.getEntityManager().getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            conexaoUtil.getEntityManager().getTransaction().rollback();
            return false;
        }
    }
    
    public boolean remove(T obj){
        try{
            conexaoUtil.getEntityManager().getTransaction().begin();
            conexaoUtil.getEntityManager().remove(obj);
            conexaoUtil.getEntityManager().getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            conexaoUtil.getEntityManager().getTransaction().rollback();
            return false;
        }
    }
    
    public EntityManager getEm(){
        return conexaoUtil.getEntityManager();
    }
}
