package com.learning.service.movieinfo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieError {
	
	private String code;
	private String message;
	
	

}
