package controller.dao;

import java.util.List;
import javax.persistence.Query;
import model.entidade.Curso;
import model.entidade.TipoAmbiente;

public class TipoAmbienteDAO {

    private GenericDAO genericDAO;

    public TipoAmbienteDAO() {
        genericDAO = new GenericDAO<TipoAmbiente>();
    }

    public boolean salvar(TipoAmbiente tipoAmbiente) {
        if (tipoAmbiente.getId() == null) {
            return genericDAO.insert(tipoAmbiente);
        } else {
            return genericDAO.update(tipoAmbiente);
        }
    }

    public boolean remover(TipoAmbiente tipoAmbiente) {
        return genericDAO.remove(tipoAmbiente);
    }

    public List<TipoAmbiente> listar() {
        return genericDAO.getEm().createQuery("select t from tipo_ambiente t").getResultList();
    }

    public List<TipoAmbiente> listarPorLocalOrdenadoPorNome(Integer id) {
        Integer a = id;
        //  System.out.print(genericDAO.getEm().createQuery("select t from tipo_ambiente t ORDER BY t.nome ASC").getFirstResult());
        Query q = genericDAO.getEm().createQuery("select t from tipo_ambiente t where t.id_localizacao = :id ORDER BY t.nome ASC");
        q.setParameter("id", id);
        return q.getResultList();
    }

    public List<TipoAmbiente> listarOrdenadoPorNome() {
        //  System.out.print(genericDAO.getEm().createQuery("select t from tipo_ambiente t ORDER BY t.nome ASC").getFirstResult());
        return genericDAO.getEm().createQuery("select t from tipo_ambiente t ORDER BY t.nome ASC").getResultList();
    }

    public TipoAmbiente findById(int id) {
        return genericDAO.getEm().find(TipoAmbiente.class, id);
    }
}
