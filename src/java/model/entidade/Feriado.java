package model.entidade;

import java.time.LocalDate;
import java.util.Objects;

public class Feriado {

    private String descCel1;
    private String descCel2;
    private String descCel3;

    private LocalDate data;

    public Feriado() {
    }

    public Feriado(String descCel1, String descCel2, String descCel3, LocalDate data) {
        this.descCel1 = descCel1;
        this.descCel2 = descCel2;
        this.descCel3 = descCel3;
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescCel1() {
        return descCel1;
    }

    public void setDescCel1(String descCel1) {
        this.descCel1 = descCel1;
    }

    public String getDescCel2() {
        return descCel2;
    }

    public void setDescCel2(String descCel2) {
        this.descCel2 = descCel2;
    }

    public String getDescCel3() {
        return descCel3;
    }

    public void setDescCel3(String descCel3) {
        this.descCel3 = descCel3;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Feriado other = (Feriado) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return data 
                + descCel1 + "\n"
                + descCel2 + "\n"
                + descCel3 + "\n";
    }

}
