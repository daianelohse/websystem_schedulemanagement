package view;

import controller.dao.CursoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.entidade.Curso;

@ManagedBean
@ViewScoped
public class ListagemCursoBean {

    private List<Curso> cursos = new ArrayList<>();
    private CursoDAO ambienteDAO = new CursoDAO();
    private Curso cursoExcluido = new Curso();
    private Integer idexcluir;

    public ListagemCursoBean() {
        cursos = ambienteDAO.listar();

    }

    public void deletar() {

        if (idexcluir != null) {
            Curso ambienteDeletar = ambienteDAO.findById(idexcluir);
            cursos.remove(ambienteDeletar);
            ambienteDAO.remover(ambienteDeletar);
        }
    }

    public Integer getIdexcluir() {
        return idexcluir;
    }

    public void setIdexcluir(Integer idexcluir) {
        this.idexcluir = idexcluir;
    }


    public String editar(Curso ambiente) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("cursoEdit", ambiente);
        return "cadastroCurso.jsf?faces-redirect=true";
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public CursoDAO getAmbienteDAO() {
        return ambienteDAO;
    }

    public void setAmbienteDAO(CursoDAO ambienteDAO) {
        this.ambienteDAO = ambienteDAO;
    }

    public Curso getCursoExcluido() {
        return cursoExcluido;
    }

    public void setCursoExcluido(Curso cursoExcluido) {
        this.cursoExcluido = cursoExcluido;
    }

}
