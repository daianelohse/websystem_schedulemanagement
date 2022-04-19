package view;

import controller.dao.ReservaDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import model.entidade.Reserva;
import model.entidade.ReservaAula;
import model.entidade.ReservaEvento;
import model.entidade.SituacaoReserva;

/**
 *
 * @author Nicolas
 */
@ManagedBean
public class ControleReservaBean {
    
    private ReservaDAO reservaDAO = new ReservaDAO();
    
    private List<ReservaEvento> reservas = new ArrayList<ReservaEvento>();
    
    private Integer idEditar;
    private Integer idExcluir;
    
    private ReservaEvento reservaEdit;
    private ReservaEvento reservaExcluir;

    public ControleReservaBean() {
        reservas = reservaDAO.listarReservaEvento();
    }
    
    public String formatarData(LocalDate data){
        if(data != null){
            return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(data);
        }else{
            return "";
        }
    }
    
    public void confirmar(ReservaEvento reserva){
        reserva.setSituacaoReserva(SituacaoReserva.CONFIRMADA);
        reserva.setReservaOk(Boolean.TRUE);
        reservaDAO.atualizar(reserva);  
    }
    
     public String editar(ReservaEvento reserva){
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservaEdit", reserva);
        return "editarReserva.jsf?faces-redirect=true";  
    }
    
    public String podeFazerAcao(ReservaEvento reservaEvento){
        return reservaEvento.getSituacaoReserva() != SituacaoReserva.A_CONFIRMAR ? "disabled" : "";
    }
    
    public boolean isHabilitado(ReservaEvento reservaEvento){
        return !podeFazerAcao(reservaEvento).equals("");
    }

    public Integer getIdEditar() {
        return idEditar;
    }

    public void setIdEditar(Integer idEditar) {
        this.idEditar = idEditar;
    }

    public Integer getIdExcluir() {
        return idExcluir;
    }

    public void setIdExcluir(Integer idExcluir) {
        this.idExcluir = idExcluir;
    }
    
    
    public List<ReservaEvento> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEvento> reservas) {
        this.reservas = reservas;
    }
    
    public ReservaEvento getReservaEdit(){
        if(reservaEdit == null){
            reservaEdit = reservaDAO.findById(idEditar);
        }
        return reservaEdit;
    }
    
    public ReservaEvento getReservaExcluir(){
        if(reservaExcluir == null){
            reservaExcluir = reservaDAO.findById(idExcluir);
        }
        return reservaExcluir;
    }
}
