package model.entidade;

public enum SituacaoAmbiente {

    ATIVO("Ativo"), MANUTENCAO("Manutenção"), INATIVO("Inativo");

    private String situacao;

    SituacaoAmbiente(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }
    
    

}
