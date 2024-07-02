package com.example.screenmatch;

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
		var data = apiService.getData("http://www.omdbapi.com/?i=tt3896198&apikey=44030e72&t=Chuck&y=2007&plot=full");

		MapperService mapper = new MapperService();
		SeriesModel series = mapper.getData(data, SeriesModel.class);
		System.out.println(series);
	}
}
