package view;

import controller.dao.ColaboradorDAO;
import controller.dao.CursoDAO;
import controller.dao.LoginDAO;
import controller.dao.ModalidadeDAO;
import controller.dao.UnidadeCurricularDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.entidade.Area;
import model.entidade.Colaborador;
import model.entidade.Curso;
import model.entidade.DiaSemana;
import model.entidade.DiaTrabalho;
import model.entidade.DisponibilidadeDocente;
import model.entidade.HorarioAula;
import model.entidade.Login;
import model.entidade.Modalidade;
import model.entidade.Permissao;
import model.entidade.Pessoa;
import model.entidade.Regra;
import model.entidade.TipoContrato;
import model.entidade.UnidadeCurricular;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import util.EncriptadorUtil;

@ManagedBean
@ViewScoped
public class CadastroColaboradorBean implements Serializable {

    private Map<HorarioAula, Map<DiaSemana, DisponibilidadeDocente>> gradeHoraria;
    private Colaborador colaborador = new Colaborador();
    private String usuario = " ";

    private ModalidadeDAO modalidadeDAO = new ModalidadeDAO();
    private CursoDAO cursoDAO = new CursoDAO();
    private UnidadeCurricularDAO unidadeCurricularDAO = new UnidadeCurricularDAO();
    private ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
    private List<Modalidade> modalidades = new ArrayList<>();
    private boolean mensalista = false;
    private List<Curso> cursos = new ArrayList<Curso>();
    private List<Curso> cursosSelecionados = new ArrayList<Curso>();

    private DualListModel<UnidadeCurricular> unidadesCurricularesPesquisa;
    private List<UnidadeCurricular> ucsSource = new ArrayList<UnidadeCurricular>();
    private List<UnidadeCurricular> ucsTarget = new ArrayList<UnidadeCurricular>();

    private boolean coordCurso = false;
    private boolean coordModalidade = false;
    private boolean professor = false;
    private boolean disableCursosPesquisa = true;

    private String mensagem = "";
    private Curso cursoEscolhido = new Curso();
    private Modalidade modalidadeEscolhida = new Modalidade();
    private List<Curso> cursosPesquisa = new ArrayList<Curso>();
    private List<Modalidade> modalidadesPesquisa = new ArrayList<>();

    private boolean emailValido = false;
    private boolean usuarioValido = false;

    private LoginDAO loginDAO = new LoginDAO();

    private String corEmailValidacao = "amber";
    private String iconeEmailValidacao = "info_outline";
    private String mensagemEmailValidacao = "O e-mail não pode"
            + " começar com números, "
            + "deve ter um '@' e finalizar com um domínio";

    public CadastroColaboradorBean() {
        gradeHoraria = new LinkedHashMap<HorarioAula, Map<DiaSemana, DisponibilidadeDocente>>();
        for (HorarioAula object : HorarioAula.values()) {
            Map<DiaSemana, DisponibilidadeDocente> map = new LinkedHashMap<>();
            for (DiaSemana dia : DiaSemana.values()) {
                map.put(dia, DisponibilidadeDocente.PREFERENCIAL);
            }
            gradeHoraria.put(object, map);
        }

        modalidades = modalidadeDAO.listar();
        modalidadesPesquisa = new ArrayList<>(modalidades);
        cursos = cursoDAO.listar();
        ucsSource = unidadeCurricularDAO.listar();
        unidadesCurricularesPesquisa = new DualListModel<UnidadeCurricular>(ucsSource, ucsTarget);

        colaborador.setPessoa(new Pessoa());
        colaborador.getPessoa().setLogin(new Login());
        
        Colaborador a = (Colaborador) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("colaboradorEdit");
        if (a != null) {
            this.colaborador = a;
            this.coordCurso = a.getCursosCoordenacao() != null;
            try{
            this.coordModalidade = a.isCoordenadorModalidade();
            }catch(Exception e){}
            this.professor = a.getCompetencias()!=null;
            if(a.getCursosCoordenacao()!=null){
                this.cursosSelecionados = a.getCursosCoordenacao();
            }  
            if(a.getCompetencias()!=null){
                this.ucsTarget = a.getCompetencias();
            }
            if(a.getTipoContrato() == TipoContrato.MENSALISTA){
                this.mensalista = true;
            }
            a.getPessoa().getLogin();
            for (DiaTrabalho diaTrabalho : a.getGradeTrabalho()) {
               Map<DiaSemana, DisponibilidadeDocente> mapA = gradeHoraria.get(diaTrabalho.getHorario());
               mapA.replace(diaTrabalho.getDia(), diaTrabalho.getDisponibilidadeDocente());
            }
        }
    }

    public DisponibilidadeDocente[] getDisponibilidades() {
        return DisponibilidadeDocente.values();
    }

    public Map<HorarioAula, Map<DiaSemana, DisponibilidadeDocente>> getGradeHoraria() {
        return gradeHoraria;
    }

    public void setGradeHoraria(Map<HorarioAula, Map<DiaSemana, DisponibilidadeDocente>> gradeHoraria) {
        this.gradeHoraria = gradeHoraria;
    }

    public DiaSemana[] getDias() {
        return DiaSemana.values();
    }

    public DisponibilidadeDocente disponibilidade() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String hor = getParam(fc, "horario");
        if (hor != null) {
            HorarioAula horario = HorarioAula.valueOf(hor);
            DiaSemana dia = DiaSemana.valueOf(getParam(fc, "dia"));
            return gradeHoraria.get(horario).get(dia);
        }
        return DisponibilidadeDocente.DISPONIVEL;
    }

    public List<DiaTrabalho> getGradetrabalho() {

        List<DiaTrabalho> gradeTrabalho = new ArrayList<>();
        for (HorarioAula horario : gradeHoraria.keySet()) {
            Map<DiaSemana, DisponibilidadeDocente> map = gradeHoraria.get(horario);
            for (DiaSemana diaSemana : map.keySet()) {
                DiaTrabalho dia = new DiaTrabalho(horario,
                        map.get(diaSemana),
                        diaSemana,
                        colaborador);
                dia.setColaborador(colaborador);
                gradeTrabalho.add(dia);
            }
        }
        return gradeTrabalho;
    }

    public Curso getCursoEscolhido() {
        return cursoEscolhido;
    }

    public void setCursoEscolhido(Curso cursoEscolhido) {
        this.cursoEscolhido = cursoEscolhido;
    }

    public String getParam(FacesContext fc, String nome) {
        Map<String, String> parametros = fc.getExternalContext().getRequestParameterMap();
        return parametros.get(nome);
    }

    public HorarioAula[] getHorarios() {
        return HorarioAula.values();
    }

    public Area[] getAreas() {
        return Area.values();
    }

    public TipoContrato[] getContratos() {
        return TipoContrato.values();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void atualizarNomeUsuario() {
        String[] nome = colaborador.getEmailComercial().split("@");
        setUsuario(nome[0]);
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public List<Modalidade> getModalidades() {
        return modalidades;
    }

    public boolean isMensalista() {
        return mensalista;
    }

    public void setMensalista(boolean mensalista) {
        this.mensalista = mensalista;
    }

    public void verificarHorasDiarias() {
        setMensalista(colaborador.getTipoContrato() == TipoContrato.MENSALISTA);
    }

    public boolean isCoordCurso() {
        return coordCurso;
    }

    public void setCoordCurso(boolean coordCurso) {
        this.coordCurso = coordCurso;
    }

    public boolean isProfessor() {
        return professor;
    }

    public void setProfessor(boolean professor) {
        this.professor = professor;
    }

    public boolean isCoordModalidade() {
        return coordModalidade;
    }

    public void setCoordModalidade(boolean coordModalidade) {
        this.coordModalidade = coordModalidade;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Curso> getCursosSelecionados() {
        return cursosSelecionados;
    }

    public void setCursosSelecionados(List<Curso> cursosSelecionados) {
        this.cursosSelecionados = cursosSelecionados;
    }

    public List<Curso> procurarCursos(String query) {
        List<Curso> retorno = new ArrayList<>();

        for (Curso curso : cursos) {
            if (curso.getNome().toLowerCase().startsWith(query.toLowerCase())) {
                retorno.add(curso);
            }
        }
        return retorno;
    }

    public void selecionou() {
        System.out.println("Selecionado");
    }

    public void removerCursoSelecionado(UnselectEvent event) {
        cursosSelecionados.remove((Curso) event.getObject());
        cursos.add((Curso) event.getObject());
    }

    public void cursoSelecionado(SelectEvent event) {
        cursos.remove((Curso) event.getObject());
    }

    public void setUnidadesCurricularesPesquisa(DualListModel<UnidadeCurricular> unidadesCurricularesPesquisa) {
        this.unidadesCurricularesPesquisa = unidadesCurricularesPesquisa;
    }

    public DualListModel<UnidadeCurricular> getUnidadesCurricularesPesquisa() {
        return unidadesCurricularesPesquisa;
    }

    public boolean isEmailValido() {
        return emailValido;
    }

    public void setEmailValido(boolean emailValido) {
        this.emailValido = emailValido;
    }

    public boolean isUsuarioValido() {
        return usuarioValido;
    }

    public void setUsuarioValido(boolean usuarioValido) {
        this.usuarioValido = usuarioValido;
    }

    //icones: done
    public void verificarEmail() {
        String mensagem = "";
        if (!colaborador.getEmailComercial().matches("^[A-z]([A-z]+|[0-9]+|\\.)+@[A-z]+\\.[A-z]{2,3}")
                || colaborador.getEmailComercial().endsWith("@sc.senai.br")
                || colaborador.getEmailComercial().endsWith("@edu.sc.senai.br")
                || colaborador.getEmailComercial().endsWith("@fiesc.br")) {
            mensagem += "O e-mail não pode começar com números, deve ter um '@' e finalizar com um domínio\n";
        } else if (colaboradorDAO.buscarColaboradorPorEmail(colaborador.getEmailComercial()) != null) {
            mensagem += "E-mail existente";
            setCorEmailValidacao("red");
            setIconeEmailValidacao("close");
        }
        if (mensagem.isEmpty()) {
            setEmailValido(true);
            setCorEmailValidacao("green");
            setIconeEmailValidacao("done");

        } else {
            setEmailValido(false);
            setCorEmailValidacao("amber");
            setIconeEmailValidacao("info_outline");
        }
        setMensagemEmailValidacao(mensagem);

    }

    public void verificarLogin() {
        String mensagem = "";
        if (loginDAO.findLoginPorUserName(usuario) != null) {
            mensagem = "Usuário já existente!";
        }
        if (mensagem.isEmpty()) {
            setUsuarioValido(true);
        } else {
            setUsuarioValido(false);
        }
    }

    public void salvar() {
        String validacao = validarCampos();
        if (validacao.isEmpty()) {
            colaborador.getPessoa().getLogin().setUsuario(usuario);
            colaborador.setCoordenadorModalidade(coordModalidade);
            colaborador.setGradeTrabalho(getGradetrabalho());
            colaborador.getPessoa().getLogin().setPessoa(colaborador.getPessoa());
            colaborador.getPessoa().getLogin().setSenha(
                    colaborador.getPessoa().getLogin().getSenha());
            
            List<Permissao> permissoes = new ArrayList<>();
            permissoes.add(new Permissao(colaborador.getPessoa().getLogin(), Regra.ROLE_FUNCIONARIO));
            if (professor) {
                for (UnidadeCurricular uc : unidadesCurricularesPesquisa.getTarget()) {
                    uc.getDocentesHabilitados().add(colaborador);
                }
                colaborador.setCompetencias(unidadesCurricularesPesquisa.getTarget());
                permissoes.add(new Permissao(colaborador.getPessoa().getLogin(), Regra.ROLE_PROFESSOR));
            }
            if (coordCurso) {
                for (Curso curso : cursosSelecionados) {
                    curso.getCoordenadores().add(colaborador);
                }
                colaborador.setCursosCoordenacao(cursosSelecionados);
                permissoes.add(new Permissao(colaborador.getPessoa().getLogin(), Regra.ROLE_COORDENADOR_CURSO));
            }
            if (coordModalidade) {
                permissoes.add(new Permissao(colaborador.getPessoa().getLogin(), Regra.ROLE_COORDENADOR_MODALIDADE));
            }
            if (coordCurso || coordModalidade) {
                permissoes.add(new Permissao(colaborador.getPessoa().getLogin(), Regra.ROLE_RELATS));
            }
            colaborador.getPessoa().getLogin().setPermissoes(permissoes);
            if (colaboradorDAO.salvar(colaborador)) {
                setMensagem("Sucesso");
                colaborador = null;
                colaborador = new Colaborador();
            } else {
                setMensagem("Erro");
            }
        } else {
            setMensagem(validacao);
        }

    }

    private String validarCampos() {
        String campos = "";

        if (colaborador.getPessoa().getNome() == null) {
            campos += "Nome;\n";
        }

        if (colaborador.getMatricula() == null) {
            campos += "Matrícula;\n";
        }

        if (colaborador.getPessoa().getDataNascimento() == null) {
            campos += "Data de Nascimento;\n";
        }

        if (colaborador.getEmailComercial() == null || colaborador.getEmailComercial().isEmpty()) {
            campos += "E-mail;\n";
        }

        if (colaborador.getPessoa().getLogin().getSenha() == null
                || colaborador.getPessoa().getLogin().getSenha().isEmpty()) {
            campos += "Senha;\n";
        }

        if (colaborador.getArea() == null) {
            campos += "Área;\n";
        }

        if (colaborador.getModalidade() == null) {
            campos += "Modalidade;\n";
        }

        if (colaborador.getTipoContrato() == null) {
            campos += "Tipo Contrato;\n";
        } else {
            if (colaborador.getTipoContrato() == TipoContrato.MENSALISTA
                    && colaborador.getQuantidadeHoraDiaria() == null) {
                campos += "Hora diária (já que é mensalista);\n";
            }
        }

        if (colaborador.getRamal() == null) {
            campos += "Ramal;\n";
        }
        if (!isCoordCurso() && !isCoordModalidade() && !isProfessor()) {
            campos += "Perfil do Colaborador(pelo menos 1);\n";
        }

        if (isCoordCurso() && cursosSelecionados.isEmpty()) {
            campos += "Curso de coordenação(pelo menos 1);\n";
        }
        if (isProfessor() && unidadesCurricularesPesquisa.getTarget().isEmpty()) {
            campos += "Competências(pelo menos 1);\n";
        }
        String retorno = "";
        if (!campos.isEmpty()) {
            retorno = "Preencha os seguintes campos obrigatórios: \n" + campos;
        }
        return retorno;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Modalidade getModalidadeEscolhida() {
        return modalidadeEscolhida;
    }

    public void setModalidadeEscolhida(Modalidade modalidadeEscolhida) {
        this.modalidadeEscolhida = modalidadeEscolhida;
    }

    public void buscarCursosPesquisa() {
        if (modalidadeEscolhida != null) {
            if (modalidadeEscolhida.getId() != null) {
                setCursosPesquisa(cursoDAO.findByModalidade(modalidadeEscolhida));
            }
            setDisableCursosPesquisa(false);
        } else {
            setModalidadeEscolhida(null);
            setCursosPesquisa(null);
            setCursoEscolhido(null);
            setDisableCursosPesquisa(true);
        }
    }

    public void setModalidades(List<Modalidade> modalidades) {
        this.modalidades = modalidades;
    }

    public List<Curso> getCursosPesquisa() {
        return cursosPesquisa;
    }

    public void setCursosPesquisa(List<Curso> cursosPesquisa) {
        this.cursosPesquisa = cursosPesquisa;
    }

    public List<Modalidade> getModalidadesPesquisa() {
        return modalidadesPesquisa;
    }

    public void setModalidadesPesquisa(List<Modalidade> modalidadesPesquisa) {
        this.modalidadesPesquisa = modalidadesPesquisa;
    }

    public boolean isDisableCursosPesquisa() {
        return disableCursosPesquisa;
    }

    public void setDisableCursosPesquisa(boolean disableCursosPesquisa) {
        this.disableCursosPesquisa = disableCursosPesquisa;
    }

    

    public List<UnidadeCurricular> getUcsSource() {
        return ucsSource;
    }

    public void setUcsSource(List<UnidadeCurricular> ucsSource) {
        this.ucsSource = ucsSource;
    }

    public List<UnidadeCurricular> getUcsTarget() {
        return ucsTarget;
    }

    public void setUcsTarget(List<UnidadeCurricular> ucsTarget) {
        this.ucsTarget = ucsTarget;
    }

    public String getCorEmailValidacao() {
        return corEmailValidacao;
    }

    public void setCorEmailValidacao(String corEmailValidacao) {
        this.corEmailValidacao = corEmailValidacao;
    }

    public String getIconeEmailValidacao() {
        return iconeEmailValidacao;
    }

    public void setIconeEmailValidacao(String iconeEmailValidacao) {
        this.iconeEmailValidacao = iconeEmailValidacao;
    }

    public String getMensagemEmailValidacao() {
        return mensagemEmailValidacao;
    }

    public void setMensagemEmailValidacao(String mensagemEmailValidacao) {
        this.mensagemEmailValidacao = mensagemEmailValidacao;
    }

}
