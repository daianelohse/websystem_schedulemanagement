package controller.dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.entidade.Colaborador;
import model.entidade.Curso;
import model.entidade.DiaTrabalho;
import model.entidade.Modalidade;

public class ColaboradorDAO {

    private GenericDAO genericDAO;

    public ColaboradorDAO() {
        genericDAO = new GenericDAO<Colaborador>();
    }

    public boolean salvar(Colaborador colaborador) {
        if (colaborador.getId() == null) {
            System.out.println(colaborador.getPessoa().getNome());
            for (DiaTrabalho object : colaborador.getGradeTrabalho()) {
                System.out.println(object.getDisponibilidadeDocente());
            }
            return genericDAO.insert(colaborador);
        } else {
            return genericDAO.update(colaborador);
        }
    }

    public boolean remover(Colaborador colaborador) {
        return genericDAO.remove(colaborador);
    }

    public List<Colaborador> listar() {
        return genericDAO.getEm().createQuery("select c from colaborador c").getResultList();
    }

    public Colaborador buscarColaboradorPorEmail(String email) {
        Query q = genericDAO.getEm().createQuery("SELECT c FROM colaborador c WHERE c.emailComercial LIKE :email");
        q.setParameter("email", email);
        try {
            return (Colaborador) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Colaborador findById(int i) {
        return genericDAO.getEm().find(Colaborador.class, i);
    }
    
    public Colaborador findByUserName(String i) {
        Query q =  genericDAO.getEm().createQuery("SELECT c FROM colaborador c WHERE c.pessoa.login.usuario = :i")
                .setParameter("i", i);
        try {
            return (Colaborador)q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Colaborador buscarColaboradorPorUsuario(String usuario) {
        Query q = genericDAO.getEm().createQuery("SELECT c FROM colaborador c WHERE c.pessoa.login.usuario = :usuario")
                .setParameter("usuario", usuario);
        try {
            return (Colaborador) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Modalidade> getModalidadesParticipante(Colaborador colaboradorLogado) {
        return genericDAO.getEm().createQuery("SELECT m FROM modalidade m JOIN m.cursos c JOIN c.coordenadores coord WHERE coord = :colaboradorLogado GROUP BY m")
                .setParameter("colaboradorLogado", colaboradorLogado)
                .getResultList();
    }

    public List<Curso> getCursosParticipantes(Modalidade modalidadePesquisa, Colaborador colaboradorLogado) {
        return genericDAO.getEm().createQuery("SELECT c FROM curso c JOIN c.coordenadores coord WHERE c.modalidade =:modalidade AND coord = :colaboradorLogado")
                .setParameter("colaboradorLogado", colaboradorLogado)
                .setParameter("modalidade", modalidadePesquisa)
                .getResultList();
    }

}
