package model.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "curso")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "sigla", length = 7, nullable = false)
    private String sigla;

    @ManyToOne()
    @JoinColumn(name = "id_modalidade")
    private Modalidade modalidade;

    @ManyToMany(mappedBy = "cursosCoordenacao")
    private List<Colaborador> coordenadores;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Turma> turmas;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<GradeCurricular> gradeCurso;

    public Curso() {
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

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public List<Colaborador> getCoordenadores() {
        return coordenadores;
    }

    public void setCoordenadores(List<Colaborador> coordenadores) {
        this.coordenadores = coordenadores;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public List<GradeCurricular> getGradeCurso() {
        return gradeCurso;
    }

    public void setGradeCurso(List<GradeCurricular> gradeCurso) {
        this.gradeCurso = gradeCurso;
    }

    public Integer getId() {
        return id;
    }

    public void addGrade(GradeCurricular grade) {
        if(this.gradeCurso == null){
            this.gradeCurso = new ArrayList<>();
        }
        this.gradeCurso.add(grade);
    }

}
