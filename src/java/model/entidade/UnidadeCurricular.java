package model.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "unidade_curricular")
public class UnidadeCurricular implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    
    @Column(name = "sigla", length = 5, nullable = false)
    private String sigla;
    
    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;
    
    @Column(name = "dica_ambiente", columnDefinition = "TEXT")
    private String dicaAmbiente;
    
    @ManyToOne()
    @JoinColumn(name = "id_modalidade")
    private Modalidade modalidade;
    
    @ManyToMany(mappedBy = "competencias")
    private List<Colaborador> docentesHabilitados;
    
    @OneToMany(mappedBy = "unidadeCurricular")
    private List<GradeCurricular> cursosGrade;
    
    @OneToMany(mappedBy = "unidadeCurricular")
    private List<ReservaAula> reservas;
    
    public UnidadeCurricular() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getDicaAmbiente() {
        return dicaAmbiente;
    }

    public void setDicaAmbiente(String dicaAmbiente) {
        this.dicaAmbiente = dicaAmbiente;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public List<Colaborador> getDocentesHabilitados() {
        return docentesHabilitados;
    }

    public void setDocentesHabilitados(List<Colaborador> docentesHabilitados) {
        this.docentesHabilitados = docentesHabilitados;
    }

    public List<GradeCurricular> getCursosGrade() {
        return cursosGrade;
    }

    public void setCursosGrade(List<GradeCurricular> cursosGrade) {
        this.cursosGrade = cursosGrade;
    }

    public List<ReservaAula> getReservas() {
        return reservas;
    }
    
    
    
    
    
}
