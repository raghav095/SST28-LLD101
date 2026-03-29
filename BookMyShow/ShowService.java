import java.util.List;

public class ShowService {

    private final IShowRepository showRepository;

    public ShowService(IShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> getShowsByMovie(Movie movie) {
        return showRepository.getShowsByMovie(movie);
    }

    public List<ShowSeat> getSeats(Show show) {
        return show.getShowSeats();
    }

    public void addShow(Show show) {
        showRepository.addShow(show);
    }

    public void deleteShow(Show show) {
        showRepository.deleteShow(show);
    }
}
