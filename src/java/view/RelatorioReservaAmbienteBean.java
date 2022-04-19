/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.io.Serializable;
import java.time.LocalDate;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import relatorio.RelatorioReservaAmbiente;

/**
 *
 * @author Desenvolvedor
 */
@ManagedBean
@ViewScoped
public class RelatorioReservaAmbienteBean implements Serializable{
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    
    private String disabledBtCalendario = "disabled";
    private String mensagemDataInvalida = "";
    
    public void habilitarGerarCalendario() {
        if (dataInicial != null && dataFinal != null) {
            if (dataFinal.isBefore(dataInicial)) {
                setMensagemDataInvalida("Período inválido! A data de início não pode ser maior que a de fim.");
                setDisabledBtCalendario("disabled");
            } else {
                setDisabledBtCalendario("");
                setMensagemDataInvalida("");
            }
        } else {
            setDisabledBtCalendario("disabled");
        }
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getDisabledBtCalendario() {
        return disabledBtCalendario;
    }

    public void setDisabledBtCalendario(String disabledBtCalendario) {
        this.disabledBtCalendario = disabledBtCalendario;
    }

    public String getMensagemDataInvalida() {
        return mensagemDataInvalida;
    }

    public void setMensagemDataInvalida(String mensagemDataInvalida) {
        this.mensagemDataInvalida = mensagemDataInvalida;
    }
    
    public void gerarRelatorio(){
        RelatorioReservaAmbiente relatorioReservaAmbiente = new RelatorioReservaAmbiente(getDataInicial(), getDataFinal());
        
        relatorioReservaAmbiente.gerarRelatorio();
    }
}
