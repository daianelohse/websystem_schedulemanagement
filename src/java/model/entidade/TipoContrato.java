package model.entidade;

public enum TipoContrato {
    
    MENSALISTA("Mensalista"), HORISTA("Horista");
    
    private String descricao;
    
    private TipoContrato(String desc){
        descricao = desc;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
    
}
