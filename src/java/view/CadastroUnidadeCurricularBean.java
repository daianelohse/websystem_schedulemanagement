package view;

import controller.dao.ModalidadeDAO;
import controller.dao.UnidadeCurricularDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.entidade.Modalidade;
import model.entidade.Turma;
import model.entidade.UnidadeCurricular;

/**
 *
 * @author Nicolas
 */
@ManagedBean
@ViewScoped
public class CadastroUnidadeCurricularBean {

    private UnidadeCurricular uc;
    private Modalidade modalidade;

    private List<Modalidade> modalidades;

    private ModalidadeDAO modalidadeDao = new ModalidadeDAO();
    private UnidadeCurricularDAO ucDao = new UnidadeCurricularDAO();
    
    private String mensagem = "";

    public CadastroUnidadeCurricularBean() {
        this.modalidades = new ArrayList<Modalidade>();
        setModalidades(modalidadeDao.listar());
        uc = new UnidadeCurricular();
        modalidade = new Modalidade();
        UnidadeCurricular a = (UnidadeCurricular) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("ucEdit");
        if(a != null){
            this.uc = a;
            modalidade = uc.getModalidade();
        }
        
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public UnidadeCurricular getUc() {
        return uc;
    }

    public void setUc(UnidadeCurricular uc) {
        this.uc = uc;
    }

    public List<Modalidade> getModalidades() {
        return modalidades;
    }

    public void setModalidades(List<Modalidade> modalidades) {
        this.modalidades = modalidades;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    

    public void salvar() {
        uc.setModalidade(modalidade);
        ucDao.salvar(uc);
        uc = new UnidadeCurricular();
        modalidade = new Modalidade();
    }
}
