package model.entidade;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "reserva_evento")
@DiscriminatorValue("EVENTO")
public class ReservaEvento extends Reserva implements Serializable{
    
    @Column(name = "nome", length = 150)
    private String nome;
    
    @Column(name = "informacao_adicional", columnDefinition = "TEXT")
    private String informacoesAdicionais;

    public ReservaEvento() {
    }

    public ReservaEvento(String nome, String informacoesAdicionais, LocalDate dataReserva, SituacaoReserva situacaoReserva) {
        super(dataReserva, situacaoReserva);
        this.nome = nome;
        this.informacoesAdicionais = informacoesAdicionais;
    }
     
    public ReservaEvento(String nome, String informacoesAdicionais, LocalDate data, HorarioAula horarioAula, LocalDate dataSolicitacao, Boolean reservaOk, Ambiente ambiente, Colaborador colaborador) {
        super(nome, null, null, data, horarioAula, dataSolicitacao, reservaOk, ambiente, colaborador);
        this.nome = nome;
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
