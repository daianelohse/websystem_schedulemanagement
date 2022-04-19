package model.entidade;

public enum DisponibilidadeDocente {
    
    DISPONIVEL("Disponível"),PREFERENCIAL("Preferencial"), INDISPONIVEL("Indisponível") ;
    private String descricao;
    
    private DisponibilidadeDocente(String desc){
        this.descricao = desc;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
}
