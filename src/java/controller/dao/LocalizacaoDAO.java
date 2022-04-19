
package controller.dao;

import java.util.List;
import model.entidade.Localizacao;

public class LocalizacaoDAO {
    
    private GenericDAO genericDAO;
    public LocalizacaoDAO() {
        genericDAO = new GenericDAO<>();
    }
    
    public boolean salvar(Localizacao localizacao){
        if(localizacao.getId() == null){
            return genericDAO.insert(localizacao);
        }else{
            return genericDAO.update(localizacao);
        }
    }
    
    public boolean remover(LocalizacaoDAO localizacao){
        return genericDAO.remove(localizacao);
    }
    
    public List<Localizacao> listar(){
        return genericDAO.getEm().createQuery("select l from localizacao l").getResultList();
    }
    
    public List<Localizacao> listarOrdenadorPorNome(){
        return genericDAO.getEm().createQuery("select l from localizacao l ORDER BY l.nome ASC").getResultList();
    }
}
