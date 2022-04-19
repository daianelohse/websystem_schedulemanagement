
package controller.dao;

import java.util.List;
import javax.persistence.Query;
import model.entidade.Ambiente;
import model.entidade.TipoAmbiente;

public class AmbienteDAO {
    
    private GenericDAO genericDAO;
    public AmbienteDAO() {
        genericDAO = new GenericDAO<Ambiente>();
    }
    
    public boolean salvar(Ambiente ambiente){
        if(ambiente.getId() == null){
            return genericDAO.insert(ambiente);
        }else{
            return genericDAO.update(ambiente);
        }
    }
    
    public boolean remover(Ambiente ambiente){
        return genericDAO.remove(ambiente);
    }
    
    public List<Ambiente> listar(){
        return genericDAO.getEm().createQuery("SELECT a FROM ambiente a").getResultList();
    }

    public List<Ambiente> findByTipoAmbiente(TipoAmbiente tipoAmbiente) {
        return genericDAO.getEm().createQuery("SELECT a FROM ambiente a WHERE a.tipoAmbiente = :tipoAmbiente")
                  .setParameter("tipoAmbiente", tipoAmbiente)
                  .getResultList();
    }

    public Ambiente findById(Integer id) {
        return genericDAO.getEm().find(Ambiente.class, id);
    }
}
