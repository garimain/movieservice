package com.learning.service.movieinfo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.service.movieinfo.common.exception.MovieNotFoundException;
import com.learning.service.movieinfo.model.MovieBO;
import com.learning.service.movieinfo.repository.MovieRepository;
import com.learning.service.movieinfo.repository.model.Movie;



@RunWith(SpringRunner.class)
public class MovieInfoServiceTest {
	
	@MockBean
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieInfoService movieService;
	
	
	@TestConfiguration
	public static class MovieInfoServiceConfiguration {
		
		@Bean
		public MovieInfoService getMovieService() {
			return new MovieInfoService();
		}
		
	}
	
	@Test
	public void getAllMoviesTest() {
		
		Mockito.when(movieRepository.findAll())
			    .thenReturn(Collections.singletonList(new Movie(Integer.valueOf("1"), "new Movie", "Some movie description")));
		
		
		List<MovieBO> movieList = movieService.getAllMovies();
		
		Assert.assertEquals(movieList.size(), 1);
		
		
		
	}
	
	
	//@Test
	public void getMovieDetailsFoundTest() {
		
		Optional<Movie> movie = Optional.of(new Movie(Integer.valueOf("1"), "new Movie", "Some movie description"));
		
		Mockito.when(movieRepository.findById(1)).thenReturn(movie);
		
		MovieBO movieObj = movieService.getMovieDetails(Integer.valueOf("1"));
		
		Assert.assertEquals(movieObj.getMovieId(), Integer.valueOf("1"));
		
		
		
	}
	
	
	//@Test(expected = MovieNotFoundException.class)
	public void getMovieDetailsNotFoundTest() { 
		
		Optional<Movie> movie = Optional.empty();
		
		Mockito.when(movieRepository.findById(1)).thenReturn(movie);
		
		movieService.getMovieDetails(Integer.valueOf("1"));
		
	}
	
	
	//@Test
	public void updateMovieDetailsFoundTest() {
		
		Optional<Movie> movie = Optional.of(new Movie(Integer.valueOf("1"), "new Movie", "Some movie description"));
		
		Mockito.when(movieRepository.findById(1)).thenReturn(movie);
		
		Mockito.when(movieRepository.save(movie.get())).thenReturn(movie.get());
		
		
		MovieBO movieBO = new MovieBO(Integer.valueOf("1"), "new Movie 2", "Some movie description 2");
		
		
		MovieBO movieObj = movieService.updateMovie(movieBO);
		
		Assert.assertEquals(movieObj.getName(), "new Movie 2");
		Assert.assertEquals(movieObj.getInformation(), "Some movie description 2");
		
		
	}
	
	//@Test (expected = MovieNotFoundException.class)
	public void updateMovieDetailsNotFoundTest() {
		
		Optional<Movie> movie = Optional.empty();
		
		Mockito.when(movieRepository.findById(1)).thenReturn(movie);
		
		
		MovieBO movieBO = new MovieBO(Integer.valueOf("1"), "new Movie 2", "Some movie description 2");
		
		
		MovieBO movieObj = movieService.updateMovie(movieBO);
		
		Assert.assertEquals(movieObj.getName(), "new Movie 2");
		Assert.assertEquals(movieObj.getInformation(), "Some movie description 2");
		
		
	}
	
	
	//@Test
	public void addMovieTest() {
		
		Movie movie = new Movie(Integer.valueOf("1"), "new Movie", "Some movie description");
		
		Mockito.when(movieRepository.save(movie)).thenReturn(movie);
	
		
		MovieBO movieBO = new MovieBO(Integer.valueOf("1"), "new Movie", "Some movie description");
		
		MovieBO movieObj = movieService.addMovie(movieBO);
		
		
		Assert.assertEquals(movieObj.getName(), "new Movie");
		Assert.assertEquals(movieObj.getInformation(), "Some movie description");
		
		
		
		
	}
	
	
	
	
	
	

}
