package view;

import controller.dao.AmbienteDAO;
import controller.dao.LocalizacaoDAO;
import controller.dao.TipoAmbienteDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.entidade.Ambiente;
import model.entidade.Localizacao;
import model.entidade.SituacaoAmbiente;
import model.entidade.TipoAmbiente;

@ManagedBean
@ViewScoped
public class CadastroAmbienteBean {

    private TipoAmbienteDAO tipoAmbienteDAO;
    private LocalizacaoDAO localizacaoDAO;

    private Ambiente ambiente = new Ambiente();
    private boolean isDivido = false;
    private List<TipoAmbiente> tipoAmbientes;
    private List<Localizacao> locais;
    private AmbienteDAO ambienteDAO;
    private String mensagem = "";

    private String idAmbiente;

    public CadastroAmbienteBean() {
        tipoAmbienteDAO = new TipoAmbienteDAO();
        localizacaoDAO = new LocalizacaoDAO();
        ambienteDAO = new AmbienteDAO();
        tipoAmbientes = tipoAmbienteDAO.listarOrdenadoPorNome();
        locais = localizacaoDAO.listarOrdenadorPorNome();

        Ambiente a = (Ambiente) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("ambienteEdit");
        if (a != null) {
            this.ambiente = a;
        }
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public boolean isIsDivido() {
        return isDivido;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public void setIsDivido(boolean isDivido) {
        this.isDivido = isDivido;
    }

    public void atualizarSigla() {
        if (!isDivido) {
            if (ambiente.getSigla().contains("/")) {
                String sigla = ambiente.getSigla().replace("/", "");
                ambiente.setDivisao(null);
            }
        }
    }

    public List<TipoAmbiente> getTipoAmbientes() {
        return tipoAmbientes;
    }

    public List<Localizacao> getLocais() {
        return locais;
    }

    public void setLocais(List<Localizacao> locais) {
        this.locais = locais;
    }

    public SituacaoAmbiente[] getSituacoes() {
        return SituacaoAmbiente.values();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void salvar() {
        String validacao = validarCampos();
        if (validacao.isEmpty()) {

            if (ambienteDAO.salvar(ambiente)) {
                setMensagem("Sucesso");
                ambiente = null;
                ambiente = new Ambiente();
            } else {
                setMensagem("Erro");
            }
        } else {
            setMensagem(validacao);
        }

    }

    private String validarCampos() {
        String campos = "";

        if (ambiente.getLocalizacao() == null) {
            campos += "Local;\n";
        }

        if (ambiente.getTipoAmbiente() == null) {
            campos += "Tipo do Ambiente;\n";
        }

        if (ambiente.getBloco() == null || ambiente.getBloco().isEmpty()) {
            campos += "Bloco;\n";
        }

        if (ambiente.getNumero() == null) {
            campos += "Número;\n";
        }

        if (ambiente.getAndar() == null || ambiente.getAndar() == -1) {
            campos += "Andar;\n";
        }

        if (ambiente.getCapacidade() == null) {
            campos += "Capacidade;\n";
        }
        String retorno = "";
        if (!campos.isEmpty()) {
            retorno = "Preencha os seguintes campos obrigatórios: \n" + campos;
        }
        return retorno;
    }

    public String cancelar() {
        setMensagem("");
        // return "/index.jsf?faces-redirect=true";
        return "";
    }

    public String getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(String idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

}
