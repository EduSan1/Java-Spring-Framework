package com.example.screenmatch;

import com.example.screenmatch.useCase.series.FindSeriesByTitleWithSeasonsUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	private FindSeriesByTitleWithSeasonsUseCase findSeriesByTitleUseCase = new FindSeriesByTitleWithSeasonsUseCase();

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var series = findSeriesByTitleUseCase.execute();
		System.out.println("Series \"" + series.getTitle() + "\"");

		AtomicInteger totalEpisodes = new AtomicInteger();
		series.getSeasons().forEach(season -> {
			totalEpisodes.addAndGet(season.episodes().size());
			System.out.println("---------- Season " + season.number() + " -----------");
			season.episodes().forEach(episode -> {
				System.out.println(episode.number() + " - " + episode.title());
			});
		});
		System.out.println("total de epideos: " + totalEpisodes);
	}
}
