package util;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import model.entidade.Feriado;

public class FeriadoUtil {

    private int year;
    private List<Feriado> feriados;

    public FeriadoUtil(int year) {
        this.year = year;
    }

    public FeriadoUtil() {
        year = 0;
    }

    private Feriado getPascoa() {
        int a = year % 19;
        int b = year % 4;
        int c = year % 7;
        int d = (19 * a + 24) % 30;
        int e = (2 * b + 4 * c + 6 * d + 5) % 7;
        int dia = 0;
        Month mes;
        if ((d + e) > 9) {
            dia = (d + e - 9);
            mes = Month.APRIL;
        } else {
            dia = (d + e + 22);
            mes = Month.MARCH;
        }
        LocalDate data = LocalDate.of(year, mes, dia);
        return new Feriado("Feriado", "Páscoa", "Feriado", data);
    }

    public List<Feriado> getFeriados() {
        return feriados;
    }

    public boolean isFeriado(LocalDate data) {
        popularFeriados(data.getYear());
        for (Feriado feriado : feriados) {
            if (feriado.getData().equals(data)) {
                return true;
            }
        }
        return false;
    }

    public Feriado getFeriado(LocalDate data) {
        popularFeriados(data.getYear());
        for (Feriado feriado : feriados) {
            if (data.equals(feriado.getData())) {
                return feriado;
            }
        }
        return null;
    }

    private void popularFeriados(int year) {
        feriados = Arrays.asList(
                new Feriado("Feriado", "Confraternização", "Universal", LocalDate.of(year, 1, 1)),
                new Feriado("Feriado", "Carnaval", "Feriado", getPascoa().getData().minusDays(47)),
                new Feriado("Feriado", "Sexta", "Santa", getPascoa().getData().minusDays(3)),
                new Feriado("Feriado", "Tiradentes", "Feriado", LocalDate.of(year, 4, 21)),
                new Feriado("Feriado", "Corpus", "Christi", getPascoa().getData().plusDays(60)),
                new Feriado("Feriado", "Independência", "Brasil", LocalDate.of(year, 9, 7)),
                new Feriado("Feriado", "Nacional", "Feriado", LocalDate.of(year, 10, 12)),
                new Feriado("Feriado", "Finados", "Feriado", LocalDate.of(year, 11, 2)),
                new Feriado("Feriado", "Natal", "Feriado", LocalDate.of(year, 12, 25)),
                new Feriado("Feriado", "Proclamação", "República", LocalDate.of(year, 11, 15)),
                new Feriado("Feriado", "Proclamação", "República", LocalDate.of(year, 11, 15))
        );
    }
}
