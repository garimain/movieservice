create table movie (

	movie_id INT(11) NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	information VARCHAR(50) NULL,
	screen_type VARCHAR(50) NULL,
	CONSTRAINT movie_pk PRIMARY KEY (movie_id)

);

