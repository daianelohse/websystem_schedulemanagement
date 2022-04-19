/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.entidade.Feriado;
import model.entidade.PreReservaAula;
import model.entidade.TipoPreReserva;
import model.entidade.TipoVisualizacaoHorario;
import model.entidade.VisualizacaoTabelaAssembler;
import util.CommonUtils;
import util.Datas;
import util.FeriadoUtil;

/**
 *
 * @author Desenvolvedor
 */
public class TableFactoryTD {

    private FeriadoUtil feriadoUtil;
    private ControlePreReservaAula controlePreReserva;
    private List<PreReservaAula> listasReservas = null;
    private PreReservaAula infosDia = null;

    public TableFactoryTD() {
        infosDia = new PreReservaAula();
        controlePreReserva = new ControlePreReservaAula();
        feriadoUtil = new FeriadoUtil();

    }

    public List<String> printarTabelaDados(LocalDate dataInicial, LocalDate dataFinal) {

        // TEM QUE PEGAR O ID HORARIO
        // QUAL O SENTIDO DE TER ID HORARIO? PQ O COORDENADOR NÃO IRÁ ORDENAR A LISTA POR SEMESTRE, SEMPRE PEGARÁ A LISTA "PICADA
        // POR ISSO A DATA SERÁ SALVA NO BANCO. SÓ PRECISAMOS SABER QUAL SERÁ A TURMA, PARA NÃO MISTURAR
        dataInicial = Datas.getSegunda(dataInicial); //data inicial escolhida pelo usuário, depois vai incrementando
        dataFinal = Datas.getSabado(dataFinal); // data final escolhida pelo usuário
        listasReservas = controlePreReserva.listar(dataInicial, dataFinal);
        List<String> dados = new ArrayList<>();
        String tabela = "";
        int numeroDias = Datas.calcularNumeroDias(dataInicial, dataFinal); //número total de dias no período escolhido
        int i2 = 0;
        for (int i = 0; i <= numeroDias + 1; i++) {
            if (i2 >= 0 && i2 <= 5) {
                if (!Datas.isDomingo(dataInicial)) { // verifica se é domingo
                    i2++; // se é domingo, pula domingo
                    if (feriadoUtil.isFeriado(dataInicial)) { // verifica se é feriado
                        tabela = "<td class=\"modal-trigger red-text lighten-2\" id=\"cel" + i + "\">";  // se é feriado, deixa a letra vermelha           
                        Feriado feriado = feriadoUtil.getFeriado(dataInicial);
                        tabela += createDivData(dataInicial, i);
                        tabela += createDivFeriado(feriado.getDescCel1());
                        tabela += createDivFeriado(feriado.getDescCel2());
                        tabela += createDivFeriado(feriado.getDescCel3());
                    } else {
                        tabela = "<td class=\"modal-trigger\" onclick=\"abreModalDia(" + i + ")\" id=\"cel" + i + "\" >";
                        tabela += createDivData(dataInicial, i);
                        tabela += createDivValue(i, "uc", (String) CommonUtils.nullToEmpty(infosDia.getUc()));
                        tabela += createDivValue(i, "prof", (String) CommonUtils.nullToEmpty(infosDia.getProfessor()));
                        tabela += createDivValue(i, "sala", (String) CommonUtils.nullToEmpty(infosDia.getSala()));
                    }
                    tabela += "</td>";
                    if (i2 == 6) {
                        tabela += "</tr>";
                    }
                    dataInicial = dataInicial.plusDays(1);
                    dados.add(tabela);
                } else {
                    i--;
                    dataInicial = dataInicial.plusDays(1);
                }
            } else {
                i2 = 0;
                dataInicial.plusDays(1);
            }
        }

        return dados;
    }

    public List<String> getDivMeses(LocalDate dataInicial, LocalDate dataFinal) {
        List<String> retorno = new ArrayList<>();
        dataInicial = Datas.getSegunda(dataInicial);
        dataFinal = Datas.getSabado(dataFinal);
        int numeroDeDias = Datas.calcularNumeroDias(dataInicial, dataFinal);
        String tabela = "";
        if (numeroDeDias > 5) {
            for (int i = 0; i < numeroDeDias / 6; i++) {
                tabela = "<tr>";
                tabela += "<td>";
                tabela += createDivColMeses(Datas.buscarMes(dataInicial));
                tabela += createDivColMeses("UC");
                tabela += createDivColMeses("PROF");
                tabela += createDivColMeses("SALA");
                tabela += "</td>";
                retorno.add(tabela);
                dataInicial = dataInicial.plusDays(7);
            }
        } else {
            tabela = "<tr>";
            tabela += "<td>";
            tabela += createDivColMeses(Datas.buscarMes(dataInicial));
            tabela += createDivColMeses("UC");
            tabela += createDivColMeses("PROF");
            tabela += createDivColMeses("SALA");
            tabela += "</td>";
            retorno.add(tabela);
        }
        return retorno;
    }

    private String createDivColMeses(String value) {
        return "<div class='divsHorarios'>" + value + "</div>";
    }

    public String criarDivData(PreReservaAula preReservaAula) {

        return criarDivData(preReservaAula.getSequencia(), preReservaAula.getDataReserva());

    }

    private String createDivData(LocalDate data, Integer id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE - dd");
        DateTimeFormatter formaterTitle = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        infosDia.setProfessor("");
        infosDia.setUc("");
        infosDia.setSala("");

        int v = 1;
        String professor = "";
        String uc = "";
        String sala = "";

        if (listasReservas != null) {
            for (PreReservaAula reserva : listasReservas) {
                if (reserva.getDataReserva().equals(data)) {
                    if (reserva.getTipoPreReserva().equals(TipoPreReserva.NORMAL)) {
                        infosDia.setProfessor(reserva.getProfessor());
                        infosDia.setUc(reserva.getUc());
                        infosDia.setSala(reserva.getSala());
                    } else {

                        if (v == 1) {
                            professor = "";
                            uc = "";
                            sala = "";

                            professor = professor.concat(reserva.getProfessor().substring(0, 3) + "/");
                            uc = uc.concat(reserva.getUc().substring(0, 3) + "/");
                            sala = sala.concat(reserva.getSala().substring(0, 3) + "/");

                            v++;
                        } else if (v == 2) {
                            professor = professor.concat(reserva.getProfessor().substring(0, 3));
                            uc = uc.concat(reserva.getUc().substring(0, 3));
                            sala = sala.concat(reserva.getSala().substring(0, 3));
                            v = 1;
                        }
                        infosDia.setProfessor(professor);
                        infosDia.setUc(uc);
                        infosDia.setSala(sala);
                        //buscar a outra reserva do dia passado por parametro
                        // verificar qual das duas é a primeira
                        // concatenar as informações: reservaum.getProfessor() + "/" + reservadois.getProfessor()
                        // colocar na div um "identificador" dizendo que ela vai ser dividida, e colocando o index das duas reservas
                    }
                }
            }
        }

        return "<div  id=\"dia" + id + "\" title=\"" + formaterTitle.format(data) + "\" class='divsHorarios dataTabHorario'>" + formatter.format(data) + "</div>";

    }

    private String createDivValue(int id, String tipo, String value) {
        return "<div id=\"" + tipo + id + "\" class='divsHorarios'>" + value + "</div>";
    }

    private String createDivFeriado(String value) {
        return "<div class='divsHorarios feriado'>" + value + "</div>";
    }

    public String criarDivValor(PreReservaAula preReservaAula) {
        StringBuilder divRetorno = new StringBuilder();
        Integer sequencia = preReservaAula.getSequencia();
        divRetorno.append("<td class=\"modal-trigger\" onclick=\"abreModalDia(" + sequencia + ")\" id=\"cel" + sequencia + "\" >");
        divRetorno.append(criarDivData(preReservaAula));
        divRetorno.append(createDivValue(sequencia, "uc", (String) CommonUtils.nullToEmpty(preReservaAula.getUc())));
        divRetorno.append(createDivValue(sequencia, "prof", (String) CommonUtils.nullToEmpty(preReservaAula.getProfessor())));
        divRetorno.append(createDivValue(sequencia, "sala", (String) CommonUtils.nullToEmpty(preReservaAula.getSala())));

        return divRetorno.toString();
    }

    // método usado quando é um horário dividido
    public String criarDivValor(PreReservaAula preReserva, PreReservaAula preReservaAulaB) {
        StringBuilder divRetorno = new StringBuilder();
        Integer sequencia = preReserva.getSequencia();

        divRetorno.append("<td class=\"modal-trigger\" onclick=\"abreModalDia(" + sequencia + ")\" id=\"cel" + sequencia + "\" >");
        divRetorno.append(criarDivData(preReserva));
        divRetorno.append(createDivValue(sequencia, "uc", (String) CommonUtils.nullToEmpty(preReserva.getUc()) + "/" + CommonUtils.nullToEmpty(preReservaAulaB.getUc())));
        divRetorno.append(createDivValue(sequencia, "prof", (String) CommonUtils.nullToEmpty(preReserva.getProfessor()) + "/" + CommonUtils.nullToEmpty(preReservaAulaB.getProfessor())));
        divRetorno.append(createDivValue(sequencia, "sala", (String) CommonUtils.nullToEmpty(preReserva.getSala()) + "/" + CommonUtils.nullToEmpty(preReservaAulaB.getSala())));

        return divRetorno.toString();
    }

    // método usado quando é um horário dividido
    public String criarDivValor(TipoVisualizacaoHorario tipoVisualizacaoHorario,
            VisualizacaoTabelaAssembler preReserva, VisualizacaoTabelaAssembler preReservaAulaB) {
        StringBuilder divRetorno = new StringBuilder();
        Integer sequencia = preReserva.getSequenciador();

        divRetorno.append("<td class=\"modal-trigger\" onclick=\"abreModalDia(" + sequencia + ")\" id=\"cel" + sequencia + "\" >");
        divRetorno.append(criarDivData(preReserva.getSequenciador(), preReserva.getData()));
        if (TipoVisualizacaoHorario.DOCENTE.equals(tipoVisualizacaoHorario)) {
            divRetorno.append(createDivValue(sequencia, "uc", (String) CommonUtils.nullToEmpty(preReserva.getUnidadeCurricular()) + "/" + CommonUtils.nullToEmpty(preReservaAulaB.getUnidadeCurricular())));
            divRetorno.append(createDivValue(sequencia, "prof", (String) CommonUtils.nullToEmpty(preReserva.getTurma()) + "/" + CommonUtils.nullToEmpty(preReservaAulaB.getDocente())));
            divRetorno.append(createDivValue(sequencia, "sala", (String) CommonUtils.nullToEmpty(preReserva.getAmbiente()) + "/" + CommonUtils.nullToEmpty(preReservaAulaB.getAmbiente())));
        }
        if (TipoVisualizacaoHorario.TURMA.equals(tipoVisualizacaoHorario)) {
            divRetorno.append(createDivValue(sequencia, "uc", (String) CommonUtils.nullToEmpty(preReserva.getUnidadeCurricular()) + "/" + CommonUtils.nullToEmpty(preReservaAulaB.getUnidadeCurricular())));
            divRetorno.append(createDivValue(sequencia, "prof", (String) CommonUtils.nullToEmpty(preReserva.getDocente()) + "/" + CommonUtils.nullToEmpty(preReservaAulaB.getDocente())));
            divRetorno.append(createDivValue(sequencia, "sala", (String) CommonUtils.nullToEmpty(preReserva.getAmbiente()) + "/" + CommonUtils.nullToEmpty(preReservaAulaB.getAmbiente())));
        }
        if (TipoVisualizacaoHorario.SALA.equals(tipoVisualizacaoHorario)) {
            //TODO
        }

        return divRetorno.toString();
    }

    public String criarFeriado(Feriado feriado, Integer i) {
        String tabela = "";
        tabela = "<td class=\"modal-trigger red-text lighten-2\" id=\"cel" + i + "\">";
        tabela += criarDivData(i, feriado.getData());
        tabela += createDivFeriado(feriado.getDescCel1());
        tabela += createDivFeriado(feriado.getDescCel2());
        tabela += createDivFeriado(feriado.getDescCel3());
        return tabela;
    }

    private String criarDivData(Integer sequencia, LocalDate dataReserva) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE - dd");
        DateTimeFormatter formaterTitle = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "<div  id=\"dia" + sequencia + "\" title=\""
                + formaterTitle.format(dataReserva)
                + "\" class='divsHorarios dataTabHorario'>"
                + formatter.format(dataReserva) + "</div>";
    }
}
