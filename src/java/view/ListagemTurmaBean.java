package view;

import controller.dao.TurmaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.entidade.Curso;
import model.entidade.Turma;

@ManagedBean
@ViewScoped
public class ListagemTurmaBean {

    private List<Turma> turmas = new ArrayList<>();
    private TurmaDAO ambienteDAO = new TurmaDAO();
  
    private Integer idexcluir;

    public ListagemTurmaBean() {
        turmas = ambienteDAO.listar();

    }

    public void deletar() {

        if (idexcluir != null) {
            Turma ambienteDeletar = ambienteDAO.findById(idexcluir);
            turmas.remove(ambienteDeletar);
            ambienteDAO.remover(ambienteDeletar);
        }
    }

    public Integer getIdexcluir() {
        return idexcluir;
    }

    public void setIdexcluir(Integer idexcluir) {
        this.idexcluir = idexcluir;
    }


    public String editar(Turma ambiente) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("turmaEdit", ambiente);
        return "cadastroTurma.jsf?faces-redirect=true";
    }

    

    public TurmaDAO getAmbienteDAO() {
        return ambienteDAO;
    }

    public void setAmbienteDAO(TurmaDAO ambienteDAO) {
        this.ambienteDAO = ambienteDAO;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

   

    

}
