import java.util.List;

public class MovieService {

    private final IMovieRepository movieRepository;

    public MovieService(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMoviesByCity(String city) {
        return movieRepository.getMoviesByCity(city);
    }

    public void addMovie(String city, Movie movie) {
        movieRepository.addMovie(city, movie);
    }

    public void deleteMovie(String city, Movie movie) {
        movieRepository.deleteMovie(city, movie);
    }
}
