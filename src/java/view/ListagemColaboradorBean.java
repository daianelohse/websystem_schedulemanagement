package view;

import controller.dao.AmbienteDAO;
import controller.dao.ColaboradorDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.entidade.Ambiente;
import model.entidade.Colaborador;

@ManagedBean
@ViewScoped
public class ListagemColaboradorBean {

    private List<Colaborador> colaboradores = new ArrayList<>();
    private ColaboradorDAO ambienteDAO = new ColaboradorDAO();
    private Colaborador colaboradorExcluido;
    private Integer idexcluir;

    public ListagemColaboradorBean() {
        colaboradores = ambienteDAO.listar();

    }

    public Integer getIdexcluir() {
        return idexcluir;
    }

    public void setIdexcluir(Integer idexcluir) {
        this.idexcluir = idexcluir;
    }
    
    public void deletar() {

        if (idexcluir != null) {
            Colaborador ambienteDeletar = ambienteDAO.findById(idexcluir);
            colaboradores.remove(ambienteDeletar);
            ambienteDAO.remover(ambienteDeletar);
        }
    }

    public void desfazerExclusao() {
        if (colaboradorExcluido != null) {
            colaboradores.add(colaboradorExcluido);
            ambienteDAO.salvar(colaboradorExcluido);
            colaboradorExcluido = null;
        }
    }

    public String editar(Colaborador ambiente) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("colaboradorEdit", ambiente);
        return "cadastroColaborador.jsf?faces-redirect=true";
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public ColaboradorDAO getAmbienteDAO() {
        return ambienteDAO;
    }

    public void setAmbienteDAO(ColaboradorDAO ambienteDAO) {
        this.ambienteDAO = ambienteDAO;
    }

    public Colaborador getColaboradorExcluido() {
        return colaboradorExcluido;
    }

    public void setColaboradorExcluido(Colaborador colaboradorExcluido) {
        this.colaboradorExcluido = colaboradorExcluido;
    }

}
