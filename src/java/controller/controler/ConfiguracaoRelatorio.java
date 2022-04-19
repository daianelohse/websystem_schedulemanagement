
package controller.controler;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ConfiguracaoRelatorio {
    
    private LocalDate dataInicial;
    private LocalDate dataFinal;

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
    
    public Long getNumDias(){
        return ChronoUnit.DAYS.between(dataInicial, dataFinal);
    }
    
}
