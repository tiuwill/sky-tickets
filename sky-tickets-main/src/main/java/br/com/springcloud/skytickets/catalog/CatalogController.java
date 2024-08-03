package br.com.springcloud.skytickets.catalog;

import br.com.springcloud.skytickets.catalog.repository.MovieRepository;
import br.com.springcloud.skytickets.catalog.repository.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }
}