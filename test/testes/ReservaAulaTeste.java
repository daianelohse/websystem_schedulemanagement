/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testes;

import controller.dao.AmbienteDAO;
import controller.dao.ColaboradorDAO;
import controller.dao.ReservaAulaDAO;
import controller.dao.UnidadeCurricularDAO;
import java.time.LocalDate;
import model.entidade.ReservaAula;
import org.junit.Test;


public class ReservaAulaTeste {
    @Test
    public void test(){
        AmbienteDAO ambienteDAO  = new AmbienteDAO();
        UnidadeCurricularDAO ucdao = new UnidadeCurricularDAO();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        
        ReservaAula ra = new ReservaAula();
        
        ra.setAmbiente(ambienteDAO.findById(5));
        ra.setColaborador(colaboradorDAO.findById(2));
        ra.setUc(ucdao.findById(1));
        
        ra.setDataReserva(LocalDate.now());
        
        ReservaAulaDAO reservaAulaDAO = new ReservaAulaDAO();
        reservaAulaDAO.salvar(ra);
        
    }
}
