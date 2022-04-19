package model.entidade;

public enum Area {
    ADM("Administrativo"), EDU("Educacional");
    
    private String descricao;
    private Area(String desc){
        this.descricao = desc;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
    
    
}
