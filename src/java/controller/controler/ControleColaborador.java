
package controller.controler;

import controller.dao.ColaboradorDAO;
import model.entidade.Colaborador;

public class ControleColaborador {
    
    private ColaboradorDAO colaboradorDAO;

    public ControleColaborador() {
        colaboradorDAO =  new ColaboradorDAO();
    }
    
    public boolean cadastrarColaborador(Colaborador colaborador){
        return colaboradorDAO.salvar(colaborador);
    }
    
    
}
