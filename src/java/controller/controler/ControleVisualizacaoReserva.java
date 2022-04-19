package controller.controler;

import controller.dao.ReservaDAO;
import java.util.ArrayList;
import java.util.List;
import model.entidade.ReservaEvento;
import model.entidade.SituacaoReserva;
import model.entidade.VisualizacaoReservaTableModel;

public class ControleVisualizacaoReserva {
    
    private ReservaDAO reservaDAO;

    public ControleVisualizacaoReserva() {
        reservaDAO = new ReservaDAO();
    }
    
    
    public List<VisualizacaoReservaTableModel> filtrarLista(List<VisualizacaoReservaTableModel> lista, SituacaoReserva situacao) {
        List<VisualizacaoReservaTableModel> retorno = new ArrayList<>();
        for (VisualizacaoReservaTableModel visualizacaoReservaTableModel : lista) {
            if (visualizacaoReservaTableModel.getReserva().getSituacaoReserva()
                    == situacao) {
                retorno.add(visualizacaoReservaTableModel);
            }
        }
        return retorno;
    }

    private List<VisualizacaoReservaTableModel> construirModel(List<ReservaEvento> itens) {
        List<VisualizacaoReservaTableModel> retorno = new ArrayList<>();
        for (ReservaEvento reserva : itens) {
            retorno.add(new VisualizacaoReservaTableModel(reserva));
        }
        return retorno;
    }
    
   

    public List<VisualizacaoReservaTableModel> buscarTodasReservas() {
        List<ReservaEvento> reservas = reservaDAO.listarReservaEvento();
        return construirModel(reservas);
    }

}
