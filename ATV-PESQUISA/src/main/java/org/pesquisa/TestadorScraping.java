package org.pesquisa;

import java.util.ArrayList;
import java.util.List;

public class TestadorScraping {

    public static void main(String[] args) throws InterruptedException {

        List<String> listaUrls = new ArrayList<>();
        listaUrls.add("https://www.globo.com");
        listaUrls.add("https://www.uol.com.br");
        listaUrls.add("https://www.poder360.com.br");
        listaUrls.add("https://www.sociedademilitar.com.br");

        String busca = "Militar";

        Resultado resultado = new Resultado(listaUrls.size());

        List<Thread> threads = new ArrayList<>();

        for (String url : listaUrls) {
            ScrapingTask task = new ScrapingTask(url, busca, resultado);
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Percentual de páginas que contêm o termo: " + resultado.calcularPercentual() + "%");
        resultado.exibirTrechosEncontrados();
    }
}