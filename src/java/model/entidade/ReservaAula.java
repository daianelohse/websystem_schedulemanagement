package model.entidade;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "reserva_aula")
@DiscriminatorValue("AULA")
public class ReservaAula extends Reserva implements Serializable{
    
    @Column(name = "atividade", columnDefinition = "TEXT")
    private String atividade;
    
    @ManyToOne()
    @JoinColumn(name = "id_turma")
    private HorarioTurma horarioTurma;
    
    @ManyToOne()
    @JoinColumn(name = "id_unidade_curricular")
    private UnidadeCurricular unidadeCurricular;

    public ReservaAula() {
    }

    public ReservaAula(String atividade, HorarioTurma turma, UnidadeCurricular uc, LocalDate data, HorarioAula horarioAula, LocalDate dataSolicitacao, Boolean reservaOk, Ambiente ambiente, Colaborador colaborador) {
        super(atividade, null, null, data, horarioAula, dataSolicitacao, reservaOk, ambiente, colaborador);
        this.atividade = atividade;
        this.horarioTurma = turma;
        this.unidadeCurricular = uc;
    }

    public UnidadeCurricular getUc() {
        return unidadeCurricular;
    }

    public void setUc(UnidadeCurricular uc) {
        this.unidadeCurricular = uc;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public HorarioTurma getTurma() {
        return horarioTurma;
    }

    public void setTurma(HorarioTurma turma) {
        this.horarioTurma = turma;
    }

}
