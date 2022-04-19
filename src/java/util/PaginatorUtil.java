package util;

import java.util.ArrayList;
import java.util.List;

public class PaginatorUtil {

    private List itens;
    private List<Integer> numeroPaginas;
    private Integer numeroPaginasTotal;
    private Integer numeroItensPorPagina = 10;

    public PaginatorUtil() {
        numeroPaginas = new ArrayList<>();
    }

    public PaginatorUtil(List itens) {
        this();
        setItens(itens);
    }

    public void setItens(List itens) {
        this.itens = itens;
        numeroPaginasTotal = itens.size() / numeroItensPorPagina;
        if (numeroPaginasTotal == 0) {
            numeroPaginas.add(1);
        } else {
            for (int i = 1; i <= numeroPaginasTotal; i++) {
                numeroPaginas.add(i);
            }
        }
    }

    public void setNumeroItensPorPagina(Integer numeroItensPorPagina) {
        this.numeroItensPorPagina = numeroItensPorPagina;
    }

    public List buscarItensPagina(Integer numeroPagina) {
        List retorno = new ArrayList();
        Integer inicio = (numeroItensPorPagina * numeroPagina) - numeroItensPorPagina;

        for (int i = inicio; i < numeroItensPorPagina + inicio; i++) {
            if (i < itens.size()) {
                retorno.add(itens.get(i));
            }
        }
        return retorno;
    }

    public List<Integer> getNumeroPaginas() {
        return numeroPaginas;
    }

    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>();
        nomes.add("A");
        nomes.add("B");
        nomes.add("C");
        nomes.add("D");
        nomes.add("E");
        nomes.add("F");
        nomes.add("G");
        nomes.add("H");
        nomes.add("I");
        nomes.add("J");
        nomes.add("K");
        nomes.add("L");
        nomes.add("M");
        nomes.add("N");
        nomes.add("O");
        nomes.add("P");
        nomes.add("Q");
        nomes.add("R");
        nomes.add("S");
        nomes.add("T");
        nomes.add("U");
        nomes.add("V");
        nomes.add("W");
        nomes.add("X");
        nomes.add("Y");
        nomes.add("Z");

        PaginatorUtil paginator = new PaginatorUtil(nomes);

        for (Object string : paginator.buscarItensPagina(1)) {
            String str = (String) string;
            System.out.println(str);
        }

        paginator.setNumeroItensPorPagina(20);
        System.out.println("---------------------------------------");
        for (Object string : paginator.buscarItensPagina(1)) {
            String str = (String) string;
            System.out.println(str);
        }

    }
}
