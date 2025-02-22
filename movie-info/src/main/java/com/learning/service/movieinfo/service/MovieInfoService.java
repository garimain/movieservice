package com.learning.service.movieinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.learning.service.movieinfo.common.exception.MovieNotAddedException;
import com.learning.service.movieinfo.common.exception.MovieNotFoundException;
import com.learning.service.movieinfo.common.exception.MovieNotUpdatedException;
import com.learning.service.movieinfo.model.MovieBO;
import com.learning.service.movieinfo.repository.MovieRepository;
import com.learning.service.movieinfo.repository.model.Movie;

@Service
@RefreshScope
public class MovieInfoService {
	
	Logger logger = LoggerFactory.getLogger(MovieInfoService.class);
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Value("${movie-info.screenType}")
	private String screenType;
	
	public MovieBO getMovieDetails(Integer movieId) throws MovieNotFoundException {
		
		logger.info("MovieInfoService is called for retrieval with id as  {} ", movieId);
		
		Optional<Movie> movieOptional = movieRepository.findById(movieId);
		
		if (movieOptional.isPresent()) {
			Movie movie = movieOptional.get();
			return new MovieBO(movie.getMovieId(), movie.getName(), movie.getInformation(), movie.getScreenType());
		} else {
			throw new MovieNotFoundException("MovieForSearhNotFound");
		}
	}
	
	public List<MovieBO> getAllMovies() {
		
		
		List<Movie> movies = movieRepository.findAll();
		
		List<MovieBO> moviesBOList = movies.stream().map(m->new MovieBO(m.getMovieId(), m.getName(), m.getInformation(), m.getScreenType()))
				.collect(Collectors.toList());
		
		return moviesBOList;
	}
	
	public List<MovieBO> getMovies(String movieIds)  {
		
		String[] movieIdsStr = movieIds.split(",");
		List<Integer> movieIdentifiers =  new ArrayList<Integer>();
		
		for (String s : movieIdsStr) {
			movieIdentifiers.add(Integer.valueOf(s));
		}
		
		List<Movie> movies = movieRepository.findAllById(movieIdentifiers);
		
		List<MovieBO> moviesBOList = movies.stream().map(m->new MovieBO(m.getMovieId(), m.getName(), m.getInformation(), m.getScreenType()))
				.collect(Collectors.toList());
		
		return moviesBOList;
	}
	
	
	
	public MovieBO updateMovie(MovieBO movieBO) throws MovieNotFoundException {
		
	    
		MovieBO savedMovieBO = null;
		if (movieBO.getMovieId() !=null) {
			
			logger.info("MovieInfoService is called for updation with id as  {} ", movieBO.getMovieId());
			
		
			Optional<Movie> movieOptional = movieRepository.findById(movieBO.getMovieId());
			
			if (movieOptional.isPresent()) {
				Movie movie = movieOptional.get();
				movie.setName(movieBO.getName());
				movie.setInformation(movieBO.getInformation());
				
				if (movieBO.getScreenType() !=null) {
					movie.setScreenType(movieBO.getScreenType());
				} else {
					movie.setScreenType(screenType);
				}
				
				Movie savedMovie = movieRepository.save(movie);
				
				savedMovieBO = new MovieBO(savedMovie.getMovieId(), savedMovie.getName(), savedMovie.getInformation(), savedMovie.getScreenType());
			} else {
				logger.info("MovieInfoService throws an exception - MovieForUpdateNotFound");
				
				throw new MovieNotUpdatedException("MovieForUpdateNotFound");
			}
		}
		
		return savedMovieBO;
		
	}
	
	public MovieBO addMovie(MovieBO movieBO) {
		
		Movie movie = new Movie();
		movie.setName(movieBO.getName());
		movie.setInformation(movieBO.getInformation());
		
		if (movieBO.getScreenType() !=null) {
			movie.setScreenType(movieBO.getScreenType());
		} else {
			movie.setScreenType(screenType);
		}
		
		Movie savedMovie = movieRepository.save(movie);
		if (savedMovie != null) {
			logger.info("MovieInfoService addMovie returns - savedMovie with id {}", savedMovie.getMovieId() );
		
			return new MovieBO(savedMovie.getMovieId(), savedMovie.getName(), savedMovie.getInformation(), savedMovie.getScreenType());
		} else {
			throw new MovieNotAddedException("MovieNotAdded");
		}
		
		
	}
	
	public void deleteMovie(Integer movieId) {
		
		
		logger.info("MovieInfoService is called for deletion with id as  {} ", movieId);
		
		Optional<Movie> movieOptional = movieRepository.findById(movieId);
		
		if (movieOptional.isPresent()) {
		
			movieRepository.deleteById(movieId);
		} else {
			throw new MovieNotFoundException("MovieForDeleteNotFound");
		}
		
	}
	

}
