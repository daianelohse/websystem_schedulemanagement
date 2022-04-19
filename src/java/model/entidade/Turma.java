package model.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "turma")
public class Turma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "numero", nullable = false)
    private Long numero;

    @Column(name = "semestre")
    private Integer semestre;

    @Enumerated
    @Column(name = "turno", nullable = false)
    private Turno turno;

    @ManyToOne()
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @OneToMany(mappedBy = "turma")
    private List<PeriodoAula> cronogramaAulas;

    @OneToMany(mappedBy = "turma")
    private List<HorarioTurma> horarios = new ArrayList<>();

    public Turma() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<PeriodoAula> getCronogramaAulas() {
        return cronogramaAulas;
    }

    public void setCronogramaAulas(List<PeriodoAula> cronogramaAulas) {
        this.cronogramaAulas = cronogramaAulas;
    }

    public List<HorarioTurma> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioTurma> horarios) {
        this.horarios = horarios;
    }

    public String getSigla() {
        String sigla = "";
        String ano = this.ano != null ? Integer.toString(this.ano) : "";
        String numero = this.numero != null ? Long.toString(this.numero) : "";
        if (this.curso != null) {
            sigla += curso.getModalidade().getSigla() + " "
                    + curso.getSigla();
        }
        if (this.numero != null) {
            sigla += " " + ano;
        }
        if (this.semestre != null) {
            sigla += "/" + semestre;
        }

        if (this.turno != null) {
            sigla += " " + turno.getSigla() + numero;
        }
        System.out.println("Ano: " + ano);
        // AI AICMR 2015/1 V1
        return sigla;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Turma other = (Turma) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
