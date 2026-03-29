import java.util.List;

public interface ITheatreRepository {
    void addTheatre(String city, Theatre theatre);
    void deleteTheatre(String city, Theatre theatre);
    List<Theatre> getTheatresByCity(String city);
}
