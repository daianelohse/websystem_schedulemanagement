
package controller.dao;

import java.util.List;
import model.entidade.Curso;
import model.entidade.Modalidade;
import model.entidade.ReservaAula;

public class ReservaAulaDAO {
    
    private GenericDAO genericDAO;
    public ReservaAulaDAO() {
        genericDAO = new GenericDAO<ReservaAula>();
    }
    
    public boolean salvar(ReservaAula reserva){
        if(reserva.getId() == null){
            return genericDAO.insert(reserva);
        }else{
            return genericDAO.update(reserva);
        }
    }
    
    public ReservaAula findById(Integer id){
        return genericDAO.getEm().find(ReservaAula.class, id);
    }
}
