package model.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "ambiente")
public class Ambiente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "bloco", length = 3, nullable = false)
    private String bloco;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "divisao", length = 5)
    private String divisao;

    @Column(name = "andar")
    private Integer andar;

    @Column(name = "capacidade", nullable = false)
    private Integer capacidade;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @Enumerated
    @Column(name = "situacao")
    private SituacaoAmbiente situacao;

    @ManyToOne()
    @JoinColumn(name = "id_tipo_ambiente")
    private TipoAmbiente tipoAmbiente;

    @ManyToOne()
    @JoinColumn(name = "id_localizacao")
    private Localizacao localizacao;

    @OneToMany(mappedBy = "ambiente")
    private List<Reserva> reservas;


    public Ambiente() {
    }

    public Ambiente(String bloco, Integer numero, String divisao, Integer andar, Integer capacidade, String observacao, SituacaoAmbiente situacao, TipoAmbiente tipoAmbiente, Localizacao localizacao) {
        this.bloco = bloco;
        this.numero = numero;
        this.divisao = divisao;
        this.andar = andar;
        this.capacidade = capacidade;
        this.observacao = observacao;
        this.situacao = situacao;
        this.tipoAmbiente = tipoAmbiente;
        this.localizacao = localizacao;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public SituacaoAmbiente getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoAmbiente situacao) {
        this.situacao = situacao;
    }

    public TipoAmbiente getTipoAmbiente() {
        return tipoAmbiente;
    }

    public void setTipoAmbiente(TipoAmbiente tipoAmbiente) {
        this.tipoAmbiente = tipoAmbiente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

//    public void setSigla(String sigla) {
//        this.sigla = sigla;
//    }
//
//    public String getSigla() {
//        this.sigla = "";
//        if (this.bloco != null) {
//            this.sigla += this.bloco;
//        }
//
//        if (this.numero != null) {
//            this.sigla += this.numero;
//        }
//
//        if (this.divisao != null && !this.divisao.isEmpty()) {
//            this.sigla += "/" + this.divisao;
//        } else {
//            if (this.sigla.contains("/")) {
//                this.sigla = this.sigla.substring(0, this.sigla.indexOf("/"));
//            }
//        }
//        return this.sigla;
//    }

    public String getSigla() {
        String sigla = "";
        if (this.bloco != null) {
            sigla += this.bloco;
        }

        if (this.numero != null) {
            sigla += this.numero;
        }

        if (this.divisao != null && !this.divisao.isEmpty()) {
            sigla += "/" + this.divisao;
        } else {
            if (sigla.contains("/")) {
                sigla = sigla.substring(0, sigla.indexOf("/"));
            }
        }
        return sigla;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

}
