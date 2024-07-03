package com.example.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeriesModel {
    @JsonAlias("Title")
    private String title;

    @JsonAlias("totalSeasons")
    private Integer totalSeasons;

    @JsonAlias("imdbRating")
    private Double rating;

    @JsonAlias("imdbID")
    private String id;

    private List<SeasonModel> seasons;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SeasonModel> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonModel> seasons) {
        this.seasons = seasons;
    }
}
