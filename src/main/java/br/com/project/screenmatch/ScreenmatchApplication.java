package br.com.project.screenmatch;

import br.com.project.screenmatch.model.SeriesData;
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
		var json = apiConsumption.getData("https://www.omdbapi.com/?t=game+of+thrones&apikey=6585022c");
		System.out.println(json);
		ConvertDataService convert = new ConvertDataService();
		SeriesData data = convert.getData(json, SeriesData.class);
		System.out.println(data);
	}
}
