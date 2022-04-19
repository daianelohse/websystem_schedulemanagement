
package controller.dao;

import java.util.List;
import model.entidade.Ambiente;
import model.entidade.Curso;
import model.entidade.Modalidade;
import model.entidade.UnidadeCurricular;

public class UnidadeCurricularDAO {
    
    private GenericDAO genericDAO;
    public UnidadeCurricularDAO() {
        genericDAO = new GenericDAO<UnidadeCurricular>();
    }
    
    public boolean salvar(UnidadeCurricular uc){
        if(uc.getId() == null){
            return genericDAO.insert(uc);
        }else{
            return genericDAO.update(uc);
        }
    }
    public boolean remover(UnidadeCurricular uc){
        return genericDAO.remove(uc);
    }
    public List<UnidadeCurricular> listar(){
        return genericDAO.getEm().createQuery("select uc from unidade_curricular uc").getResultList();
    }

    public UnidadeCurricular findById(int i) {
        return genericDAO.getEm().find(UnidadeCurricular.class, i);
    }
    
    public List<UnidadeCurricular> findByModalidade(Modalidade modalidade){
        return genericDAO.getEm().createQuery("SELECT u FROM unidade_curricular u WHERE u.modalidade = :modalidade")
                .setParameter("modalidade", modalidade)
                .getResultList();
    }

    public List<UnidadeCurricular> findByCurso(Curso curso) {
        return genericDAO.getEm().createQuery("SELECT uc FROM unidade_curricular uc INNER JOIN uc.cursosGrade ucc where ucc.curso = :curso GROUP BY uc")
                .setParameter("curso", curso)
                .getResultList();
    }

    
}
