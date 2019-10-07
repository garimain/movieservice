package com.learning.service.movieinfo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.service.movieinfo.common.exception.MovieNotFoundException;
import com.learning.service.movieinfo.model.MovieBO;
import com.learning.service.movieinfo.repository.MovieRepository;
import com.learning.service.movieinfo.repository.model.Movie;

@Service
public class MovieInfoService {
	
	Logger logger = LoggerFactory.getLogger(MovieInfoService.class);
	
	@Autowired
	private MovieRepository movieRepository;
	
	public MovieBO getMovieDetails(Integer movieId) throws MovieNotFoundException {
		
		logger.info("MovieInfoService is called for retrieval with id as  {} ", movieId);
		
		Optional<Movie> movieOptional = movieRepository.findById(movieId);
		
		if (movieOptional.isPresent()) {
			Movie movie = movieOptional.get();
			return new MovieBO(movie.getMovieId(), movie.getName(), movie.getInformation());
		} else {
			throw new MovieNotFoundException("MovieForSearhNotFound");
		}
	}
	
	public List<MovieBO> getAllMovies() throws MovieNotFoundException {
		
		List<Movie> movies = movieRepository.findAll();
		
		List<MovieBO> moviesBOList = movies.stream().map(m->new MovieBO(m.getMovieId(), m.getName(), m.getInformation()))
				.collect(Collectors.toList());
		
		return moviesBOList;
	}
	
	public MovieBO updateMovie(MovieBO movieBO) throws MovieNotFoundException {
		
		MovieBO savedMovieBO = null;
		if (movieBO.getMovieId() !=null) {
		
			Optional<Movie> movieOptional = movieRepository.findById(movieBO.getMovieId());
			
			if (movieOptional.isPresent()) {
				Movie movie = movieOptional.get();
				movie.setName(movieBO.getName());
				movie.setInformation(movieBO.getInformation());
				Movie savedMovie = movieRepository.save(movie);
				
				savedMovieBO = new MovieBO(savedMovie.getMovieId(), savedMovie.getName(), savedMovie.getInformation());
			} else {
				throw new MovieNotFoundException("MovieForUpdateNotFound");
			}
		}
		
		return savedMovieBO;
		
	}
	
	public MovieBO addMovie(MovieBO movieBO) {
		
		Movie movie = new Movie();
		movie.setName(movieBO.getName());
		movie.setInformation(movieBO.getInformation());
		Movie savedMovie = movieRepository.save(movie);
		
		return new MovieBO(savedMovie.getMovieId(), savedMovie.getName(), savedMovie.getInformation());
		
		
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
