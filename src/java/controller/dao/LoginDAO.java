package controller.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.entidade.Login;

public class LoginDAO {

    private GenericDAO<Login> daoGeneric;

    public LoginDAO() {
        daoGeneric = new GenericDAO<>();
    }

    public boolean salvar(Login login) {
        if (login.getId() == null) {
            return daoGeneric.insert(login);
        } else {
            return daoGeneric.update(login);
        }
    }

    public boolean remover(Login login) {
        return daoGeneric.remove(login);
    }

    public Login findLoginPorUserName(String nomeUsuario) {
        Query q = daoGeneric.getEm().createQuery("SELECT l FROM login l WHERE l.usuario LIKE :nomeUsuario");
        q.setParameter("nomeUsuario", nomeUsuario);
        try {
            Login l = (Login) q.getSingleResult();
            return l;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
