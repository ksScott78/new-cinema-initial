package com.qa.cinema.intergration;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import com.qa.cinema.business.repository.*;
import com.qa.cinema.persistance.Movie;
import com.qa.cinema.util.*;



@Path("/cinema")

public class MovieEndpoint {
	
	@Inject
	private MovieServiceDBImpl MovieService;
	@Inject
	private JSONUtil util;
	
	@GET
	@Path("/collection/{id}")
	public String findMovie(@PathParam("id") Long id) {
		Movie movie = MovieService.findAMovie(id);
		return util.getJSONForObject(movie);
	}
	
	@GET
	@Path("/json")
	public String findMovies() {
	List<Movie>movieMap = MovieService.getAllMovies();
	return util.getJSONForObject(movieMap);
	}
	
	@DELETE
	@Path("/json/{id}")
	public void deleteMovie(@PathParam("id") Long id) {
		MovieService.deleteMovie(id);
	}
	
	
	@PUT
	@Path("/json/{id}")
	public void updateMovie(@PathParam("id")Long id, String newDetails) {
		MovieService.updateMovie(id, newDetails);
	}
	
	@POST
	@Path("/json")
	public void createMovie(String newMovie) {
		MovieService.createMovie(newMovie);
	}
}
