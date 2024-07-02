package com.example.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesModel(@JsonAlias("Title") String title,
                          @JsonAlias("totalSeasons") Integer totalSeasons,
                          @JsonAlias("imdbRating") Double rating) {
}