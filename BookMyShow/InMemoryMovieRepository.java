import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryMovieRepository implements IMovieRepository {
    private final Map<String, List<Movie>> cityToMovies = new HashMap<>();

    @Override
    public void addMovie(String city, Movie movie) {
        cityToMovies.putIfAbsent(city, new ArrayList<>());
        cityToMovies.get(city).add(movie);
    }

    @Override
    public void deleteMovie(String city, Movie movie) {
        if (cityToMovies.containsKey(city)) {
            cityToMovies.get(city).remove(movie);
        }
    }

    @Override
    public List<Movie> getMoviesByCity(String city) {
        return cityToMovies.getOrDefault(city, new ArrayList<>());
    }
}
