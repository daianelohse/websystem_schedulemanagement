package controller.dao;

import controller.controler.ConfiguracaoRelatorio;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import model.entidade.Ambiente;
import model.entidade.Colaborador;
import model.entidade.HorarioAula;
import model.entidade.Reserva;
import model.entidade.ReservaAula;
import model.entidade.ReservaEvento;
import model.entidade.Turma;
import model.entidade.Turno;

public class ReservaDAO {

    private GenericDAO genericDAO;

    public ReservaDAO() {
        genericDAO = new GenericDAO<Reserva>();
    }

    public boolean salvar(Reserva reserva) {
        if (reserva.getId() == null) {
            return genericDAO.insert(reserva);
        } else {
            return genericDAO.update(reserva);
        }
    }

    public boolean remover(Reserva reserva) {
        return genericDAO.remove(reserva);
    }

    public List<ReservaEvento> listarReservaEvento() {
        return genericDAO.getEm().createQuery("select r from reserva_evento r GROUP BY r.dataReserva,r.dataSolicitacao, r.nome ORDER BY r.dataSolicitacao desc").getResultList();
    }

    public List<Reserva> listar() {
        return genericDAO.getEm().createQuery("select r from reserva r").getResultList();
    }

    public List<Reserva> buscarReservaAmbiente(Ambiente ambiente, Date dataInicio, Date dataFinal) {
        return genericDAO.getEm().createQuery("SELECT r FROM reserva r WHERE r.ambiente = :ambiente AND r.dataReserva BETWEEN :dataInicio AND :dataFinal")
                .setParameter("dataInicio", dataInicio)
                .setParameter("dataFinal", dataFinal)
                .getResultList();
    }

    public Double quantidadeReservaAmbiente(Turno turno, Ambiente ambiente, ConfiguracaoRelatorio config) {

        Object obj = genericDAO.getEm().createQuery("SELECT COUNT(r.id) FROM reserva r WHERE r.horarioAula in (:horarioAula) AND r.ambiente = :ambiente AND r.dataSolicitacao BETWEEN :dataInicio AND :dataFinal")
                .setParameter("horarioAula", turno.getHorarios())
                .setParameter("ambiente", ambiente)
                .setParameter("dataInicio", config.getDataInicial())
                .setParameter("dataFinal", config.getDataFinal())
                .getSingleResult();
        return (Double) obj;
    }

    public void atualizar(ReservaEvento reserva) {
        List<ReservaEvento> reservas = buscarReservasComHorarios(reserva);
        for (ReservaEvento reservaEvento : reservas) {
            reservaEvento.setSituacaoReserva(reserva.getSituacaoReserva());
            reserva.setReservaOk(reserva.isReservaOk());
            genericDAO.update(reserva);
        }

    }

    public List<ReservaEvento> buscarReservasComHorarios(ReservaEvento reserva) {
        return genericDAO.getEm().createQuery("SELECT r FROM reserva_evento r where r.dataReserva = :dataReserva AND r.dataSolicitacao = :dataSolicitacao AND r.nome = :nome")
                .setParameter("dataReserva", reserva.getDataReserva())
                .setParameter("dataSolicitacao", reserva.getDataSolicitacao())
                .setParameter("nome", reserva.getNome())
                .getResultList();
    }

    public ReservaEvento findById(Integer idEditar) {
        return genericDAO.getEm().find(ReservaEvento.class, idEditar);
    }

    public List<ReservaEvento> buscarReservaPorUsuario(Colaborador colaboradorLogado) {
        return genericDAO.getEm().createQuery("SELECT r FROM reserva_evento r WHERE r.colaborador = :colaborador GROUP BY r.dataReserva,r.dataSolicitacao, r.nome ORDER BY r.dataSolicitacao desc")
                .setParameter("colaborador", colaboradorLogado)
                .getResultList();
    }

    public Long buscarNumeroReservasNaoOK() {
        return (Long) genericDAO.getEm().createQuery("SELECT COUNT(r) FROM reserva r WHERE r.reservaOk = false GROUP BY r.dataReserva,r.dataSolicitacao, r.nome")
                .getSingleResult();
    }

    public Long buscarNumeroReservasPeriodo() {
        LocalDate now = LocalDate.now();
        List<HorarioAula> horarios = getHorariosAtual();
        return (Long) genericDAO.getEm().createQuery("SELECT COUNT(r) FROM reserva r WHERE r.horarioAula in (:horarios) and r.dataReserva = :data GROUP BY r.dataReserva,r.dataSolicitacao, r.nome")
                .setParameter("horarios", horarios)
                .setParameter("data", now)
                .getSingleResult();
    }

    private List<HorarioAula> getHorariosAtual() {
        LocalTime now = LocalTime.now();
        if (now.getHour() > 6 && now.getHour() < 12) {
            return Arrays.asList(HorarioAula.HM1, HorarioAula.HM2);
        } else if (now.getHour() < 18) {
            return Arrays.asList(HorarioAula.HV1, HorarioAula.HV2);
        } else {
            return Arrays.asList(HorarioAula.HN1, HorarioAula.HN2);
        }
    }

    public List<ReservaAula> buscarReservasPorDocente(LocalDate dataInicial, LocalDate dataFinal, Turno turno, Colaborador colaborador) {
        return genericDAO.getEm().createQuery("SELECT r from reserva_aula r where r.dataReserva BETWEEN :dataInicial and :dataFinal and r.colaborador = :colaborador and r.horarioAula in (:horarioAula) order ORDER BY r.dataReserva")
                .setParameter("dataInicial", dataInicial)
                .setParameter("dataFinal", dataFinal)
                .setParameter("colaborador", colaborador)
                .setParameter("horarioAula", Arrays.asList(turno.getHorarios()))
                .getResultList();
                
    }

    public List<ReservaAula> buscarReservasPorAmbiente(LocalDate dataInicial, LocalDate dataFinal, Turno turno, Ambiente ambiente) {
        return genericDAO.getEm().createQuery("SELECT r from reserva_aula r where r.dataReserva BETWEEN :dataInicial and :dataFinal and r.ambiente = :ambiente and r.horarioAula in (:horarioAula) order ORDER BY r.dataReserva")
                .setParameter("dataInicial", dataInicial)
                .setParameter("dataFinal", dataFinal)
                .setParameter("ambiente", ambiente)
                .setParameter("horarioAula", Arrays.asList(turno.getHorarios()))
                .getResultList();
    }

    public List<ReservaAula> buscarReservasPorTurma(LocalDate dataInicial, LocalDate dataFinal, Turno turno, Turma turma) {
        return genericDAO.getEm().createQuery("SELECT r from reserva_aula r where r.dataReserva BETWEEN :dataInicial and :dataFinal and r.horarioTurma.turma = :turma and r.horarioAula in (:horarioAula) order ORDER BY r.dataReserva")
                .setParameter("dataInicial", dataInicial)
                .setParameter("dataFinal", dataFinal)
                .setParameter("turma", turma)
                .setParameter("horarioAula", Arrays.asList(turno.getHorarios()))
                .getResultList();
    }
}
