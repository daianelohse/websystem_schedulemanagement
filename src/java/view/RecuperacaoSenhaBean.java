package view;

import controller.dao.ColaboradorDAO;
import emailservice.EmailUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.entidade.Colaborador;
import org.apache.commons.mail.EmailException;
import util.EncriptadorUtil;
import util.GeradorSenha;
import util.ValidatorUtil;

@ViewScoped
@ManagedBean
public class RecuperacaoSenhaBean implements Serializable {

    private String email;
    private ColaboradorDAO colaboradorDAO;
    private boolean mensagemEnviandoEmail = false;
    private String mensagemErro = "";
    private boolean panelCadastro = true;
    private boolean panelMensagemEnviada = false;

    public RecuperacaoSenhaBean() {
        colaboradorDAO = new ColaboradorDAO();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public boolean isMensagemEnviandoEmail() {
        return mensagemEnviandoEmail;
    }

    public void setMensagemEnviandoEmail(boolean mensagemEnviandoEmail) {
        this.mensagemEnviandoEmail = mensagemEnviandoEmail;
    }

    public void enviarEmailRecuperacao() {
        mensagemErro = "Você não possui acesso ao sistema ou o e-mail digitado não está correto. "
                + "Por favor, verifique e tente novamente";
        if (!email.isEmpty() && ValidatorUtil.validarEmail(email)) {
            panelCadastro = false;

            Colaborador colaborador = colaboradorDAO.buscarColaboradorPorEmail(this.getEmail());
            if (colaborador == null) {

                mensagemEnviandoEmail = false;
                panelCadastro = true;
                panelMensagemEnviada = false;
            } else {
                try {
                    String senhaGerada = GeradorSenha.gerarSenha();
                    colaborador.getPessoa().getLogin().setSenha(senhaGerada);
                    EmailUtils.enviaEmailRecuperacaoSenha(colaborador, senhaGerada);
                    colaboradorDAO.salvar(colaborador);
                    mensagemEnviandoEmail = false;
                    panelMensagemEnviada = true;

                } catch (EmailException ex) {
                    Logger.getLogger(RecuperacaoSenhaBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public boolean isPanelCadastro() {
        return panelCadastro;
    }

    public void setPanelCadastro(boolean panelCadastro) {
        this.panelCadastro = panelCadastro;
    }

    public boolean isPanelMensagemEnviada() {
        return panelMensagemEnviada;
    }

    public void setPanelMensagemEnviada(boolean panelMensagemEnviada) {
        this.panelMensagemEnviada = panelMensagemEnviada;
    }
}
