package com.example.screenmatch;

import com.example.screenmatch.model.SeriesModel;
import com.example.screenmatch.useCase.series.FindSeriesByTitleWithSeasonsUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	private Scanner scanner = new Scanner(System.in);
	private FindSeriesByTitleWithSeasonsUseCase findSeriesByTitleUseCase = new FindSeriesByTitleWithSeasonsUseCase();

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) {
		boolean proceed = true;

		while (proceed) {
			this.searchSeries();
			System.out.println("\nDeseja fazer outra busca? (s/n): ");
			String response = scanner.nextLine().trim().toLowerCase();
			if (!response.equals("s")) {
				proceed = false;
			}
		}
	}

	private void searchSeries() {
		try {
			var series = findSeriesByTitleUseCase.execute();
			System.out.println("Nome da serie: \"" + series.getTitle() + "\"");

			AtomicInteger totalEpisodes = new AtomicInteger();
			series.getSeasons().forEach(season -> {
				totalEpisodes.addAndGet(season.episodes().size());
				System.out.println("\n---------- Temporada " + season.number() + " -----------");
				season.episodes().forEach(episode -> {
					System.out.println(episode.number() + " - " + episode.title());
				});
			});
			System.out.println("Total de episódios: " + totalEpisodes);
		} catch (RuntimeException e) {
			System.out.println("********* " + e.getMessage() + " *********");
		}
	}
}
