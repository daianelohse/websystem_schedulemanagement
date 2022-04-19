/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package relatorio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import util.Datas;

/**
 *
 * @author Desenvolvedor
 */
public class RelatorioReservaAmbiente {
    
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public RelatorioReservaAmbiente(LocalDate dataInicial, LocalDate dataFinal) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    
    
    public void gerarRelatorio(){
        if(dataInicial != null & dataFinal != null){
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("dataInicial", formatarData("dd/MM/yyyy", dataInicial));
            parametros.put("dataFinal", formatarData("dd/MM/yyyy", dataFinal));
            parametros.put("dataInicialPesquisa", formatarData("yyyy-MM-dd", dataInicial));
            parametros.put("dataFinalPesquisa", formatarData("yyyy-MM-dd", dataFinal));
            parametros.put("numeroDiasUteis", getDiasUteis(dataInicial, dataFinal));
            
            try {
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgh_v1", "root", "");
                
                RelatorioUtil relatorioUtil  = new RelatorioUtil();
                relatorioUtil.gerarRelatorio("RelatorioDeReservas.jasper", parametros, c);
            } catch (SQLException | JRException | IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(RelatorioReservaAmbiente.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    
    
    private String formatarData(String formato, LocalDate data){
        return DateTimeFormatter.ofPattern(formato).format(data);
    }

    private int getDiasUteis(LocalDate dataInicial, LocalDate dataFinal) {
        return Datas.calcularNumeroDias(dataInicial, dataFinal);
    }
    
    
}
