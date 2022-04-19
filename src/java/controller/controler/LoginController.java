package controller.controler;

import controller.dao.LoginDAO;
import model.entidade.Login;
import util.EncriptadorUtil;

public class LoginController {

    private LoginDAO loginDAO;

    public LoginController() {
        loginDAO = new LoginDAO();
    }

    public Login buscarLoginByNomeUsuario(String nomeUsuario) {
        return loginDAO.findLoginPorUserName(nomeUsuario);
    }

    public boolean efetuarLogin(String usuario, String senha) {
        Login login = buscarLoginByNomeUsuario(usuario);
        if (login != null) {
            return EncriptadorUtil.validarSenha(senha, login.getSenha());
        }
        return false;
    }

}
