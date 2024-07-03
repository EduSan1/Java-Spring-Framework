package com.example.screenmatch.useCase.season;

import com.example.screenmatch.model.SeasonModel;
import com.example.screenmatch.service.ApiService;
import com.example.screenmatch.service.MapperService;

public class FindSeasonBySeriesIdAndNumberUseCase {
    private MapperService mapper = new MapperService();

    private final String ADDRESS = "http://www.omdbapi.com/?i=";
    private final String API_KEY = "&apikey=44030e72";

    public SeasonModel execute(String seriesId, Integer season) {
        var apiService = new ApiService();
        var data = apiService.getData(ADDRESS + seriesId + "&season=" + season + API_KEY);
        return mapper.getData(data, SeasonModel.class);
    }
}
