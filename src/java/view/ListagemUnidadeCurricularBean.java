package view;

import controller.dao.CursoDAO;
import controller.dao.TurmaDAO;
import controller.dao.UnidadeCurricularDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.entidade.Curso;
import model.entidade.Turma;
import model.entidade.UnidadeCurricular;

@ManagedBean
@ViewScoped
public class ListagemUnidadeCurricularBean {

    private List<UnidadeCurricular> unidadeCurriculares = new ArrayList<>();
    private Curso curso = new Curso();
    private Integer idexcluir;
    private UnidadeCurricularDAO unidadeCurricularDAO = new UnidadeCurricularDAO();

    public ListagemUnidadeCurricularBean() {
        unidadeCurriculares = unidadeCurricularDAO.listar();
      }

    public void deletar() {

        if (idexcluir != null) {
            UnidadeCurricular ambienteDeletar = unidadeCurricularDAO.findById(idexcluir);
            unidadeCurriculares.remove(ambienteDeletar);
            unidadeCurricularDAO.remover(ambienteDeletar);
        }
    }

    public Integer getIdexcluir() {
        return idexcluir;
    }

    public void setIdexcluir(Integer idexcluir) {
        this.idexcluir = idexcluir;
    }


    public String editar(UnidadeCurricular ambiente) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("ucEdit", ambiente);
        return "cadastroUnidadeCurricular.jsf?faces-redirect=true";
    }
    
    public void atualizarListagem(Curso curso){
        if(curso == null){
            unidadeCurriculares = unidadeCurricularDAO.listar();
        }else{
            unidadeCurriculares = unidadeCurricularDAO.findByCurso(curso);
        }
    }

    public List<UnidadeCurricular> getUnidadeCurriculares() {
        return unidadeCurriculares;
    }

    public void setUnidadeCurriculares(List<UnidadeCurricular> unidadeCurriculares) {
        this.unidadeCurriculares = unidadeCurriculares;
    }


   

    

}
