package controller.controler;

import controller.dao.AmbienteDAO;
import controller.dao.ReservaDAO;
import java.util.Date;
import java.util.List;
import model.entidade.Ambiente;
import model.entidade.Localizacao;
import model.entidade.Reserva;
import model.entidade.SituacaoAmbiente;
import model.entidade.TipoAmbiente;
import util.ValidatorUtil;

public class ControleAmbiente {

    private AmbienteDAO ambienteDAO;
    private ReservaDAO reservaDAO;
    private ValidatorUtil validatorUtil;

    public ControleAmbiente() {
        ambienteDAO = new AmbienteDAO();
        reservaDAO = new ReservaDAO();
        validatorUtil = new ValidatorUtil();
    }

    public boolean cadastrarAmbiente(Ambiente ambiente) {
        if (ambiente != null) {
            return ambienteDAO.salvar(ambiente);
        } else {
            throw new NullPointerException("O ambiente está nulo!");
        }

    }

    public boolean cadastrarAmbiente(String bloco, Integer numero, String divisao, Integer andar, Integer capacidade, String observacao, SituacaoAmbiente situacao, TipoAmbiente tipoAmbiente, Localizacao localizacao) {
         Ambiente ambiente = new Ambiente(bloco, numero, divisao, andar, capacidade, observacao, situacao, tipoAmbiente, localizacao);
         return cadastrarAmbiente(ambiente);
    }
    
    public boolean removerAmbiente(Ambiente ambiente) {
        if (ambiente != null) {
            return ambienteDAO.remover(ambiente);
        }else{
            throw new NullPointerException("O ambiente à ser excluido está nulo! "
                    + "\n Método: removerAmbiente() "
                    + "\n Classe: controller.controller.ControleAmbiente");
        }
    }
    
    public List<Ambiente> listar(){
        List<Ambiente> ambientes = ambienteDAO.listar();
        if(validatorUtil.validarList(ambientes))
            return ambientes;
        return null;
    }
    
    public Ambiente buscarAmbiente(Integer id){
        return ambienteDAO.findById(id);
    }
    
    public List<Ambiente> buscarAmbientesPorTipo(TipoAmbiente tipoAmbiente){
        List<Ambiente> list = ambienteDAO.findByTipoAmbiente(tipoAmbiente);
        if(validatorUtil.validarList(list)){
            return list;
        }
        return null;
    }
    
    public List<Reserva> buscarReservaAmbiente(Ambiente ambiente, Date dataInicio, Date dataFinal){
        List<Reserva> lista = reservaDAO.buscarReservaAmbiente(ambiente, dataInicio, dataFinal);
        if(validatorUtil.validarList(lista)){
            return lista;
        }
        return null;
    }

}
