package org.pesquisa;

import java.util.List;

public class ScrapingTask implements Runnable {

    private final String url;
    private final String termo;
    private final Resultado resultado;

    public ScrapingTask(String url, String termo, Resultado resultado) {
        this.url = url;
        this.termo = termo;
        this.resultado = resultado;
    }

    @Override
    public void run() {
        long inicio = System.currentTimeMillis();
        List<String> trechosEncontrados = WebScraper.procurar(termo, url);
        long fim = System.currentTimeMillis();

        System.out.println("Tarefa concluída para " + url + " em " + (fim - inicio) + " ms");

        if (!trechosEncontrados.isEmpty()) {
            resultado.registrarResultado(true);
            resultado.adicionarTrechos(url, trechosEncontrados);
        } else {
            resultado.registrarResultado(false);
        }
    }
}