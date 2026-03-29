import java.util.List;

public interface IMovieRepository {
    void addMovie(String city, Movie movie);
    void deleteMovie(String city, Movie movie);
    List<Movie> getMoviesByCity(String city);
}
