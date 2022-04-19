/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entidade;

/**
 *
 * @author Nicolas
 */
public enum SituacaoReserva {
    
    CONFIRMADA("Confirmada"), EDITADA("Editada"), A_CONFIRMAR("Á confirmar"), NEGACAO_SOLICITADA("Negação solicitada"),
    NEGACAO_NEGADA("Negação de reserva negada");
    private String text;
    
    SituacaoReserva(String text){
       this.text = text;
    }

    public String getText() {
        return text;
    }
    
    
}
