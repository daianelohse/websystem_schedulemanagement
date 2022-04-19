/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controler;

import controller.dao.PreReservaAulaDAO;
import java.time.LocalDate;
import java.util.List;
import model.entidade.PreReservaAula;
import model.entidade.TipoPreReserva;
import model.entidade.Turma;
import util.ConexaoUtil;
import util.ValidatorUtil;

/**
 *
 * @author Pc - Floripa
 */
public class ControlePreReservaAula {
    private PreReservaAulaDAO preReservaDAO;
    private ValidatorUtil validatorUtil;
    private ConexaoUtil conexaoUtil = new ConexaoUtil();

    public ControlePreReservaAula() {
        preReservaDAO = new PreReservaAulaDAO();
        validatorUtil = new ValidatorUtil();
    }
   
     public List<PreReservaAula> listar(LocalDate dataInicial, LocalDate dataFinal){
        List<PreReservaAula> reservas = preReservaDAO.listar(dataInicial, dataFinal);
        if(validatorUtil.validarList(reservas))
            return reservas;
        return null;
    }
}
