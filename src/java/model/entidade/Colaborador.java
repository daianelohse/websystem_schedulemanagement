package model.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "colaborador")
public class Colaborador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(name = "matricula", nullable = false)
    private Integer matricula;

    @Column(name = "email_comercial", nullable = false)
    private String emailComercial;

    @Column(name = "ramal", length = 6)
    private String ramal;

    @Column(name = "quantidade_hora_diaria", nullable = false)
    private Integer quantidadeHoraDiaria;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    // ver
    @Column(name = "is_coordenador_modalidade", columnDefinition = "boolean default false")
    private Boolean coordenadorModalidade;

    @Enumerated
    @Column(name = "tipo_contrato")
    private TipoContrato tipoContrato;

    @Enumerated
    @Column(name = "area")
    private Area area;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_modalidade")
    private Modalidade modalidade;

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL)
    private List<DiaTrabalho> gradeTrabalho;

    @ManyToMany()
    @JoinTable(name = "curso_coordenacao", 
            joinColumns = @JoinColumn(name = "colaborador_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursosCoordenacao;

    @ManyToMany()
    @JoinTable(name = "competencia_colaborador", joinColumns = @JoinColumn(name = "colaborador_id"),
            inverseJoinColumns = @JoinColumn(name = "unidade_curricular_id"))
    private List<UnidadeCurricular> competencias;

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.MERGE)
    private List<Reserva> reservas;

    public Colaborador() {
    }

    public Colaborador(Integer matricula, String emailComercial, String ramal, Integer quantidadeHoraDiaria, String observacao, Boolean coordenadorModalidade, TipoContrato tipoContrato, Area area, Modalidade modalidade) {
        this.matricula = matricula;
        this.emailComercial = emailComercial;
        this.ramal = ramal;
        this.quantidadeHoraDiaria = quantidadeHoraDiaria;
        this.observacao = observacao;
        this.coordenadorModalidade = coordenadorModalidade;
        this.tipoContrato = tipoContrato;
        this.area = area;
        this.modalidade = modalidade;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getEmailComercial() {
        return emailComercial;
    }

    public void setEmailComercial(String emailComercial) {
        this.emailComercial = emailComercial;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public Integer getQuantidadeHoraDiaria() {
        return quantidadeHoraDiaria;
    }

    public void setQuantidadeHoraDiaria(Integer quantidadeHoraDiaria) {
        this.quantidadeHoraDiaria = quantidadeHoraDiaria;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean isCoordenadorModalidade() {
        return coordenadorModalidade;
    }

    public void setCoordenadorModalidade(Boolean coordenadorModalidade) {
        this.coordenadorModalidade = coordenadorModalidade;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<DiaTrabalho> getGradeTrabalho() {
        return gradeTrabalho;
    }

    public void setGradeTrabalho(List<DiaTrabalho> gradeTrabalho) {
        this.gradeTrabalho = gradeTrabalho;
    }

    public List<UnidadeCurricular> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<UnidadeCurricular> competencias) {
        this.competencias = competencias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Curso> getCursosCoordenacao() {
        return cursosCoordenacao;
    }

    public void setCursosCoordenacao(List<Curso> cursosCoordenacao) {
        this.cursosCoordenacao = cursosCoordenacao;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
   

}
