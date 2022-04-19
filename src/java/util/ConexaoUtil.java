package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoUtil {

    private EntityManagerFactory emf;
    private EntityManager em;
    private static final String PU = "SGHPU";
    private boolean conectado = false;

    public ConexaoUtil() {
    }

    public boolean conectar() {
        if (!conectado) {
            try {
                emf = Persistence.createEntityManagerFactory(PU);
                em = emf.createEntityManager();
                return conectado = true;
            } catch (Exception e) {
                e.printStackTrace();
                return conectado;
            }
        }
        return conectado;
    }
    
    public boolean desconectar(){
        if(conectado){
            try {
                em.close();
                emf.close();
                conectado = false;
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
    
    public EntityManager getEntityManager(){
        conectar();
        return this.em;
    }
    
    public static void main(String[] args) {
        ConexaoUtil c = new ConexaoUtil();
        c.conectar();
    }
}
