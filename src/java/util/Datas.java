package util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Datas {

    private static DateTimeFormatter formatadorDomingo = DateTimeFormatter.ofPattern("EEE");
    private static DateTimeFormatter formatadorMes = DateTimeFormatter.ofPattern("MMM");

    public static LocalDate getSegunda(LocalDate date) {
        if (isDomingo(date)) {
            return date.plusDays(1);
        } else {
            int diaSemanaInicial = date.getDayOfWeek().getValue();
            if (diaSemanaInicial != 1) {
                date = date.minusDays(diaSemanaInicial - 1);
            }
            return date;
        }

    }

    public static LocalDate getSabado(LocalDate date) {
        if (isDomingo(date)) {
            return date.plusDays(5);
        } else {
            int diaSemanaFinal = date.getDayOfWeek().getValue();
            if (diaSemanaFinal != 6) {
                date = date.plusDays(5 - diaSemanaFinal);
            }
        }

        return date;
    }

    public static boolean isDomingo(LocalDate date) {
        return formatadorDomingo.format(date).equalsIgnoreCase("DOM");
    }

    public static int calcularNumeroDias(LocalDate dataInicial, LocalDate dataFinal) {

        long numeroDiasTotal = ChronoUnit.DAYS.between(dataInicial, dataFinal);

        return (int) numeroDiasTotal;
    }

    public static String buscarMes(LocalDate dataInicial) {
        int diaMinimo = 0;

        if (dataInicial.lengthOfMonth() == 31) {
            diaMinimo = 27;
        } else {
            diaMinimo = 26;
        }
        if (dataInicial.getDayOfMonth() >= diaMinimo && dataInicial.getDayOfMonth() <= 31) {
            LocalDate dateA = LocalDate.from(dataInicial);
            LocalDate dateB = dateA.plusDays(6);
            String mesA = formatadorMes.format(dateA);
            String mesB = formatadorMes.format(dateB);

            return (mesA + "/" + mesB).toUpperCase();

        } else {
            return formatadorMes.format(dataInicial).toUpperCase();
        }
    }

    public static boolean isSabado(LocalDate date) {
        return date.getDayOfWeek().getValue() == 7;
    }

    public boolean isSunday(LocalDate date) {
        return date.getDayOfWeek().getValue() == 1;
    }

    
    public static int getNumeroDias(LocalDate dataInicial, LocalDate dataFinal) {
        int cont = 0;

        for (; dataInicial.isBefore(dataFinal); dataInicial = dataInicial.plusDays(1)) {
            if (!isSabado(dataInicial) && !isDomingo(dataInicial)) {
                cont++;
            }
        }
        return cont;
    }

}
