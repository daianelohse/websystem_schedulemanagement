package view;

import controller.dao.CursoDAO;
import controller.dao.ModalidadeDAO;
import controller.dao.UnidadeCurricularDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.entidade.Ambiente;
import model.entidade.Curso;
import model.entidade.GradeCurricular;
import model.entidade.Modalidade;
import model.entidade.UnidadeCurricular;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Daiane e Nicolas
 */
@ManagedBean
@ViewScoped
public class CadastroCursoBean {

    private Curso curso = new Curso();
    private Modalidade modalidade = new Modalidade();
    private UnidadeCurricular unidadeCurricular = new UnidadeCurricular();

    private List<Modalidade> modalidades;
    private List<UnidadeCurricular> unidadesCurriculares;
    private List<UnidadeCurricular> UCSelecionadas1;
    private List<UnidadeCurricular> UCSelecionadas2;
    private List<UnidadeCurricular> UCSelecionadas3;
    private List<UnidadeCurricular> UCSelecionadas4;
    private List<UnidadeCurricular> UCSelecionadas5;
    private List<UnidadeCurricular> UCSelecionadas6;
    private List<UnidadeCurricular> UCSelecionadas7;
    private List<UnidadeCurricular> UCSelecionadas8;
    private List<UnidadeCurricular> UCSelecionadas9;
    private List<UnidadeCurricular> UCSelecionadas10;

    private ModalidadeDAO modalidadeDao = new ModalidadeDAO();
    private CursoDAO cursoDao = new CursoDAO();
    private UnidadeCurricularDAO ucDao = new UnidadeCurricularDAO();

    private String teste;

    public CadastroCursoBean() {
        this.UCSelecionadas1 = new ArrayList<UnidadeCurricular>();
        this.UCSelecionadas2 = new ArrayList<UnidadeCurricular>();
        this.UCSelecionadas3 = new ArrayList<UnidadeCurricular>();
        this.UCSelecionadas4 = new ArrayList<UnidadeCurricular>();
        this.UCSelecionadas5 = new ArrayList<UnidadeCurricular>();
        this.UCSelecionadas6 = new ArrayList<UnidadeCurricular>();
        this.UCSelecionadas7 = new ArrayList<UnidadeCurricular>();
        this.UCSelecionadas8 = new ArrayList<UnidadeCurricular>();
        this.UCSelecionadas9 = new ArrayList<UnidadeCurricular>();
        this.UCSelecionadas10 = new ArrayList<UnidadeCurricular>();

        this.modalidades = new ArrayList<Modalidade>();
        this.unidadesCurriculares = new ArrayList<UnidadeCurricular>();
        setModalidades(modalidadeDao.listar());
        setUnidadesCurriculares(ucDao.listar());
        
        Curso a = (Curso) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("cursoEdit");
        if (a != null) {
            this.curso = a;
            this.modalidade = curso.getModalidade();
        }
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public List<UnidadeCurricular> getUCSelecionadas1() {
        return UCSelecionadas1;
    }

    public void setUCSelecionadas1(List<UnidadeCurricular> UCSelecionadas1) {
        this.UCSelecionadas1 = UCSelecionadas1;
    }

    public List<UnidadeCurricular> getUCSelecionadas2() {
        return UCSelecionadas2;
    }

    public void setUCSelecionadas2(List<UnidadeCurricular> UCSelecionadas2) {
        this.UCSelecionadas2 = UCSelecionadas2;
    }

    public List<UnidadeCurricular> getUCSelecionadas3() {
        return UCSelecionadas3;
    }

    public void setUCSelecionadas3(List<UnidadeCurricular> UCSelecionadas3) {
        this.UCSelecionadas3 = UCSelecionadas3;
    }

    public List<UnidadeCurricular> getUCSelecionadas4() {
        return UCSelecionadas4;
    }

    public void setUCSelecionadas4(List<UnidadeCurricular> UCSelecionadas4) {
        this.UCSelecionadas4 = UCSelecionadas4;
    }

    public List<UnidadeCurricular> getUCSelecionadas5() {
        return UCSelecionadas5;
    }

    public void setUCSelecionadas5(List<UnidadeCurricular> UCSelecionadas5) {
        this.UCSelecionadas5 = UCSelecionadas5;
    }

    public List<UnidadeCurricular> getUCSelecionadas6() {
        return UCSelecionadas6;
    }

    public void setUCSelecionadas6(List<UnidadeCurricular> UCSelecionadas6) {
        this.UCSelecionadas6 = UCSelecionadas6;
    }

    public List<UnidadeCurricular> getUCSelecionadas7() {
        return UCSelecionadas7;
    }

    public void setUCSelecionadas7(List<UnidadeCurricular> UCSelecionadas7) {
        this.UCSelecionadas7 = UCSelecionadas7;
    }

    public List<UnidadeCurricular> getUCSelecionadas8() {
        return UCSelecionadas8;
    }

    public void setUCSelecionadas8(List<UnidadeCurricular> UCSelecionadas8) {
        this.UCSelecionadas8 = UCSelecionadas8;
    }

    public List<UnidadeCurricular> getUCSelecionadas9() {
        return UCSelecionadas9;
    }

    public void setUCSelecionadas9(List<UnidadeCurricular> UCSelecionadas9) {
        this.UCSelecionadas9 = UCSelecionadas9;
    }

    public List<UnidadeCurricular> getUCSelecionadas10() {
        return UCSelecionadas10;
    }

    public void setUCSelecionadas10(List<UnidadeCurricular> UCSelecionadas10) {
        this.UCSelecionadas10 = UCSelecionadas10;
    }

    public ModalidadeDAO getModalidadeDao() {
        return modalidadeDao;
    }

    public void setModalidadeDao(ModalidadeDAO modalidadeDao) {
        this.modalidadeDao = modalidadeDao;
    }

    public CursoDAO getCursoDao() {
        return cursoDao;
    }

    public void setCursoDao(CursoDAO cursoDao) {
        this.cursoDao = cursoDao;
    }

    public UnidadeCurricularDAO getUcDao() {
        return ucDao;
    }

    public void setUcDao(UnidadeCurricularDAO ucDao) {
        this.ucDao = ucDao;
    }

    public List<Modalidade> getModalidades() {
        return modalidades;
    }

    public void setModalidades(List<Modalidade> modalidades) {
        this.modalidades = modalidades;
    }

    public UnidadeCurricular getUnidadeCurricular() {
        return unidadeCurricular;
    }

    public void setUnidadeCurricular(UnidadeCurricular unidadeCurricular) {
        this.unidadeCurricular = unidadeCurricular;
    }

    public Curso getCurso() {
        return curso;
    }

    public List<UnidadeCurricular> getUnidadesCurriculares() {
        return unidadesCurriculares;
    }

    public void setUnidadesCurriculares(List<UnidadeCurricular> unidadesCurriculares) {
        this.unidadesCurriculares = unidadesCurriculares;
    }

    public List<UnidadeCurricular> procurarUnidadesCurriculares(String query) {
        List<UnidadeCurricular> retorno = new ArrayList<>();

        for (UnidadeCurricular uc : unidadesCurriculares) {
            if (uc.getNome().toLowerCase().startsWith(query.toLowerCase())) {
                retorno.add(uc);
            }
        }
        return retorno;
    }

    public void removerUCSelecionado(UnselectEvent event) {

        Object s = event.getSource();

        System.out.println(s);
        System.out.println(event.getSource().toString());

        UCSelecionadas1.remove((UnidadeCurricular) event.getObject());

        unidadesCurriculares.add((UnidadeCurricular) event.getObject());
    }

    public void UCSelecionado(SelectEvent event) {
        unidadesCurriculares.remove((UnidadeCurricular) event.getObject());
    }

    public void salvar() {
        curso.setModalidade(modalidade);

        if (UCSelecionadas1 != null && !UCSelecionadas1.isEmpty()) {
            for (UnidadeCurricular uc : UCSelecionadas1) {
                GradeCurricular grade = new GradeCurricular();
                grade.setCurso(curso);
                grade.setUnidadeCurricular(uc);
                grade.setSemestre(1);
                curso.addGrade(grade);
            }
        }

        if (UCSelecionadas2 != null && !UCSelecionadas2.isEmpty()) {
            for (UnidadeCurricular uc : UCSelecionadas2) {
                GradeCurricular grade = new GradeCurricular();
                grade.setCurso(curso);
                grade.setUnidadeCurricular(uc);
                grade.setSemestre(2);
                curso.addGrade(grade);
            }
        }

        if (UCSelecionadas3 != null && !UCSelecionadas3.isEmpty()) {
            for (UnidadeCurricular uc : UCSelecionadas3) {
                GradeCurricular grade = new GradeCurricular();
                grade.setCurso(curso);
                grade.setUnidadeCurricular(uc);
                grade.setSemestre(3);
                curso.addGrade(grade);
            }
        }

        if (UCSelecionadas4 != null && !UCSelecionadas4.isEmpty()) {
            for (UnidadeCurricular uc : UCSelecionadas4) {
                GradeCurricular grade = new GradeCurricular();
                grade.setCurso(curso);
                grade.setUnidadeCurricular(uc);
                grade.setSemestre(4);
                curso.addGrade(grade);
            }
        }

        if (UCSelecionadas5 != null && !UCSelecionadas5.isEmpty()) {
            for (UnidadeCurricular uc : UCSelecionadas5) {
                GradeCurricular grade = new GradeCurricular();
                grade.setCurso(curso);
                grade.setUnidadeCurricular(uc);
                grade.setSemestre(5);
                curso.addGrade(grade);
            }
        }

        if (UCSelecionadas6 != null && !UCSelecionadas6.isEmpty()) {
            for (UnidadeCurricular uc : UCSelecionadas6) {
                GradeCurricular grade = new GradeCurricular();
                grade.setCurso(curso);
                grade.setUnidadeCurricular(uc);
                grade.setSemestre(6);
                curso.addGrade(grade);
            }
        }

        if (UCSelecionadas7 != null && !UCSelecionadas7.isEmpty()) {
            for (UnidadeCurricular uc : UCSelecionadas7) {
                GradeCurricular grade = new GradeCurricular();
                grade.setCurso(curso);
                grade.setUnidadeCurricular(uc);
                grade.setSemestre(7);
                curso.addGrade(grade);
            }
        }

        if (UCSelecionadas8 != null && !UCSelecionadas8.isEmpty()) {
            for (UnidadeCurricular uc : UCSelecionadas8) {
                GradeCurricular grade = new GradeCurricular();
                grade.setCurso(curso);
                grade.setUnidadeCurricular(uc);
                grade.setSemestre(8);
                curso.addGrade(grade);
            }
        }

        if (UCSelecionadas9 != null && !UCSelecionadas9.isEmpty()) {
            for (UnidadeCurricular uc : UCSelecionadas9) {
                GradeCurricular grade = new GradeCurricular();
                grade.setCurso(curso);
                grade.setUnidadeCurricular(uc);
                grade.setSemestre(9);
                curso.addGrade(grade);
            }
        }

        if (UCSelecionadas10 != null && !UCSelecionadas10.isEmpty()) {
            for (UnidadeCurricular uc : UCSelecionadas10) {
                GradeCurricular grade = new GradeCurricular();
                grade.setCurso(curso);
                grade.setUnidadeCurricular(uc);
                grade.setSemestre(10);
                curso.addGrade(grade);
            }
        }
        cursoDao.salvar(curso);

    }

    public void limparTodasUcs() {
        if (UCSelecionadas1 != null) {
            UCSelecionadas1.clear();
        }
        if (UCSelecionadas1 != null) {
            UCSelecionadas2.clear();
        }
        if (UCSelecionadas3 != null) {
            UCSelecionadas3.clear();
        }
        if (UCSelecionadas4 != null) {
            UCSelecionadas4.clear();
        }
        if (UCSelecionadas5 != null) {
            UCSelecionadas5.clear();
        }
        if (UCSelecionadas6 != null) {
            UCSelecionadas6.clear();
        }
        if (UCSelecionadas7 != null) {
            UCSelecionadas7.clear();
        }
        if (UCSelecionadas8 != null) {
            UCSelecionadas8.clear();
        }
        if (UCSelecionadas9 != null) {
            UCSelecionadas9.clear();
        }
        if (UCSelecionadas10 != null) {
            UCSelecionadas10.clear();
        }
        if (modalidade == null) {
            unidadesCurriculares = new ArrayList<>();
        } else {
            unidadesCurriculares = ucDao.findByModalidade(modalidade);
        }
    }

}
