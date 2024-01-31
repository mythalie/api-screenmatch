package br.com.project.screenmatch;

import br.com.project.screenmatch.model.EpisodeData;
import br.com.project.screenmatch.model.ShowData;
import br.com.project.screenmatch.service.ApiConsumptionService;
import br.com.project.screenmatch.service.ConvertDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var apiConsumption = new ApiConsumptionService();
		ConvertDataService convert = new ConvertDataService();

		var showJson = apiConsumption.getData("https://www.omdbapi.com/?t=game+of+thrones&apikey=6585022c");
		ShowData showData = convert.getData(showJson, ShowData.class);
		System.out.println(showData);

		var episodeJson = apiConsumption.getData("https://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=6585022c");
		EpisodeData episodeData = convert.getData(episodeJson, EpisodeData.class);
		System.out.println(episodeData);
	}
}
