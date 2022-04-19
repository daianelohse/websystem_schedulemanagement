package view;

import controller.controler.ControleVisualizacaoReserva;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.entidade.VisualizacaoReservaTableModel;

@ManagedBean
public class VisualizacaoReservaBean {

    private List<VisualizacaoReservaTableModel> items = new ArrayList<VisualizacaoReservaTableModel>();
    private VisualizacaoReservaTableModel modelo = new VisualizacaoReservaTableModel();
    private ControleVisualizacaoReserva controleVisualizacao;
    private Integer paginaAtual = 1;

    public VisualizacaoReservaBean() {
        controleVisualizacao = new ControleVisualizacaoReserva();
        items = controleVisualizacao.buscarTodasReservas();
    }

    public List<VisualizacaoReservaTableModel> getItems() {
        return items;
    }

    public void setItems(List<VisualizacaoReservaTableModel> items) {
        this.items = items;
    }

    public VisualizacaoReservaTableModel getModelo() {
        return modelo;
    }

    public void atualizarListaConfirmada(int numeroPagina) {
        this.paginaAtual = numeroPagina;
    }

}
