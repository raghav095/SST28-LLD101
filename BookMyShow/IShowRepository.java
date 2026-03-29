import java.util.List;

public interface IShowRepository {
    void addShow(Show show);
    void deleteShow(Show show);
    List<Show> getShowsByMovie(Movie movie);
}
