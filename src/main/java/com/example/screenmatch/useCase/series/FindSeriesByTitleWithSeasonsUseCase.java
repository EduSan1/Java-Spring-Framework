package com.example.screenmatch.useCase.series;

import com.example.screenmatch.model.SeasonModel;
import com.example.screenmatch.model.SeriesModel;
import com.example.screenmatch.service.ApiService;
import com.example.screenmatch.service.MapperService;
import com.example.screenmatch.useCase.season.FindSeasonBySeriesIdAndNumberUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FindSeriesByTitleWithSeasonsUseCase {
    private Scanner scanner = new Scanner(System.in);

    private MapperService mapper = new MapperService();

    private FindSeasonBySeriesIdAndNumberUseCase findSeasonBySeriesIdAndNumberUseCase = new FindSeasonBySeriesIdAndNumberUseCase();

    private final String ADDRESS = "http://www.omdbapi.com/?T=";

    private final String API_KEY = "&apikey=44030e72";

    public SeriesModel execute() {
        System.out.println("Digite o nome da série");
        var seriesName = scanner.nextLine();

        var apiService = new ApiService();
        var data = apiService.getData(ADDRESS + seriesName.replace(" ", "+")  + API_KEY);
        SeriesModel series = mapper.getData(data, SeriesModel.class);

        if (Objects.isNull(series.getId()))
            throw new RuntimeException("Serie não encontrada");

        series.setSeasons(this.findSeasons(series));
        return series;
    }

    private List<SeasonModel> findSeasons(SeriesModel series) {
        var seasons = new ArrayList<SeasonModel>();
        for (int index = 1; index <= series.getTotalSeasons(); index++)
            seasons.add(findSeasonBySeriesIdAndNumberUseCase.execute(series.getId(), index));

        return seasons;
    }
}
