package org.pesquisa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WebScraper {

    public static List<String> procurar(String termo, String pagina) {
        String termoProcurado = termo.trim().toLowerCase();
        List<String> trechosEncontrados = new ArrayList<>();

        try {
            Document documento = Jsoup.connect(pagina)
                    .timeout(10000)
                    .get();
            String textoPagina = documento.text().toLowerCase();

            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(termoProcurado) + "\\b");
            if (pattern.matcher(textoPagina).find()) {
                System.out.println("Termo encontrado na página: " + pagina);

                Elements elementos = documento.getElementsContainingOwnText(termoProcurado);
                for (Element elemento : elementos) {
                    String trecho = elemento.text();
                    trechosEncontrados.add(trecho);
                    System.out.println("Trecho encontrado: " + trecho);
                }
                System.out.println("-------------------------------");
            } else {
                System.out.println("Termo não encontrado na página: " + pagina);
            }
        } catch (IOException e) {
            System.err.println("Erro ao acessar a página: " + pagina + " - " + e.getMessage());
        }

        return trechosEncontrados;
    }
}