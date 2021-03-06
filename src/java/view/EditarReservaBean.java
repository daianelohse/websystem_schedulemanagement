package view;

import controller.controler.ControleReserva;
import controller.dao.ReservaDAO;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.entidade.Ambiente;
import model.entidade.HorarioAula;
import model.entidade.Reserva;
import model.entidade.ReservaEvento;
import model.entidade.SituacaoReserva;

@ManagedBean
@ViewScoped
public class EditarReservaBean implements Serializable {

    private ReservaEvento reserva;
    private Ambiente ambienteSelecionado = new Ambiente();
    private ReservaDAO reservaDAO = new ReservaDAO();
    List<Ambiente> ambientesDisponÃ­veis = new ArrayList<Ambiente>();
    ControleReserva controleReservaBean = new ControleReserva();
    List<ReservaEvento> reservas;

    public EditarReservaBean() {
        ReservaEvento a = (ReservaEvento) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("reservaEdit");
        if (a != null) {
            reserva = a;
        }
        this.reserva = reserva;
        reservas = reservaDAO.buscarReservasComHorarios(this.reserva);
        ambientesDisponÃ­veis = controleReservaBean.buscarAmbientesLivres(reserva.getDataReserva(),
                reserva.getAmbiente().getTipoAmbiente(), reserva.getAmbiente().getLocalizacao(),
                getHorariosAula(reservas));
    }

    public ReservaEvento getReserva() {
        return reserva;
    }

    public void setReserva(ReservaEvento reserva) {
        this.reserva = reserva;
    }

    public String formatarData(LocalDate data) {
        if (data != null) {
            return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(data);
        } else {
            return "";
        }
    }

    public String getHorarios() {
        String horarios = "";

        for (ReservaEvento reservaEvento : reservas) {
            horarios += reservaEvento.getHorarioAula().getDescricao();
            horarios += ", ";
        }
        return horarios.substring(0, horarios.length() - 2);
    }

    public String getMensagem() {
        if (ambientesDisponÃ­veis.isEmpty()) {
            return "NÃ£o Ã© possÃ­vel editar esta reserva, pois nÃ£o hÃ¡ ambientes disponÃ­veis. Caso precise deste ambiente,"
                    + " envie ao solicitante uma requisiÃ§Ã£o de cancelamento de reserva.";
        } else {
            return "Selecione um ambiente e informe o motivo da ediÃ§Ã£o.";
        }
    }

    public String getCorTexto() {
        if (ambientesDisponÃ­veis.isEmpty()) {
            return "red-text text-darken-2";
        } else {
            return "amber-text text-darken-4";
        }
    }

    public boolean isSelecaoAmbienteHabilitada() {
        return !this.ambientesDisponÃ­veis.isEmpty();
    }
    
    public String habilitarSalvar(){
        return isSelecaoAmbienteHabilitada() ? "" : "disabled";
    }

    private List<HorarioAula> getHorariosAula(List<ReservaEvento> reservas) {
        List<HorarioAula> horarioAulas = new ArrayList<>();
        for (Reserva reserva : reservas) {
            horarioAulas.add(reserva.getHorarioAula());
        }
        return horarioAulas;
    }

    public String enviarSolicitacao() {
        reserva.setSituacaoReserva(SituacaoReserva.NEGACAO_SOLICITADA);
        reservaDAO.salvar(reserva);
        return "controleReserva.jsf?faces-redirect=true";
    }
    public String salvar() {
        
       
        for (ReservaEvento reserva : reservas) {
            reserva.setSituacaoReserva(SituacaoReserva.EDITADA);
            reserva.setAmbienteAnterior(reserva.getAmbiente());
             reserva.setAmbiente(ambienteSelecionado);
             reservaDAO.salvar(reserva);
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().remove("reservaEdit");
        return "controleReserva.jsf?faces-redirect=true";
    }

    public Ambiente getAmbienteSelecionado() {
        return ambienteSelecionado;
    }

    public void setAmbienteSelecionado(Ambiente ambienteSelecionado) {
        this.ambienteSelecionado = ambienteSelecionado;
    }

    public List<Ambiente> getAmbientesDisponÃ­veis() {
        return ambientesDisponÃ­veis;
    }

    public void setAmbientesDisponÃ­veis(List<Ambiente> ambientesDisponÃ­veis) {
        this.ambientesDisponÃ­veis = ambientesDisponÃ­veis;
    }

}
