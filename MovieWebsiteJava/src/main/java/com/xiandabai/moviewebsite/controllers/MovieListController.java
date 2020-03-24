package com.xiandabai.moviewebsite.controllers;

import com.xiandabai.moviewebsite.domain.Movie;
import com.xiandabai.moviewebsite.domain.MovieList;
import com.xiandabai.moviewebsite.repositories.MovieListRepository;
import com.xiandabai.moviewebsite.services.MovieListService;
import com.xiandabai.moviewebsite.services.MovieService;
import com.xiandabai.moviewebsite.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/movielist")
@CrossOrigin
public class MovieListController {

    @Autowired
    MovieListService movieListService;

    @Autowired
    ValidationErrorService validationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewMovie(@Valid @RequestBody MovieList movieList, BindingResult result) {

        ResponseEntity<?> error = validationErrorService.MapValidationService(result);

        if (error != null)
            return error;

        MovieList l = movieListService.saveOrUpdateMovieList(movieList);
        return new ResponseEntity<MovieList>(l, HttpStatus.OK);
    }

    @GetMapping("/{movieList_id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long movie_id) {
        MovieList list = movieListService.findById(movie_id);
        return new ResponseEntity<MovieList>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{movieList_id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Long movie_id) {
        movieListService.deleteMovieById(movie_id);
        return new ResponseEntity<String>("Movie is deleted", HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<MovieList> getAllMovies() {
        return movieListService.findAllMovies();
    }

}
