package model.entidade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "horario_turma")
public class HorarioTurma implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "semestre")
    private Integer semestre;
    
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    
    @Column(name = "data_fim")
    private LocalDate dataFim;
    
    @Column(name = "is_finalizado", columnDefinition = "boolean default true")
    private Boolean finalizado;
    
    @ManyToOne()
    @JoinColumn(name = "id_turma")
    private Turma turma;
    
    @OneToMany(mappedBy = "horarioTurma")
    private List<ReservaAula> aulas;
    
    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL)
    private List<PreReservaAula> listaPreReservas;
    
    @Column(name = "data_ultima_modificacao")
    private LocalDate ultimaDataModificacao = LocalDate.now();
    
    public HorarioTurma() {
    }

    public HorarioTurma(Integer id, Integer semestre, LocalDate dataInicio, LocalDate dataFim, Boolean finalizado, Turma turma, List<ReservaAula> aulas, List<PreReservaAula> listaPreReservas) {
        this.id = id;
        this.semestre = semestre;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.finalizado = finalizado;
        this.turma = turma;
        this.aulas = aulas;
        this.listaPreReservas = listaPreReservas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<ReservaAula> getAulas() {
        return aulas;
    }

    public void setAulas(List<ReservaAula> aulas) {
        this.aulas = aulas;
    }

    public List<PreReservaAula> getListaPreReservas() {
        return listaPreReservas;
    }

    public void setListaPreReservas(List<PreReservaAula> listaPreReservas) {
        this.listaPreReservas = listaPreReservas;
    }

    public LocalDate getUltimaDataModificacao() {
        return ultimaDataModificacao;
    }

    public void setUltimaDataModificacao(LocalDate ultimaDataModificacao) {
        this.ultimaDataModificacao = ultimaDataModificacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final HorarioTurma other = (HorarioTurma) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
