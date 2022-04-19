package controller.controler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Query;
import model.entidade.Ambiente;
import model.entidade.HorarioAula;
import model.entidade.Localizacao;
import model.entidade.TipoAmbiente;
import util.ConexaoUtil;

public class ControleReserva {

    private ConexaoUtil conexaoUtil;

    public ControleReserva() {
        this.conexaoUtil = new ConexaoUtil();
    }

    public List<TipoAmbiente> buscarTiposByLocalidade(Localizacao localizacao) {
        return conexaoUtil.getEntityManager().createQuery("SELECT t FROM tipo_ambiente t join t.ambientes a WHERE a.localizacao = :localizacao GROUP BY t")
                .setParameter("localizacao", localizacao)
                .getResultList();
    }

    public List<TipoAmbiente> buscarLocaisByTipoAmbiente(TipoAmbiente tipo) {
        return conexaoUtil.getEntityManager().createQuery("SELECT l FROM localizacao l JOIN l.ambientes a WHERE a.tipoAmbiente = :tipoAmbiente")
                .setParameter("tipoAmbiente", tipo)
                .getResultList();
    }

    public List<Ambiente> buscarAmbientesLivres(LocalDate data, TipoAmbiente tipo, Localizacao local, List<HorarioAula> horarios) {

        return conexaoUtil.getEntityManager().createQuery("SELECT a FROM ambiente a "
                + "WHERE a.tipoAmbiente = :tipoAmbiente "
                + "AND a.localizacao = :local "
                + "AND a NOT IN "
                + "(SELECT r.ambiente FROM reserva AS r "
                + "WHERE r.dataReserva = :data AND r.horarioAula IN :horarioAulas)")
                .setParameter("horarioAulas", horarios)
                .setParameter("tipoAmbiente", tipo)
                .setParameter("local", local)
                .setParameter("data", data)
                .getResultList();

    }
}
