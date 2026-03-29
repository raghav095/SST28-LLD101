import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryShowRepository implements IShowRepository {
    private final Map<Movie, List<Show>> movieToShows = new HashMap<>();

    @Override
    public void addShow(Show show) {
        movieToShows.putIfAbsent(show.getMovie(), new ArrayList<>());
        movieToShows.get(show.getMovie()).add(show);
    }

    @Override
    public void deleteShow(Show show) {
        if (movieToShows.containsKey(show.getMovie())) {
            movieToShows.get(show.getMovie()).remove(show);
        }
    }

    @Override
    public List<Show> getShowsByMovie(Movie movie) {
        return movieToShows.getOrDefault(movie, new ArrayList<>());
    }
}
