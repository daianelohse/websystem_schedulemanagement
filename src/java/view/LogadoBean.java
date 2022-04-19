/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.dao.ColaboradorDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.entidade.Colaborador;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean
@SessionScoped
public class LogadoBean {

    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    private String usuarioLogado = auth.getName();
    private Colaborador colaborador = new Colaborador();
    private ColaboradorDAO colaboradorDAO = new ColaboradorDAO();

    public LogadoBean() {
        colaborador = colaboradorDAO.buscarColaboradorPorUsuario(usuarioLogado);
    }

    public String getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(String usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

}
