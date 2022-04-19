package model.entidade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VisualizacaoReservaTableModel {
    
    private String data;
    private String titulo;
    private String informacoesReserva;
    private String situacao;
    private Reserva reserva;
    public VisualizacaoReservaTableModel() {
    }

    
    
    public VisualizacaoReservaTableModel(ReservaEvento reserva) {
        try{
        data = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(reserva.getDataReserva());
        }catch(NullPointerException ex){
            data = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        }
        titulo = reserva.getNome();
        informacoesReserva = reserva.getInformacoesAdicionais();
        situacao = reserva.getSituacaoReserva().getText();
        this.reserva =  reserva;
    }

    public String getData() {
        return data;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getInformacoesReserva() {
        return informacoesReserva;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setInformacoesReserva(String informacoesReserva) {
        this.informacoesReserva = informacoesReserva;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    
    
    
}
