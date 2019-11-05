package com.learning.service.movieinfo.model;

import org.junit.Assert;
import org.junit.Test;

import com.learning.service.movieinfo.repository.model.Movie;



public class MovieBOTest {
	
	@Test
	public void addNewMovieTest() {
		
		Movie movie = new Movie(Integer.getInteger("1"), "PS: I don't love you", "Same old love bullshit");
		
		Assert.assertEquals(movie.getMovieId(), Integer.getInteger("1"));
		
	}
	

}
