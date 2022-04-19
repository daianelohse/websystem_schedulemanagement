package controller.controler;

import controller.dao.CursoDAO;
import controller.dao.TurmaDAO;
import javax.persistence.Query;
import javax.swing.text.html.HTML;
import model.entidade.Curso;
import model.entidade.Turma;
import model.entidade.Turno;
import util.ConexaoUtil;

public class ControleTurma {

    private ConexaoUtil conexaoUtil;

    public ControleTurma() {
        conexaoUtil = new ConexaoUtil();
    }

    public Long buscarNumeroTurma(Turma turma) {
        Query q = conexaoUtil.getEntityManager().createQuery("SELECT COUNT(t.id) FROM turma t WHERE t.ano = :ano AND t.curso = :curso AND t.turno = :turno AND t.semestre = :semestre");
        q.setParameter("ano", turma.getAno()).setParameter("curso", turma.getCurso()).setParameter("turno", turma.getTurno()).setParameter("semestre", turma.getSemestre());
        return ((Long) q.getSingleResult() + 1);
    }

    public static void main(String[] args) {
//        CursoDAO cursoDAO = new CursoDAO();
//        Curso curso = cursoDAO.findById(2);
//        Turma t = new Turma();
//        t.setAno(2015);
//        t.setCurso(curso);
//        t.setNumero(2);
//        t.setTurno(Turno.VESPERTINO);
//        TurmaDAO turmaDAO = new TurmaDAO();
//        turmaDAO.salvar(t);
        ControleTurma controleTurma = new ControleTurma();
        CursoDAO cursoDAO = new CursoDAO();
        Curso curso = cursoDAO.findById(2);
        Turma t = new Turma();
        t.setAno(2015);
        t.setCurso(curso);
        t.setTurno(Turno.VESPERTINO);
        System.out.println(controleTurma.buscarNumeroTurma(t));

    }

}
