package org.pesquisa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Resultado {

    private final AtomicInteger totalPaginas;
    private final AtomicInteger paginasComTermo;
    private final Map<String, List<String>> trechosPorUrl;

    public Resultado(int totalPaginas) {
        this.totalPaginas = new AtomicInteger(totalPaginas);
        this.paginasComTermo = new AtomicInteger(0);
        this.trechosPorUrl = new HashMap<>();
    }

    public void registrarResultado(boolean encontrado) {
        if (encontrado) {
            paginasComTermo.incrementAndGet();
        }
    }

    public void adicionarTrechos(String url, List<String> trechos) {
        trechosPorUrl.put(url, trechos);
    }

    public double calcularPercentual() {
        return (paginasComTermo.doubleValue() / totalPaginas.doubleValue()) * 100;
    }

    public void exibirTrechosEncontrados() {
        System.out.println("\nTrechos encontrados por URL:");
        for (Map.Entry<String, List<String>> entry : trechosPorUrl.entrySet()) {
            System.out.println("URL: " + entry.getKey());
            for (String trecho : entry.getValue()) {
                System.out.println("  - " + trecho);
            }
            System.out.println("-------------------------------");
        }
    }
}