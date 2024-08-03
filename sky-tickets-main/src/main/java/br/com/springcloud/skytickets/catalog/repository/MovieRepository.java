package br.com.springcloud.skytickets.catalog.repository;

import br.com.springcloud.skytickets.catalog.repository.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    private static List<Movie> movieList =
            List.of(new Movie(1L, "Deadpool & Wolverine", "img/deadpool-and-wolverine.png", "10:00 AM"),
                    new Movie(2L, "Divertida Mente 2", "img/divertida-mente-2.png", "2:00 PM"),
                    new Movie(3L, "Um Lugar Silencioso: Dia Um", "img/lugar-silencioso.png", "6:00 PM"));


    public List<Movie> findAll() {
        return movieList;
    }





}
