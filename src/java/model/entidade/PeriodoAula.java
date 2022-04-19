package model.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "periodo_aula")
public class PeriodoAula implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Enumerated
    @Column(name = "horario", nullable = false)
    private HorarioAula horario;
    
    @Enumerated
    @Column(name = "dia", nullable = false)
    private DiaSemana dia;
    
    @ManyToOne()
    @JoinColumn(name = "id_turma")
    private Turma turma;

    public PeriodoAula() {
    }

    public PeriodoAula(HorarioAula horario, DiaSemana dia, Turma turma) {
        this.horario = horario;
        this.dia = dia;
        this.turma = turma;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HorarioAula getHorario() {
        return horario;
    }

    public void setHorario(HorarioAula horario) {
        this.horario = horario;
    }

    public DiaSemana getDia() {
        return dia;
    }

    public void setDia(DiaSemana dia) {
        this.dia = dia;
    }
    
    
    
}
