package com.qa.cinema.business.repository;

import com.qa.cinema.persistance.*;
import com.qa.cinema.util.JSONUtil;
import java.util.List;
import javax.persistence.*;
import javax.transaction.*;

@Transactional(Transactional.TxType.SUPPORTS)

public class MovieServiceDBImpl {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	private JSONUtil util;

	public Movie findAMovie(Long id) {
		Movie foundMovie = manager.find(Movie.class, id);
		return foundMovie;
	}

	public List<Movie> getAllMovies() {
		TypedQuery<Movie> query = manager.createQuery("SELECT m FROM Movie m ORDER BY m.title DESC", Movie.class);
		return query.getResultList();
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void createMovie(String movie) {
		Movie aMovie = util.getObjectForJSON(movie,Movie.class);
		manager.persist(aMovie);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void updateMovie(Long id, String newDetails) {
		Movie newMovieDetails = util.getObjectForJSON(newDetails, Movie.class);
		Movie currentMovie = manager.find(Movie.class, id);
		if(newMovieDetails != null) {
			currentMovie = newMovieDetails;
		}
		manager.merge(currentMovie);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void deleteMovie(Long id) {
		manager.remove(manager.find(Movie.class, id));
	}
}
