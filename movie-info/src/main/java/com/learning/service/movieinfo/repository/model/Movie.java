package com.learning.service.movieinfo.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer movieId;
	private String name;
	private String information;
	
		
	

}
