package com.learning.service.movieinfo.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.service.movieinfo.repository.model.Movie;



@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieInfoRepositoryTest {

	@Autowired
	private TestEntityManager tem;
	
	@Autowired
	private MovieRepository repository;
	
	@Test
	public void addMovie() {
		Movie movie = new Movie (null,  "PS: Hello World", "Its a great world", "Small");
		
		Movie persistentMovie = tem.persistFlushFind(movie);
		
		Assert.assertTrue(persistentMovie.getMovieId() > 0);
		Assert.assertEquals(persistentMovie.getName(), movie.getName());
		
	}
	
	@Test
	public void findByMovieName() {
		Movie persistentMovie = repository.save(new Movie (null,  "PS: Hello World", "Its a great world", "Medium"));
		
		Movie searchedMovie = repository.findByName("PS: Hello World");
		
		Assert.assertEquals(searchedMovie.getMovieId(), persistentMovie.getMovieId());
		Assert.assertEquals(searchedMovie.getInformation(), persistentMovie.getInformation());
		
		
	}
	@Test
	public void findByMovieInformation() {
		Movie persistentMovie = repository.save(new Movie (null,  "PS: Hello World", "Its a great world", "Small"));
		
		Movie searchedMovie = repository.findByInformation("Its a great world");
		
		Assert.assertEquals(searchedMovie.getMovieId(), persistentMovie.getMovieId());
		Assert.assertEquals(searchedMovie.getInformation(), persistentMovie.getInformation());
		
		
	}
	
	
	
}
