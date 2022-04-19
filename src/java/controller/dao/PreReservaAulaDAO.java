package controller.dao;

import java.time.LocalDate;
import java.util.List;
import model.entidade.PreReservaAula;

/**
 *
 * @author Daiane
 */
public class PreReservaAulaDAO {

    private GenericDAO genericDAO;

    public PreReservaAulaDAO() {
        genericDAO = new GenericDAO<PreReservaAula>();
    }
    
     public boolean salvar(PreReservaAula p) {
        if (p.getId() == null) {
            return genericDAO.insert(p);
        } else {
            return genericDAO.update(p);
        }
    }

    public List<PreReservaAula> listar(LocalDate dataInicial, LocalDate dataFinal) {
        return genericDAO.getEm().createQuery("SELECT a FROM pre_reserva_aula a WHERE a.dataReserva >= :dataInicial and a.dataReserva <= :dataFinal")
                .setParameter("dataInicial", dataInicial)
                .setParameter("dataFinal", dataFinal)
                .getResultList();
    }

}
