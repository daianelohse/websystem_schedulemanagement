
package testes;

import controller.controler.ControleColaborador;
import controller.dao.ColaboradorDAO;
import controller.dao.CursoDAO;
import controller.dao.ModalidadeDAO;
import controller.dao.UnidadeCurricularDAO;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import model.entidade.Area;
import model.entidade.Colaborador;
import model.entidade.DiaSemana;
import model.entidade.DiaTrabalho;
import model.entidade.DisponibilidadeDocente;
import model.entidade.HorarioAula;
import model.entidade.Login;
import model.entidade.Permissao;
import model.entidade.Pessoa;
import model.entidade.Regra;
import model.entidade.TipoContrato;
import org.junit.Assert;
import org.junit.Test;
import util.EncriptadorUtil;

public class CadastroColaborador {
    
    @Test
    public void cadastrar(){
        ControleColaborador controleColaborador = new ControleColaborador();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        Colaborador nicolas = new Colaborador();
        ModalidadeDAO modalidadeDAO = new ModalidadeDAO();
        CursoDAO cursoDAO = new CursoDAO();
        UnidadeCurricularDAO unidadeCurricularDAO = new UnidadeCurricularDAO();
        Pessoa nicolasPessoa = new Pessoa();
        Login nicolasLogin = new Login();
        
        nicolasPessoa.setNome("Nicolas José Cordeiro Viana");
        nicolasPessoa.setDataNascimento(LocalDate.of(1997, Month.APRIL, 8));
        nicolasPessoa.setEmailPessoal("nirgeonic@gmail.com");
        nicolasPessoa.setTelefonePessoal("(47) 9129-2646");
        
        nicolas.setPessoa(nicolasPessoa);
        nicolas.setArea(Area.EDU);
        nicolas.setEmailComercial("nicolas.viana@sc.senai.br");
        nicolas.setMatricula(2332);
        nicolas.setTipoContrato(TipoContrato.MENSALISTA);
        nicolas.setQuantidadeHoraDiaria(8);
        
        nicolasLogin.setSenha(EncriptadorUtil.encriptarSenha("1234"));
        nicolasLogin.setUsuario(nicolas.getEmailComercial());
        
        nicolasPessoa.setLogin(nicolasLogin);
        nicolasLogin.setPessoa(nicolasPessoa);
        
        List<DiaTrabalho> diasTrabalho = new ArrayList<>();
        diasTrabalho.add(new DiaTrabalho(HorarioAula.HM1, DisponibilidadeDocente.DISPONIVEL, DiaSemana.TERCA, nicolas));
        diasTrabalho.add(new DiaTrabalho(HorarioAula.HM1, DisponibilidadeDocente.DISPONIVEL, DiaSemana.SEGUNDA, nicolas));
        diasTrabalho.add(new DiaTrabalho(HorarioAula.HM1, DisponibilidadeDocente.DISPONIVEL, DiaSemana.QUARTA, nicolas));
        
        List<Permissao> permissoes = new ArrayList<Permissao>();
        permissoes.add(new Permissao(nicolasLogin, Regra.ROLE_ADMIN));
        nicolas.getPessoa().getLogin().setPermissoes(permissoes);
        
        nicolas.setGradeTrabalho(diasTrabalho);
        nicolas.setCompetencias(unidadeCurricularDAO.listar());
        nicolas.setCursosCoordenacao(cursoDAO.listar());
        nicolas.setModalidade(modalidadeDAO.findById(1));
        nicolas.setReservas(null);
        Assert.assertTrue(controleColaborador.cadastrarColaborador(nicolas));
    }
    
}
