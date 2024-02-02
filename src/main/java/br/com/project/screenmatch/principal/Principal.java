package br.com.project.screenmatch.principal;

import br.com.project.screenmatch.model.SeasonData;
import br.com.project.screenmatch.model.Serie;
import br.com.project.screenmatch.model.ShowData;
import br.com.project.screenmatch.service.ApiConsumptionService;
import br.com.project.screenmatch.service.ConvertDataService;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=6585022c";

    private Scanner reading = new Scanner(System.in);
    private ApiConsumptionService apiConsumption = new ApiConsumptionService();
    private ConvertDataService convert = new ConvertDataService();
    private List<ShowData> showData = new ArrayList<>();

    public void displayMenu() {
        var option = -1;

        while (option != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            option = reading.nextInt();
            reading.nextLine();

            switch (option) {
                case 1:
                    getSerie();
                    break;
                case 2:
                    getEpisodeBySeries();
                    break;
                case 3:
                    getSearchedSeries();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void getSerie() {
        ShowData data = getShowData();
        showData.add(data);
        System.out.println(data);
    }

    private ShowData getShowData() {
        System.out.println("Digite o nome da série para buscar");
        var seriesName = reading.nextLine();
        var json = apiConsumption.getData(ADDRESS + seriesName.replace(" ", "+") + API_KEY);
        return convert.getData(json, ShowData.class);
    }

    private void getEpisodeBySeries(){
        ShowData data = getShowData();
        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= data.totalSeasons(); i++) {
            var json = apiConsumption.getData(ADDRESS + data.title().replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData seasonData = convert.getData(json, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);
    }

    private void getSearchedSeries() {
        List<Serie> series = new ArrayList<>();
        series = showData.stream()
                .map(d -> new Serie(d))
                .collect(Collectors.toList());
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);
    }
}