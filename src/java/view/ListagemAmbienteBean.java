package view;

import controller.dao.AmbienteDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.entidade.Ambiente;

@ManagedBean
@ViewScoped
public class ListagemAmbienteBean {

    private List<Ambiente> ambientes = new ArrayList<>();
    private AmbienteDAO ambienteDAO = new AmbienteDAO();
    private Ambiente ambienteExcluido;
    private Integer idexcluir;

    public ListagemAmbienteBean() {
       
        this.ambientes = ambienteDAO.listar();
        

    }

    public void deletar() {

        if (idexcluir != null) {
            Ambiente ambienteDeletar = ambienteDAO.findById(idexcluir);
            this.ambienteExcluido = ambienteDeletar;
            ambientes.remove(ambienteDeletar);
            ambienteDAO.remover(ambienteDeletar);
            System.out.println("excluido");
        }
    }

     public String editar(Ambiente ambiente) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("ambienteEdit", ambiente);
        return "cadastroAmbiente.jsf?faces-redirect=true";
    }

    public List<Ambiente> getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(List<Ambiente> ambientes) {
        this.ambientes = ambientes;
    }

    public void setIdexcluir(Integer idexcluir) {
        this.idexcluir = idexcluir;
    }

    public Integer getIdexcluir() {
        return idexcluir;
    }

}
