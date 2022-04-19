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

@Entity(name = "dia_trabalho")
public class DiaTrabalho implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Enumerated()
    @Column(name = "horario", nullable = false)
    private HorarioAula horario;
    
    @Enumerated
    @Column(name = "disponibilidade_docente", nullable = false)
    private DisponibilidadeDocente disponibilidadeDocente;
    
    @Enumerated
    @Column(name = "dia", nullable = false)
    private DiaSemana dia;
    
    @ManyToOne()
    @JoinColumn(name = "id_colaborador")
    private Colaborador colaborador;

    public DiaTrabalho() {
    }
    

    public DiaTrabalho(HorarioAula horario, DisponibilidadeDocente disponibilidadeDocente) {
        this.horario = horario;
        this.disponibilidadeDocente = disponibilidadeDocente;
    }

    public DiaTrabalho(HorarioAula horario, DisponibilidadeDocente disponibilidadeDocente, DiaSemana dia, Colaborador colaborador) {
        this.horario = horario;
        this.disponibilidadeDocente = disponibilidadeDocente;
        this.dia = dia;
        this.colaborador = colaborador;
    }
    
    

    public DisponibilidadeDocente getDisponibilidadeDocente() {
        return disponibilidadeDocente;
    }

    public void setDisponibilidadeDocente(DisponibilidadeDocente disponibilidadeDocente) {
        this.disponibilidadeDocente = disponibilidadeDocente;
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

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
    
    
    
}
