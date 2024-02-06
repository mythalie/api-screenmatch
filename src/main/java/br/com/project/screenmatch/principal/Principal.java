package br.com.project.screenmatch.principal;

import br.com.project.screenmatch.service.ApiConsumption;
import br.com.project.screenmatch.domain.entity.Episode;
import br.com.project.screenmatch.domain.entity.Serie;
import br.com.project.screenmatch.model.SeasonData;
import br.com.project.screenmatch.model.ShowData;
import br.com.project.screenmatch.repository.SerieRepository;
import br.com.project.screenmatch.service.ConvertDataService;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=6585022c";
    private Scanner reading = new Scanner(System.in);
    private ApiConsumption apiConsumption = new ApiConsumption();
    private ConvertDataService convert = new ConvertDataService();
    private SerieRepository repository;
    private List<Serie> series = new ArrayList<>();

    public Principal(SerieRepository repository) {
        this.repository = repository;
    }

    public void displayMenu() {
        var option = -1;

        while (option != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    4 - Top 5 séries
                                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            option = reading.nextInt();
            reading.nextLine();

            switch (option) {
                case 1:
                    searchSerie();
                    break;
                case 2:
                    searchEpisodesBySeries();
                    break;
                case 3:
                    listSearchedSeries();
                    break;
                case 4:
                    listTop5Series();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void searchSerie() {
        ShowData showData = getShowData();
        Serie serie = new Serie(showData);
        repository.save(serie);
        System.out.println(showData);
    }

    private ShowData getShowData() {
        System.out.println("Digite o nome da série para buscar");
        var seriesName = reading.nextLine();
        var json = apiConsumption.getData(ADDRESS + seriesName.replace(" ", "+") + API_KEY);
        return convert.getData(json, ShowData.class);
    }

    private void searchEpisodesBySeries(){
        listSearchedSeries();
        System.out.println("\nEscolha uma série para buscar os episódios: ");
        var serieName = reading.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitle().toLowerCase().contains(serieName.toLowerCase()))
                .findFirst();

        serie.ifPresentOrElse(this::fetchAndSaveEpisodes, () -> System.out.println("Série não encontrada!"));
    }

    private void fetchAndSaveEpisodes(Serie seriesFound) {
        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= seriesFound.getTotalSeasons(); i++) {
            String json = apiConsumption.getData(ADDRESS + seriesFound.getTitle().replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData seasonData = convert.getData(json, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
                .flatMap(d -> d.episodes().stream()
                        .map(e -> new Episode(d.number(), e)))
                .collect(Collectors.toList());

        seriesFound.setEpisodes(episodes);
        repository.save(seriesFound);
    }

    private void listSearchedSeries() {
        series = repository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);
    }

    private void listTop5Series() {
        List<Serie> top5Series = repository.findTop5ByOrderByRatingDesc();
        top5Series.forEach(s ->
                System.out.println(s.getTitle() + " avaliação: " + s.getRating()));
    }
}