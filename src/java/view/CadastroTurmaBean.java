/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.controler.ControleTurma;
import controller.dao.CursoDAO;
import controller.dao.ModalidadeDAO;
import controller.dao.TurmaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.entidade.Curso;
import model.entidade.Modalidade;
import model.entidade.Turma;
import model.entidade.Turno;

/**
 *
 * @author Nicolas
 */
@ViewScoped
@ManagedBean
public class CadastroTurmaBean {

    private Turma turma;
    private CursoDAO cursoDAO = new CursoDAO();
    private ModalidadeDAO modalidadeDAO = new ModalidadeDAO();
    private List<Modalidade> modalidades;
    private List<Curso> cursos;
    private Modalidade modalidadeEscolhida = new Modalidade();
    private ControleTurma controleTurma = new ControleTurma();
    private TurmaDAO turmaDAO = new TurmaDAO();
    private boolean semestral = false;
    private Integer ano = 0;
    private String mensagem = "";

    public CadastroTurmaBean() {
        turma = new Turma();

//        Optional<List> lista = Optional.ofNullable(modalidadeDAO.listar());
//        lista.ifPresent(l-> modalidades = l);
        modalidades = modalidadeDAO.listar();

        cursos = new ArrayList<>();
        Turma a = (Turma) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("turmaEdit");
        if (a != null) {
            cursos = cursoDAO.findByModalidade(a.getCurso().getModalidade());
            this.turma = a;
            this.modalidadeEscolhida = a.getCurso().getModalidade();
            this.semestral = this.turma.getSemestre() != null;
        }
    }

    public Turma getTurma() {
        if (turma.getCurso() != null && turma.getAno() != null && turma.getTurno() != null) {
            turma.setNumero(controleTurma.buscarNumeroTurma(turma));
        }
        return turma;
    }

    public boolean isSemestral() {
        return semestral;
    }

    public void setSemestral(boolean semestral) {
        this.semestral = semestral;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Modalidade getModalidadeEscolhida() {
        return modalidadeEscolhida;
    }

    public void setModalidadeEscolhida(Modalidade modalidadeEscolhida) {
        this.modalidadeEscolhida = modalidadeEscolhida;
    }

    public List<Modalidade> getModalidades() {
        return modalidades;
    }

    public void setModalidades(List<Modalidade> modalidades) {
        this.modalidades = modalidades;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Turno[] getTurnos() {
        return Turno.values();
    }

    public void buscarCursos() {
        if (modalidadeEscolhida.getId() != null) {
            setCursos(cursoDAO.findByModalidade(modalidadeEscolhida));
        }
    }

    public void atualizarSigla() {
        if (!isSemestral()) {
            turma.setSemestre(null);
        }
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void salvar() {

        String validacao = validarCampos();
        if (validacao.isEmpty()) {

            if (turmaDAO.salvar(turma)) {
                setMensagem("Sucesso");
                turma = null;
                turma = new Turma();
                modalidadeEscolhida = new Modalidade();
                semestral = false;
            } else {
                setMensagem("Erro");
            }
        } else {
            setMensagem(validacao);
        }

    }

    private String validarCampos() {
        String campos = "";

        if (turma.getCurso() == null) {
            campos += "Curso;\n";
        }

        if (turma.getTurno() == null) {
            campos += "Turno;\n";
        }

        if (turma.getAno() == null) {
            campos += "Ano;\n";
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
}
