package br.com.springcloud.skytickets.catalog.repository;

import br.com.springcloud.skytickets.catalog.repository.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    private static List<Movie> movieList =
            List.of(new Movie(1L, "Deadpool & Wolverine", "url-to-deadpool-wolverine-image", "10:00 AM"),
                    new Movie(2L, "Divertida Mente 2", "url-to-deadpool-2-image", "2:00 PM"),
                    new Movie(3L, "Um Lugar Silencioso: Dia Um", "url-to-um-lugar-silencioso-dia-um-imagee", "6:00 PM"));


    public List<Movie> findAll() {
        return movieList;
    }





}
