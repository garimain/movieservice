package com.learning.service.movieinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.service.movieinfo.repository.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository <Movie, Integer> {
	
	public Movie findByName(String name);
	public Movie findByInformation(String information);
	
	

}
