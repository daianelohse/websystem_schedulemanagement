package model.entidade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Desenvolvedor
 */
@Entity(name = "pre_reserva_aula")
public class PreReservaAula implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "professor")
    private String professor;
    
    @Column(name = "sala")
    private String sala;
    
    @Column(name = "uc")
    private String uc;
    
    @Column(name = "data_reserva")
    private LocalDate dataReserva;
    
    
    @ManyToOne
    @JoinColumn(name="id_horario")
    private HorarioTurma horario;
    
    @Enumerated
    @Column(name="tipo")
    private TipoPreReserva tipoPreReserva;
    
    @Column(name = "sequencia_horario")
    private Integer sequencia;
    
    
    public PreReservaAula() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public TipoPreReserva getTipoPreReserva() {
        return tipoPreReserva;
    }

    public void setTipoPreReserva(TipoPreReserva tipoPreReserva) {
        this.tipoPreReserva = tipoPreReserva;
    }



    public HorarioTurma getHorario() {
        return horario;
    }

    public void setHorario(HorarioTurma horario) {
        this.horario = horario;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final PreReservaAula other = (PreReservaAula) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

}
