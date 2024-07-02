package com.example.screenmatch;

import com.example.screenmatch.model.EpisodeModel;
import com.example.screenmatch.model.SeasonModel;
import com.example.screenmatch.model.SeriesModel;
import com.example.screenmatch.service.ApiService;
import com.example.screenmatch.service.MapperService;
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
		var apiService = new ApiService();
		MapperService mapper = new MapperService();

		var seriesJson = apiService.getData("http://www.omdbapi.com/?i=tt0934814&apikey=44030e72");
		SeriesModel series = mapper.getData(seriesJson, SeriesModel.class);

		System.out.println("Series \"" + series.title() + "\"");
		for (int index = 1; index <= series.totalSeasons(); index++) {
			var seasonJson = apiService.getData("http://www.omdbapi.com/?i=tt0934814&apikey=44030e72&season=" + index);
			SeasonModel season = mapper.getData(seasonJson, SeasonModel.class);

			System.out.println("---------- Season " + season.number() + " -----------");
			season.episodes().forEach(episode -> {
				System.out.println(episode.number() + " - " + episode.title());
			});
		}
	}
}
