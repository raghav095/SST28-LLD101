import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTheatreRepository implements ITheatreRepository {
    private final Map<String, List<Theatre>> cityToTheatres = new HashMap<>();

    @Override
    public void addTheatre(String city, Theatre theatre) {
        cityToTheatres.putIfAbsent(city, new ArrayList<>());
        cityToTheatres.get(city).add(theatre);
    }

    @Override
    public void deleteTheatre(String city, Theatre theatre) {
        if (cityToTheatres.containsKey(city)) {
            cityToTheatres.get(city).remove(theatre);
        }
    }

    @Override
    public List<Theatre> getTheatresByCity(String city) {
        return cityToTheatres.getOrDefault(city, new ArrayList<>());
    }
}
