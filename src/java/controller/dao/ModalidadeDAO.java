package controller.dao;

import java.util.List;
import model.entidade.Curso;
import model.entidade.Modalidade;

public class ModalidadeDAO {

    private GenericDAO genericDAO;

    public ModalidadeDAO() {
        genericDAO = new GenericDAO<Modalidade>();
    }

    public boolean salvar(Modalidade modalidade) {
        if (modalidade.getId() == null) {
            return genericDAO.insert(modalidade);
        } else {
            return genericDAO.update(modalidade);
        }
    }

    public boolean remover(Modalidade modalidade) {
        return genericDAO.remove(modalidade);
    }

    public List<Modalidade> listar() {
        return genericDAO.getEm().createQuery("select m FROM modalidade m").getResultList();

    }

    public Modalidade findById(int id) {
        return genericDAO.getEm().find(Modalidade.class, id);
    }
}
