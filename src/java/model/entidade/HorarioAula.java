package model.entidade;

import java.time.LocalTime;

public enum HorarioAula {
    
    HM1(LocalTime.of(7, 30), LocalTime.of(9, 0),"M1 - 07:30 ás 09:00", true),
    HM2(LocalTime.of(9, 0), LocalTime.of(11, 30),"M2 - 09:00 ás 11:30", false),
    HV1(LocalTime.of(13, 15), LocalTime.of(15, 0),"V1 - 13:15 às 15:00", true),
    HV2(LocalTime.of(15, 00), LocalTime.of(17, 15),"V2 - 15:00 às 17:15", false),
    HN1(LocalTime.of(18, 30), LocalTime.of(20, 15),"N1 - 18:30 às 20:15", true),
    HN2(LocalTime.of(20, 15), LocalTime.of(22, 0),"N2 - 20:15 - 22:00", false);
  
 
    private LocalTime horaInicio;
    private LocalTime horaTermino;
    private String descricao;
    private boolean antesIntervalo;
    private HorarioAula(LocalTime horaInicio, LocalTime horaTermino, String descricao, boolean antesIntervalo){
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.descricao = descricao;
        this.antesIntervalo = antesIntervalo;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraTermino() {
        return horaTermino;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAntesIntervalo() {
        return antesIntervalo;
    }

    public void setAntesIntervalo(boolean antesIntervalo) {
        this.antesIntervalo = antesIntervalo;
    }
    
    
    
}
