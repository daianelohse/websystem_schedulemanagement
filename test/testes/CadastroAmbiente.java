/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testes;

import controller.controler.ControleAmbiente;
import static junit.framework.Assert.*;
import model.entidade.Ambiente;
import model.entidade.Localizacao;
import model.entidade.SituacaoAmbiente;
import model.entidade.TipoAmbiente;
import org.junit.Test;

/**
 *
 * @author Nicolas
 */
public class CadastroAmbiente {
    
    ControleAmbiente controle;

    public CadastroAmbiente() {
        controle = new ControleAmbiente();
    }
    
    
   @Test 
    public void teste1(){
        Localizacao senai = new Localizacao("SENAI");
        TipoAmbiente sala = new TipoAmbiente("Sala", "SALA", "");
        
        Ambiente a01 = new Ambiente();
        a01.setLocalizacao(senai);
        a01.setTipoAmbiente(sala);
        a01.setNumero(1);
        a01.setSituacao(SituacaoAmbiente.ATIVO);
        a01.setBloco("A");
        a01.setAndar(1);
        a01.setCapacidade(40);
        a01.setObservacao("Sala com prioridade ao Superior");
        
        assertTrue(controle.cadastrarAmbiente(a01));
    }
}
