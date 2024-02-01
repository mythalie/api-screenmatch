package br.com.project.screenmatch.principal;

import br.com.project.screenmatch.model.Episode;
import br.com.project.screenmatch.model.EpisodeData;
import br.com.project.screenmatch.model.SeasonData;
import br.com.project.screenmatch.model.ShowData;
import br.com.project.screenmatch.service.ApiConsumptionService;
import br.com.project.screenmatch.service.ConvertDataService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=6585022c";

    private Scanner reading = new Scanner(System.in);
    private ApiConsumptionService apiConsumption = new ApiConsumptionService();
    private ConvertDataService convert = new ConvertDataService();

    /*Método displayMenu:
        Este método representa o menu principal do programa, onde o usuário é solicitado a digitar o nome da série.
        O nome da série é então codificado utilizando o método encodeSeriesName.
        A chamada ao método getShowData obtém os dados da série da API.
        Se os dados da série e o número total de temporadas não são nulos, o método displayShowInfo é chamado. Caso contrário, uma mensagem de erro é exibida.*/
    public void displayMenu() {
        while (true) {
            System.out.println("Digite o nome da série:");
            var seriesName = reading.nextLine();

            try {
                String encodedSeriesName = encodeSeriesName(seriesName);

                ShowData showData = getShowData(encodedSeriesName);

                // Verifica se o objeto ShowData e totalSeasons não são nulos
                if (showData != null && showData.totalSeasons() != null) {
                    displayShowInfo(showData, encodedSeriesName);
                    break;
                } else {
                    System.out.println("Série não encontrada ou informações incompletas. Por favor, digite um nome de série existente.");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    /*Método encodeSeriesName:
        Este método substitui espaços em branco por "+" no nome da série e o codifica.*/
    private String encodeSeriesName(String seriesName) throws UnsupportedEncodingException {
        return URLEncoder.encode(seriesName.replace(" ", "+"), StandardCharsets.UTF_8.toString());
    }

    /*Método getShowData:
        Este método faz uma chamada à API usando a URL construída com o nome da série e a chave da API.
        Em seguida, converte a resposta JSON para um objeto ShowData usando o serviço de conversão.*/
    private ShowData getShowData(String encodedSeriesName) {
        String showJson = apiConsumption.getData(ADDRESS + encodedSeriesName + API_KEY);
        return convert.getData(showJson, ShowData.class);
    }

    /*Método displayShowInfo:
        Este método exibe informações sobre a série, como detalhes gerais e informações sobre cada temporada.*/
    private void displayShowInfo(ShowData showData, String encodedSeriesName) {
        System.out.println(showData);

        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= showData.totalSeasons().intValue(); i++) {
            var seasonJson = apiConsumption.getData(ADDRESS + encodedSeriesName + "&season=" + i + API_KEY);
            SeasonData seasonData = convert.getData(seasonJson, SeasonData.class);
            seasons.add(seasonData);
        }
        displayEpisodes(seasons);
    }

    /*Método displayEpisodes:
        Coordena a exibição de diferentes informações sobre os episódios, chamando métodos auxiliares específicos para cada caso.*/
    private void displayEpisodes(List<SeasonData> seasons) {
        List<EpisodeData> episodeData = seasons.stream()
                .flatMap(t -> t.episodes().stream())
                .collect(Collectors.toList());

        displayTopEpisodes(episodeData);
        displayAllEpisodes(seasons.stream()
                .flatMap(t -> t.episodes().stream()
                        .map(d -> new Episode(t.number(), d)))
                        .collect(Collectors.toList()));
        displayEpisodesByYear(seasons.stream()
                .flatMap(t -> t.episodes().stream()
                        .map(d -> new Episode(t.number(), d)))
                .collect(Collectors.toList()));

        displayEpisodesByTitle(seasons.stream()
                .flatMap(t -> t.episodes().stream()
                        .map(d -> new Episode(t.number(), d)))
                .collect(Collectors.toList()));
    }

    /*Método displayAllEpisodes:
        Exibe todas as temporadas e episódios.*/
    private void displayAllEpisodes(List<Episode> episodes) {
        System.out.println("\nTodos os episódios:");
        for (Episode e : episodes) {
            System.out.println(e);
        }
        System.out.println();
    }

    /*Método displayEpisodes:
        Exibe os cinco melhores episódios*/
    private void displayTopEpisodes(List<EpisodeData> episodeData) {
        System.out.println("\nTop 5 episódios:");
        episodeData.stream()
                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeData::rating).reversed())
                .limit(5)
                .map(EpisodeData::title)
                .forEach(System.out::println);
    }

    /*Método episodeYear:
        Busca os episódios a partir de uma data.*/
    private void displayEpisodesByYear (List<Episode> episodes) {
        System.out.println("A partir de que ano você deseja ver os episódios?");
        var year = reading.nextInt();
        reading.nextLine();

        LocalDate search = LocalDate.of(year, 1, 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodes.stream()
                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(search))
                .forEach(e -> System.out.println(e.getFormattedString()));
        System.out.println();
    }

    /*Método getEpisode:
        Busca os episódios a partir de um título.*/
    private void displayEpisodesByTitle(List<Episode> episodes) {
        System.out.println("Digite uma parte do título do episódio:");
        var titleExcerpt = reading.nextLine();

        List<Episode> episodesFound = episodes.stream()
                .filter(e -> e.getTitle().toUpperCase().contains(titleExcerpt.toUpperCase()))
                .collect(Collectors.toList());

        if (!episodesFound.isEmpty()) {
            System.out.println("Episódios encontrados:");

            // Agrupa os episódios pelo título
            Map<String, List<Episode>> groupedEpisodes = episodesFound.stream()
                    .collect(Collectors.groupingBy(Episode::getTitle));

            // Para cada título exibe as informações
            groupedEpisodes.forEach((title, episodeList) -> {
                System.out.println("Título: " + title);
                episodeList.forEach(episode -> {
                    System.out.println("Temporada: " + episode.getSeason());
                });
            });
        }
    }
}