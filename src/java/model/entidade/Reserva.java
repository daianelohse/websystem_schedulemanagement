package model.entidade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "reserva")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_reserva")
public abstract class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao_reserva")
    private String descricao;


    @Column(name = "data_reserva")
    private LocalDate dataReserva;

    @Enumerated
    @Column(name = "horario_aula")
    private HorarioAula horarioAula;

    @Column(name = "data_solicitacao")
    private LocalDate dataSolicitacao;

    @Column(name = "is_reserva_ok", columnDefinition = "boolean default false")
    private Boolean reservaOk;

    @Enumerated
    @Column(name = "situacao_reserva")
    private SituacaoReserva situacaoReserva;

    @ManyToOne()
    @JoinColumn(name = "id_ambiente")
    private Ambiente ambiente;

    @ManyToOne()
    @JoinColumn(name = "id_colaborador")
    private Colaborador colaborador;
    
    @ManyToOne()
    @JoinColumn(name = "id_ambiente_anterior")
    private Ambiente ambienteAnterior;

    public Reserva() {
    }

    public Reserva(LocalDate dataReserva, SituacaoReserva situacaoReserva) {
        this.dataReserva = dataReserva;
        this.situacaoReserva = situacaoReserva;
    }

    public Reserva(String descricao, Localizacao local, TipoAmbiente tipo, LocalDate data, HorarioAula horarioAula, LocalDate dataSolicitacao, Boolean reservaOk, Ambiente ambiente, Colaborador colaborador) {
 
        this.descricao = descricao;
        this.dataReserva = data;
        this.horarioAula = horarioAula;
        this.dataSolicitacao = dataSolicitacao;
        this.reservaOk = reservaOk;
        this.ambiente = ambiente;
        this.colaborador = colaborador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public HorarioAula getHorarioAula() {
        return horarioAula;
    }

    public void setHorarioAula(HorarioAula horarioAula) {
        this.horarioAula = horarioAula;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Boolean isReservaOk() {
        return reservaOk;
    }

    public void setReservaOk(Boolean reservaOk) {
        this.reservaOk = reservaOk;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public SituacaoReserva getSituacaoReserva() {
        return situacaoReserva;
    }

    public void setSituacaoReserva(SituacaoReserva situacaoReserva) {
        this.situacaoReserva = situacaoReserva;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Ambiente getAmbienteAnterior() {
        return ambienteAnterior;
    }

    public void setAmbienteAnterior(Ambiente ambienteAnterior) {
        this.ambienteAnterior = ambienteAnterior;
    }
    
    

}
