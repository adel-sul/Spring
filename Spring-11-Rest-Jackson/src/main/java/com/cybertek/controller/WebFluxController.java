package com.cybertek.controller;

import com.cybertek.entity.Genre;
import com.cybertek.entity.MovieCinema;
import com.cybertek.repository.GenreRepository;
import com.cybertek.repository.MovieCinemaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class WebFluxController {
    /*
        Spring WebFlux implements reactive programming via Reactive Streams.
        In Spring WebFlux, the data returned from any operation is packed into a reactive stream.
        There are two types that embody this approach and are the building blocks in WebFlux applications - Mono and Flux.
     */
    // Creating WebClient Instance
    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    private MovieCinemaRepository movieCinemaRepository;
    private GenreRepository genreRepository;

    public WebFluxController(MovieCinemaRepository movieCinemaRepository, GenreRepository genreRepository) {
        this.movieCinemaRepository = movieCinemaRepository;
        this.genreRepository = genreRepository;
    }

    // Flux is a stream which returns zero or more items (0..N).
    // Used when we're expecting multiple results or a collection of some sort.
    @GetMapping("/flux-movie-cinemas")
    public Flux<MovieCinema> readAllCinemaFlux() {
        return Flux.fromIterable(movieCinemaRepository.findAll());
    }

    // Mono is a stream which returns zero items or a single item (0..1)
    // Used when we're expecting a single (or none) result, such as retrieving a unique user from the database
    @GetMapping("/mono-movie-cinema/{id}")
    public Mono<MovieCinema> readByIdMono(@PathVariable("id") Long id) {
        return Mono.just(movieCinemaRepository.findByMovieCinemaId(id));
    }

    @GetMapping("/mono-movie-cinema")
    public Mono<MovieCinema> readByIdRequestParam(@RequestParam("id") Long id) {
        return Mono.just(movieCinemaRepository.findByMovieCinemaId(id));
    }

    @PostMapping("/create-genre")
    public Mono<Genre> createGenre(@RequestBody Genre genre) {
        // return Mono.just(genreRepository.save(genre));
        Genre createdGenre = genreRepository.save(genre);
        return Mono.just(createdGenre);
    }

    @PutMapping("/update-genre")
    public Mono<Genre> updateGenre(@RequestBody Genre genre) {
        Genre updatedGenre = genreRepository.save(genre);
        return Mono.just(updatedGenre);
    }

    @DeleteMapping("/delete-genre/{id}")
    public Mono<Void> deleteGenre(@PathVariable("id") Long id) {
        genreRepository.deleteById(id);
        return Mono.empty();
    }

    //------------------------- WEBCLIENT EXAMPLE -------------------------//
    /*
        WebClient is a reactive, non-blocking client for HTTP requests with a functional-style API client and Reactive Streams support.
        By comparison to the RestTemplate, WebClient is:
            1. Non-blocking, reactive and supports higher concurrency with less hardware resources
            2. Provides a functional API that takes advantage of Java 8 lambdas
            3. Supports both synchronous and asynchronous scenarios
     */

    @GetMapping("/flux")
    public Flux<MovieCinema> readWithWebClient(){
        return webClient
                .get()                                                                  //
                .uri("/flux-movie-cinemas")                                          // Setting request URI
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)     // Setting request headers and authentication details
                .retrieve()                                                             // retrieve()method directly performs the HTTP request and retrieve the response body.
                .bodyToFlux(MovieCinema.class);
    }

    @GetMapping("/mono/{id}")
    public Mono<MovieCinema> readMonoWithWebClient(@PathVariable("id") Long id) {
        return webClient
                .get()
                .uri("/mono-movie-cinema/{id}")
                .retrieve()
                .bodyToMono(MovieCinema.class);
    }

    @GetMapping("/mono-rp")
    public Mono<MovieCinema> readMonoWithWebClientRequestParam(@RequestParam("id") Long id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                    .path("/mono-movie-cinema")
                    .queryParam("id", id)
                    .build())
                .retrieve()
                .bodyToMono(MovieCinema.class);
    }

    @PostMapping("/create")
    public Mono<Genre> createGenreWebClient(@RequestBody Genre genre) {
        return webClient
                .post()
                .uri("/create-genre")
                .body(Mono.just(genre), Genre.class)                                       // Setting the request body
                .retrieve()
                .bodyToMono(Genre.class);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteGenreWebClient(@PathVariable("id") Long id) throws Exception {

        Integer countGenres = genreRepository.countGenresNativeQuery(id);
        if (countGenres > 0) { throw new RuntimeException("Genre can't be deleted, it is linked with movie(s)"); }

        return webClient
                .delete()
                .uri("/delete-genre/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
