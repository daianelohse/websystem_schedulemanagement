
package controller.dao;

import java.util.List;
import model.entidade.Curso;
import model.entidade.Modalidade;

public class CursoDAO {
    
    private GenericDAO genericDAO;
    public CursoDAO() {
        genericDAO = new GenericDAO<Curso>();
    }
    
    public boolean salvar(Curso curso){
        if(curso.getId() == null){
            return genericDAO.insert(curso);
        }else{
            return genericDAO.update(curso);
        }
    }
    
    public boolean remover(Curso curso){
        return genericDAO.remove(curso);
    }
    
    public List<Curso> listar(){
        return genericDAO.getEm().createQuery("select c from curso c").getResultList();
    }
    
    public List<Curso> findByModalidade(Modalidade modalidade){
        return genericDAO.getEm().createQuery("SELECT c FROM curso c WHERE c.modalidade = :modalidade")
                .setParameter("modalidade", modalidade).getResultList();
    }

    public Curso findById(int id) {
        return genericDAO.getEm().find(Curso.class, id);
    }
}
