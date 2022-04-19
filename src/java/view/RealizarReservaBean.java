package view;

import controller.controler.ControleAmbiente;
import controller.controler.ControleReserva;
import controller.dao.ColaboradorDAO;
import controller.dao.LocalizacaoDAO;
import controller.dao.ReservaDAO;
import controller.dao.TipoAmbienteDAO;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.entidade.Ambiente;
import model.entidade.HorarioAula;
import model.entidade.Localizacao;
import model.entidade.ReservaEvento;
import model.entidade.SituacaoReserva;
import model.entidade.TipoAmbiente;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean
@ViewScoped
public class RealizarReservaBean implements Serializable {

    private String titulo = "";
    private ReservaEvento reserva = new ReservaEvento();
    private ReservaDAO dao = new ReservaDAO();
    private ColaboradorDAO colaboradorDAO = new ColaboradorDAO();

    private TipoAmbienteDAO tipoAmbienteDao = new TipoAmbienteDAO();
    private LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();

    private ControleAmbiente controleAmbiente = new ControleAmbiente();
    private ControleReserva controleReserva = new ControleReserva();

    private List<Ambiente> ambientesLivres = new ArrayList<Ambiente>();
    private List<TipoAmbiente> tiposAmbiente = new ArrayList<TipoAmbiente>();
    private List<Localizacao> localizacoes = new ArrayList<Localizacao>();
    private Map<HorarioAula, Boolean> horariosSelecionados = new LinkedHashMap<HorarioAula, Boolean>();

    private Localizacao localizacaoSelecionada = new Localizacao();
    private TipoAmbiente tipoAmbienteSelecionado = new TipoAmbiente();
    private Ambiente ambiente = new Ambiente();

    private LocalDate dataReserva = LocalDate.now();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    private String mensagem = "";
    private boolean localSelecionado = false;

    public ReservaEvento getReserva() {
        return reserva;
    }

    public RealizarReservaBean() {
        localizacoes = localizacaoDAO.listarOrdenadorPorNome();
        //tiposAmbiente = tipoAmbienteDao.listarOrdenadoPorNome();
        for (HorarioAula horario : HorarioAula.values()) {
            horariosSelecionados.put(horario, Boolean.FALSE);
        }
        setAmbientes(new ArrayList<>());
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Ambiente> getAmbientes() {
        return ambientesLivres;
    }

    public void setAmbientes(List<Ambiente> ambientes) {
        this.ambientesLivres = ambientes;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void atualizarComboTipoAmbiente() {
        if (localizacaoSelecionada.getId() != null) {
            setTiposAmbiente(controleReserva.buscarTiposByLocalidade(localizacaoSelecionada));
            setLocalSelecionado(true);
        } else {
            setTiposAmbiente(tipoAmbienteDao.listarOrdenadoPorNome());
            setLocalSelecionado(false);
        }
        limparListaAmbientes();
        limparHorariosSelecionados();
        limparDataSelecionada();
    }

    public List<TipoAmbiente> getTiposAmbiente() {
        return tiposAmbiente;
    }

    //Retorno string
    public void addReserva() {
        if (reserva.getAmbiente() != null) {
            List<ReservaEvento> reservas = new ArrayList<ReservaEvento>();
            reserva.setDataReserva(dataReserva);
            reserva.setDataSolicitacao(LocalDate.now());
            reserva.setColaborador(colaboradorDAO.findByUserName(auth.getName()));
            for (HorarioAula horario : getHorariosPesquisa()) {
                ReservaEvento r = new ReservaEvento();
                r.setDataReserva(reserva.getDataReserva());
                r.setDataSolicitacao(reserva.getDataSolicitacao());
                r.setDescricao(reserva.getDescricao());
                r.setHorarioAula(horario);
                r.setNome(reserva.getNome());
                r.setReservaOk(false);
                r.setAmbiente(reserva.getAmbiente());
                r.setColaborador(reserva.getColaborador());
                r.setDataSolicitacao(LocalDate.now());
                r.setSituacaoReserva(SituacaoReserva.A_CONFIRMAR);
                 reservas.add(r);
            }
            boolean reservaOK = true;
            for (ReservaEvento reservaEvento : reservas) {
                reservaOK = dao.salvar(reservaEvento);
            }
            if (reservaOK) {
                setMensagem("Sucesso");
            } else {
                setMensagem("Erro");
            }
            reserva = new ReservaEvento();
            dataReserva = null;
            ambientesLivres = new ArrayList<>();
            for (HorarioAula horarioAula : horariosSelecionados.keySet()) {
                horariosSelecionados.replace(horarioAula, Boolean.FALSE);
            }
            localizacaoSelecionada = null;
            tipoAmbienteSelecionado = null;
            ambiente = null;
        } else {
            setMensagem("Escolha um ambiente para realizar reserva!");
        }

        // return "visualizacaoReservas";
    }

    public List<Localizacao> getLocalizacoes() {
        return localizacoes;
    }

    public void setTiposAmbiente(List<TipoAmbiente> tiposAmbiente) {
        this.tiposAmbiente = tiposAmbiente;
    }

    public Localizacao getLocalizacaoSelecionada() {
        return localizacaoSelecionada;
    }

    public void setLocalizacaoSelecionada(Localizacao localizacaoSelecionada) {
        this.localizacaoSelecionada = localizacaoSelecionada;
    }

    public TipoAmbiente getTipoAmbienteSelecionado() {
        return tipoAmbienteSelecionado;
    }

    public void setTipoAmbienteSelecionado(TipoAmbiente tipoAmbienteSelecionado) {
        this.tipoAmbienteSelecionado = tipoAmbienteSelecionado;
    }

    public void buscarAmbientesLivres() {
        if (getHorariosPesquisa() != null && !getHorariosPesquisa().isEmpty() && dataReserva != null) {
            setAmbientes(controleReserva.buscarAmbientesLivres(dataReserva,
                    tipoAmbienteSelecionado,
                    localizacaoSelecionada,
                    getHorariosPesquisa()));
        } else {
            setAmbientes(new ArrayList<>());
            if (dataReserva == null) {
                for (HorarioAula horarioAula : horariosSelecionados.keySet()) {
                    horariosSelecionados.replace(horarioAula, Boolean.FALSE);
                }
            }
        }

    }

    public void limparListaAmbientes() {
        //  setAmbientes(null);
        this.ambientesLivres = null;
    }

    public void limparDataSelecionada() {
        //  setAmbientes(null);
        this.dataReserva = null;
    }

    public void limparHorariosSelecionados() {
        //  setAmbientes(null);
        for (HorarioAula horarioAula : horariosSelecionados.keySet()) {
            horariosSelecionados.replace(horarioAula, Boolean.FALSE);
            //horariosSelecionados.put(horarioAula,Boolean.FALSE);
        }
    }

    private List<HorarioAula> getHorariosPesquisa() {
        List<HorarioAula> retorno = new ArrayList<>();
        for (HorarioAula horarioAula : HorarioAula.values()) {
            if (horariosSelecionados.get(horarioAula) == true) {
                retorno.add(horarioAula);
            }
        }
        return retorno;
    }

    public Map<HorarioAula, Boolean> getHorariosSelecionados() {
        return horariosSelecionados;
    }

    public void setHorariosSelecionados(Map<HorarioAula, Boolean> horariosSelecionados) {
        this.horariosSelecionados = horariosSelecionados;
    }

    public void setReserva(ReservaEvento reserva) {
        this.reserva = reserva;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isLocalSelecionado() {
        return localSelecionado;
    }

    public void setLocalSelecionado(boolean localSelecionado) {
        this.localSelecionado = localSelecionado;
    }

}
