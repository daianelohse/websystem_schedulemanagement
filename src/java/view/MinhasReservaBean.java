package view;

import controller.dao.ColaboradorDAO;
import controller.dao.ReservaDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.entidade.Ambiente;
import model.entidade.Colaborador;
import model.entidade.ReservaEvento;
import model.entidade.SituacaoReserva;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Nicolas
 */
@ManagedBean
@ViewScoped
public class MinhasReservaBean {

    private ReservaDAO reservaDAO = new ReservaDAO();

    private List<ReservaEvento> reservas = new ArrayList<ReservaEvento>();

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Colaborador colaboradorLogado;
    ColaboradorDAO colaboradorDAO = new ColaboradorDAO();

    public MinhasReservaBean() {
        colaboradorLogado = colaboradorDAO.findByUserName(auth.getName());
        reservas = reservaDAO.buscarReservaPorUsuario(colaboradorLogado);
    }

    public String formatarData(LocalDate data) {
        if (data != null) {
            return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(data);
        } else {
            return "";
        }
    }

    public List<ReservaEvento> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEvento> reservas) {
        this.reservas = reservas;
    }

    public String getTextoAmbiente(ReservaEvento reserva) {
        if (reserva.getSituacaoReserva() == SituacaoReserva.EDITADA) {
            return reserva.getAmbienteAnterior().getSigla() + " para " + reserva.getAmbiente().getSigla();
        }
        return reserva.getAmbiente().getSigla();
    }

    public void aceitar(ReservaEvento reserva) {
        List<ReservaEvento> reservas = reservaDAO.buscarReservasComHorarios(reserva);
        for (ReservaEvento reservaEvento : reservas) {
            reservaEvento.setReservaOk(Boolean.TRUE);
            reservaEvento.setSituacaoReserva(SituacaoReserva.CONFIRMADA);
            reservaDAO.salvar(reservaEvento);
        }

    }

    public void negar(ReservaEvento reserva) {
        List<ReservaEvento> reservas = reservaDAO.buscarReservasComHorarios(reserva);
        for (ReservaEvento reservaEvento : reservas) {
            reservaEvento.setReservaOk(Boolean.TRUE);
            reservaEvento.setSituacaoReserva(SituacaoReserva.NEGACAO_NEGADA);
            Ambiente a = reserva.getAmbiente();
            reserva.setAmbiente(reserva.getAmbienteAnterior());
            reserva.setAmbienteAnterior(a);
            reservaDAO.salvar(reservaEvento);
        }
    }

    public boolean isHabilitado(ReservaEvento reserva) {
        return reserva.getSituacaoReserva() == SituacaoReserva.NEGACAO_SOLICITADA
                || reserva.getSituacaoReserva() == SituacaoReserva.EDITADA;
    }

}
