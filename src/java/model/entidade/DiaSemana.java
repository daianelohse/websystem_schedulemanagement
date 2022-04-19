package model.entidade;

public enum DiaSemana {
    
    SEGUNDA("segunda"), TERCA("terca"), QUARTA("quarta"), QUINTA("quinta"), SEXTA("sexta"), SABADO("sabado");
    
    private String descricao;
    
    private DiaSemana(String desc){
        this.descricao = desc;
    }

    public String getDescricao() {
        return descricao;
    }
}
