package view;

import controller.controler.ConstrucaoHorarioController;
import controller.controler.TableFactoryTD;
import controller.dao.ColaboradorDAO;
import controller.dao.CursoDAO;
import controller.dao.HorarioTurmaDAO;
import controller.dao.ModalidadeDAO;
import controller.dao.PreReservaAulaDAO;
import controller.dao.TurmaDAO;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.entidade.Colaborador;
import model.entidade.Curso;
import model.entidade.HorarioTurma;
import model.entidade.Modalidade;
import model.entidade.PreReservaAula;
import model.entidade.Regra;
import model.entidade.TipoPreReserva;
import model.entidade.Turma;
import model.entidade.UnidadeCurricular;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean
@ViewScoped
public class ConstrucaoHorarioBean implements Serializable {

    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    private Colaborador colaboradorLogado;

    private ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
    private ModalidadeDAO modalidadeDAO = new ModalidadeDAO();
    private CursoDAO cursoDAO = new CursoDAO();

    //Listas superiores
    private List<UnidadeCurricular> ucs = new ArrayList<UnidadeCurricular>();
    private List<Modalidade> modalidadesHabilitadas = new ArrayList<Modalidade>();
    private List<Curso> cursosHabilitados = new ArrayList<Curso>();
    private List<Turma> turmasHabilitadas = new ArrayList<Turma>();
    private List<String> dadosTabelaHorario = new ArrayList<String>();
    private List<String> meses = new ArrayList<String>();
    private List<PreReservaAula> preReservas = new ArrayList<PreReservaAula>();
    
    // Variáveis para selects
    private Modalidade modalidadePesquisa = new Modalidade();
    private Curso cursoEscolhido = new Curso();
    private Turma turmaEscolhida = new Turma();
    
    // 

    private boolean isAdm = true;
    private String iconBuscarTurma = "keyboard_arrow_down";
    private String disabledBuscar = "disabled";

    //tabela de horarios
    public LocalDate dataInicial;
    public LocalDate dataFinal;
    private boolean primeiraTabelaGerada = false;
    private String disabledBtCalendario = "disabled";
    private String mensagemDataInvalida = "";
    private String dataString = "";

    // Divisão Horário
    private PreReservaAula reservaOpcional = null;
    private Integer indexSelecionado;
    private PreReservaAula preReserva = null;
    
    private HorarioTurmaDAO horarioDao = new HorarioTurmaDAO();
    private TurmaDAO turmaDao = new TurmaDAO();
    private PreReservaAulaDAO preRervaDao = new PreReservaAulaDAO();
    private HorarioTurma horarioTurma;
    
    private boolean isDividido = false;
    private int semestre = 0;
    
    private ConstrucaoHorarioController construcaoHorarioController = new ConstrucaoHorarioController();

    public ConstrucaoHorarioBean() {
        reservaOpcional = new PreReservaAula();
        preReserva = new PreReservaAula();
        colaboradorLogado = colaboradorDAO.buscarColaboradorPorUsuario(auth.getName());
        isAdm = auth.getAuthorities().contains(new GrantedAuthorityImpl(Regra.ROLE_ADMIN.toString()));
        listarModalidadesHabilistadas();
        
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
      
    // change
    public void verificarExistenciaHorario(){
        
        horarioTurma = horarioDao.procurarHorario(turmaEscolhida, semestre); 
        if(horarioTurma != null){
            // habilita op botão
            dataInicial = horarioTurma.getDataInicio();
            dataFinal = horarioTurma.getDataFim();
//            atualizarTabelaHorario();
        }else{
            //desabilita
            habilitarBuscar();
        }
    }
    
    public void usarUltimoHorarioTurma(){
        horarioTurma = horarioDao.procurarHorario(turmaEscolhida, semestre);
        
    }

    public PreReservaAula getReservaOpcional() {
        return reservaOpcional;
    }

    public void setReservaOpcional(PreReservaAula reservaOpcional) {
        this.reservaOpcional = reservaOpcional;
    }
   

    public boolean isIsDividido() {
        return isDividido;
    }

    public void setIsDividido(boolean isDividido) {
        this.isDividido = isDividido;
    }

    public void listarCursosHabilitados() {
        if (modalidadePesquisa != null) {
//            if(isAdm){
            setCursosHabilitados(cursoDAO.findByModalidade(modalidadePesquisa));
//            }else{
//                setCursosHabilitados(colaboradorDAO.getCursosParticipantes(modalidadePesquisa, colaboradorLogado));
//            }
        }

    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }
    
    

    public List<Turma> getTurmasHabilitadas() {
        return turmasHabilitadas;
    }

    public void setTurmasHabilitadas(List<Turma> turmasHabilitadas) {
        this.turmasHabilitadas = turmasHabilitadas;
    }
    
    
    
    public void listarTurmasHabilitados() {
        if (cursoEscolhido != null) {
//            if(isAdm){
            setTurmasHabilitadas(turmaDao.findByCurso(cursoEscolhido));
//            }else{
//                setCursosHabilitados(colaboradorDAO.getCursosParticipantes(modalidadePesquisa, colaboradorLogado));
//            }
        }

    }

    public Turma getTurmaEscolhida() {
        return turmaEscolhida;
    }

    public void setTurmaEscolhida(Turma turmaEscolhida) {
        this.turmaEscolhida = turmaEscolhida;
    }
    
    

    private void listarModalidadesHabilistadas() {
//        if (isAdm) {
        setModalidadesHabilitadas(modalidadeDAO.listar());
//        } else {
//            setModalidadesHabilitadas(colaboradorDAO.getModalidadesParticipante(colaboradorLogado));
//        }
    }

    public void habilitarBuscar() {
        if (cursoEscolhido != null) {
            setDisabledBuscar("");
        } else {
            setDisabledBuscar("disabled");
        }
    }

    public void habilitarGerarCalendario() {
        if (dataInicial != null && dataFinal != null) {
            if (dataFinal.isBefore(dataInicial)) {
                setMensagemDataInvalida("Período inválido! A data de início não pode ser maior que a de fim.");
                setDisabledBtCalendario("disabled");
            } else {
                setDisabledBtCalendario("");
                setMensagemDataInvalida("");
            }
        } else {
            setDisabledBtCalendario("disabled");
        }
    }

    public void trocarIcone() {
        if (iconBuscarTurma.equals("keyboard_arrow_down")) {
            setIconBuscarTurma("keyboard_arrow_left");
        } else {
            setIconBuscarTurma("keyboard_arrow_down");
        }
    }

    public void salvarPreReserva() {
        // Verificar se já existe no banco um semestre cadastrado para tal turma.
        // Integer id, Integer semestre, Boolean finalizado, Turma turma, List<ReservaAula> aulas, List<PreReservaAula> listaPreReservas)
       
        //Converte data selecionada em LocalDate
        String[] words = null;
        if(!dataString.equals("")) {
             words = dataString.split("/");
        }
        
        LocalDate myDate = LocalDate.parse(words[2] + "-" + words[1]+ "-" + words[0]);
        preReserva.setDataReserva(myDate);
       
        List<PreReservaAula> listaPreReserva; 
        //Seta informações nos objetos
        if(horarioTurma == null){
        horarioTurma = new HorarioTurma();
            listaPreReserva = new ArrayList<PreReservaAula>(); 
        }else{
            listaPreReserva = horarioTurma.getListaPreReservas();
        }
        
        horarioTurma.setFinalizado(false);
        horarioTurma.setSemestre(semestre); //tem que ser o semestre selecionado
        horarioTurma.setDataFim(dataFinal);
        horarioTurma.setDataInicio(dataInicial);
        horarioTurma.setTurma(turmaEscolhida);
        turmaEscolhida.getHorarios().add(horarioTurma);
        preReserva.setHorario(horarioTurma);
        if(isDividido) {
            reservaOpcional.setTipoPreReserva(TipoPreReserva.DIVIDIDO_SEGUNDO);
            reservaOpcional.setDataReserva(myDate);
            reservaOpcional.setHorario(horarioTurma);
            preReserva.setTipoPreReserva(TipoPreReserva.DIVIDIDO_PRIMEIRO);
            listaPreReserva.add(reservaOpcional);
        } else {
            preReserva.setTipoPreReserva(TipoPreReserva.NORMAL);
        }
        listaPreReserva.add(preReserva);
        
        horarioTurma.setListaPreReservas(listaPreReserva);
       
        horarioDao.salvar(horarioTurma);
    }
    
    public void criarNovoHorario(){  
        setMeses(construcaoHorarioController.criarMeses(dataInicial, dataFinal));
        horarioTurma = construcaoHorarioController.criarNovoHorario(turmaEscolhida, semestre, dataInicial, dataFinal);
        List<String> tabelaHorario = construcaoHorarioController.processarTabelaHorarioTurma(horarioTurma);
        preReservas = horarioTurma.getListaPreReservas();
        setDadosTabelaHorario(tabelaHorario);
    }
    
    public void adicionarPreReserva(){
        PreReservaAula preReservaAula = preReservas.get(indexSelecionado);
        if(!isDividido){
            construcaoHorarioController.adicionarPreReserva(preReservaAula, this.preReserva);
        }else{
            construcaoHorarioController.adicionarPreReservaDivida(preReserva, reservaOpcional, horarioTurma, indexSelecionado);
        }
        List<String> tabelaHorario = construcaoHorarioController.processarTabelaHorarioTurma(horarioTurma);
        setDadosTabelaHorario(tabelaHorario);
        limparFormReserva();
    }
//    public void atualizarTabelaHorario() {
//        setMeses(factory.getDivMeses(dataInicial, dataFinal));
//        setDadosTabelaHorario(factory.printarTabelaDados(dataInicial, dataFinal));
//        for (String td : dadosTabelaHorario) {
//            System.out.println(td);
//        }
//    }

    private void limparFormReserva() {
        this.preReserva.setProfessor(null);
        this.preReserva.setUc(null);
        this.preReserva.setSala(null);
    }

    public Colaborador getColaboradorLogado() {
        return colaboradorLogado;
    }

    public void setColaboradorLogado(Colaborador colaboradorLogado) {
        this.colaboradorLogado = colaboradorLogado;
    }

    public List<Modalidade> getModalidadesHabilitadas() {
        return modalidadesHabilitadas;
    }

    public void setModalidadesHabilitadas(List<Modalidade> modalidadesHabilitadas) {
        this.modalidadesHabilitadas = modalidadesHabilitadas;
    }

    public List<Curso> getCursosHabilitados() {
        return cursosHabilitados;
    }

    public void setCursosHabilitados(List<Curso> cursosHabilitados) {
        this.cursosHabilitados = cursosHabilitados;
    }

    public Modalidade getModalidadePesquisa() {
        return modalidadePesquisa;
    }

    public void setModalidadePesquisa(Modalidade modalidadePesquisa) {
        this.modalidadePesquisa = modalidadePesquisa;
    }

    public String getIconBuscarTurma() {
        return iconBuscarTurma;
    }

    public void setIconBuscarTurma(String iconBuscarTurma) {
        this.iconBuscarTurma = iconBuscarTurma;
    }

    public Curso getCursoEscolhido() {
        return cursoEscolhido;
    }

    public void setCursoEscolhido(Curso cursoEscolhido) {
        this.cursoEscolhido = cursoEscolhido;
    }

    public String getDisabledBuscar() {
        return disabledBuscar;
    }

    public void setDisabledBuscar(String disabledBuscar) {
        this.disabledBuscar = disabledBuscar;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public boolean isPrimeiraTabelaGerada() {
        return primeiraTabelaGerada;
    }

    public void setPrimeiraTabelaGerada(boolean primeiraTabelaGerada) {
        this.primeiraTabelaGerada = primeiraTabelaGerada;
    }

    public List<String> getDadosTabelaHorario() {
        return dadosTabelaHorario;
    }

    public void setDadosTabelaHorario(List<String> dadosTabelaHorario) {
        this.dadosTabelaHorario = dadosTabelaHorario;
    }

    public List<String> getMeses() {
        return meses;
    }

    public void setMeses(List<String> meses) {
        this.meses = meses;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getDisabledBtCalendario() {
        return disabledBtCalendario;
    }

    public void setDisabledBtCalendario(String disabledBtCalendario) {
        this.disabledBtCalendario = disabledBtCalendario;
    }

    public String getMensagemDataInvalida() {
        return mensagemDataInvalida;
    }

    public void setMensagemDataInvalida(String mensagemDataInvalida) {
        this.mensagemDataInvalida = mensagemDataInvalida;
    }

    public Integer getIndexSelecionado() {
        return indexSelecionado;
    }

    public void setIndexSelecionado(Integer indexSelecionado) {
        this.indexSelecionado = indexSelecionado;
    }

   
    public PreReservaAula getPreReserva() {
        return preReserva;
    }

    public void setPreReserva(PreReservaAula preReserva) {
        this.preReserva = preReserva;
    }

   
}
