import java.util.List;

public class TheatreService {

    private final ITheatreRepository theatreRepository;

    public TheatreService(ITheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public List<Theatre> getTheatresByCity(String city) {
        return theatreRepository.getTheatresByCity(city);
    }

    public void addTheatre(String city, Theatre theatre) {
        theatreRepository.addTheatre(city, theatre);
    }

    public void deleteTheatre(String city, Theatre theatre) {
        theatreRepository.deleteTheatre(city, theatre);
    }
}
