
package controller.dao;

import java.util.List;
import model.entidade.Curso;
import model.entidade.Turma;

public class TurmaDAO {
    
    private GenericDAO genericDAO;
    public TurmaDAO() {
        genericDAO = new GenericDAO<Turma>();
    }
    
    public boolean salvar(Turma turma){
        if(turma.getId() == null){
            return genericDAO.insert(turma);
        }else{
            return genericDAO.update(turma);
        }
    }
    
    public boolean remover(Turma turma){
        return genericDAO.remove(turma);
    }
    
    public List<Turma> listar(){
        return genericDAO.getEm().createQuery("select t from turma t").getResultList();
    }

    public Turma findById(Integer idexcluir) {
        return genericDAO.getEm().find(Turma.class, idexcluir);
    }
    
    public List<Turma> findByCurso(Curso curso) {
        return genericDAO.getEm().createQuery("select t from turma t WHERE t.curso = :curso").setParameter("curso", curso).getResultList();
    }
}
