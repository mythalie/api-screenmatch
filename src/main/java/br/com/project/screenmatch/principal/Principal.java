package br.com.project.screenmatch.principal;

import br.com.project.screenmatch.model.SeasonData;
import br.com.project.screenmatch.model.ShowData;
import br.com.project.screenmatch.service.ApiConsumptionService;
import br.com.project.screenmatch.service.ConvertDataService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner reading = new Scanner(System.in);
    private ApiConsumptionService apiConsumption = new ApiConsumptionService();
    private ConvertDataService convert = new ConvertDataService();
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    public void displayMenu() {
        while (true) {
            System.out.println("Digite o nome da série: ");
            var seriesName = reading.nextLine();

            try {
                // Substitui espaços em branco por "+"
                String encodedSeriesName = URLEncoder.encode(seriesName.replace(" ", "+"), StandardCharsets.UTF_8.toString());

                var showJson = apiConsumption.getData(ADDRESS + encodedSeriesName + API_KEY);
                ShowData showData = convert.getData(showJson, ShowData.class);

                // Verifica se o objeto ShowData e totalSeasons não são nulos
                if (showData != null && showData.totalSeasons() != null) {
                    System.out.println(showData);

                    List<SeasonData> seasons = new ArrayList<>();

                    for (int i = 1; i <= showData.totalSeasons().intValue(); i++) {
                        var seasonJson = apiConsumption.getData(ADDRESS + encodedSeriesName + "&season=" + i + API_KEY);
                        SeasonData seasonData = convert.getData(seasonJson, SeasonData.class);
                        seasons.add(seasonData);
                    }
                    seasons.forEach(System.out::println);

                    seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));

                    // Se o nome da série foi encontrado e as condições foram satisfeitas, sai do loop
                    break;
                } else {
                    System.out.println("Série não encontrada ou informações incompletas. Por favor, digite um nome de série existente.");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}