package com.learning.service.movieinfo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learning.service.movieinfo.model.MovieBO;
import com.learning.service.movieinfo.model.MovieInfoSearchResponseBO;
import com.learning.service.movieinfo.service.MovieInfoService;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
	
	Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private MovieInfoService movieInfoService;
	
	
	@RequestMapping(path = "/movie/{movieId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public MovieBO getMovie(@PathVariable(name = "movieId") String movieId) {
		
		logger.info("Movie service is called for retrieval with id as  {} ", movieId);
		
		MovieBO movieBO =  movieInfoService.getMovieDetails(Integer.valueOf(movieId));
		return movieBO;
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public MovieInfoSearchResponseBO getAllMovies(@RequestParam (name = "movieIds", required = false ) String movieIds) {
		
		logger.info("Movie service is called movies with parameters  {} " + movieIds);
		List<MovieBO> movies = null;
		MovieInfoSearchResponseBO movieInfoSearchResponse = new MovieInfoSearchResponseBO();
		
		if (movieIds != null) {
			movies = movieInfoService.getMovies(movieIds);
		} else {
			movies = movieInfoService.getAllMovies();
		}
		
		movieInfoSearchResponse.setMovieList(movies);
		
		return movieInfoSearchResponse;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/movie")
	@ResponseStatus(HttpStatus.CREATED)
	public MovieBO addMovie(@RequestBody MovieBO movieBO) {
		
		logger.info("Movie service is called for adding a new movie with details {} ", movieBO.toString());
		
		MovieBO savedMovieBO = movieInfoService.addMovie(movieBO);
		
		return savedMovieBO;
		
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/movie")
	@ResponseStatus(HttpStatus.OK)
	public MovieBO updateMovie(@RequestBody MovieBO movieBO) {
		
		logger.info("Movie service is called for updating an existing movie with details {}", movieBO);
		
		MovieBO savedMovieBO = movieInfoService.updateMovie(movieBO);
		
		return savedMovieBO;
		
	}
	
	
	@RequestMapping(path = "/movie/{movieId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteMovie(@PathVariable(name = "movieId") String movieId) {
		
		logger.info("Movie service is called for deleting an existing movie with id {}", movieId);
		
		movieInfoService.deleteMovie(Integer.valueOf(movieId));
		
		
	}
	
	

}
