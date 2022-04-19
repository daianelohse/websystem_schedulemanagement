/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.controler;

import controller.dao.LocalizacaoDAO;
import java.util.List;
import model.entidade.Localizacao;
import util.ValidatorUtil;

/**
 *
 * @author Nicolas
 */
public class ControleLocalizacao {
    
    private LocalizacaoDAO localizacaoDAO;
    private ValidatorUtil validatorUtil;

    public ControleLocalizacao() {
        localizacaoDAO = new LocalizacaoDAO();
        validatorUtil = new ValidatorUtil();
    }

    public boolean cadastrarAmbiente(Localizacao localizacao) {
        if (localizacao != null) {
            return localizacaoDAO.salvar(localizacao);
        } else {
            throw new NullPointerException("O ambiente está nulo!");
        }

    }
    
    public List<Localizacao> listar(){
        List<Localizacao> locais = localizacaoDAO.listar();
       // if(validatorUtil.validarList(locais))
            return locais;
       // return null;
    }
//    
//    public Localizacao buscarAmbiente(Integer id){
//        return localizacaoDAO.findById(id);
//    }
//    
//    public List<Localizacao> buscarAmbientesPorTipo(Localizacao tipoAmbiente){
//        List<Localizacao> list = localizacaoDAO.findByTipoAmbiente(tipoAmbiente);
//        if(validatorUtil.validarList(list)){
//            return list;
//        }
//        return null;
//    }
  
}
