package model.entidade;

import java.util.Arrays;
import java.util.List;

public enum Turno {

    MATUTINO("Matutino","M", HorarioAula.HM1, HorarioAula.HM2),
    VESPERTINO("Vespertino","V", HorarioAula.HV1, HorarioAula.HV2),
    NOTURNO("Noturno","N", HorarioAula.HN1, HorarioAula.HN2),
    INTEGRAL("Integral","INT", HorarioAula.HM1, HorarioAula.HM2, HorarioAula.HV1, HorarioAula.HV2);

    private final String nome;
    private final String sigla;
    private final List<HorarioAula> horarios;

    private Turno(String nome, String sigla, HorarioAula... horarios) {
        this.nome = nome;
        this.sigla = sigla;
        this.horarios = Arrays.asList(horarios);
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    public List<HorarioAula> getHorarios() {
        return horarios;
    }
}
