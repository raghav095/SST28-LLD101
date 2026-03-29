import java.util.ArrayList;
import java.util.List;

public class ShowService {

    public List<Show> getShowsByMovie(Movie movie) {
        return new ArrayList<>();
    }

    public List<ShowSeat> getSeats(Show show) {
        return show.getShowSeats();
    }
}
