package view;

import controller.controler.LoginController;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoginBean implements Serializable {

    private LoginController loginControler;
    private String usuario;
    private String senha;
    private String mensagem = "";

    public LoginBean() {
        loginControler = new LoginController();
    }

    public String getSenha() {
        return senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String efetuarLogin() {
        if (!this.usuario.isEmpty() && !this.senha.isEmpty()) {
            boolean res = loginControler.efetuarLogin(this.getUsuario(), this.getSenha());
            if (res) {
                return "/homes/homeAdm?faces-redirect=true";
            } else {
                setMensagem("Login ou senha incorretos!");
                this.setSenha("");
                this.setUsuario("");
                return null;
            }
        }
        return null;
    }
}
