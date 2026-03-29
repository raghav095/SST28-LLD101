import java.util.List;

public class Theatre {
    private final int id;
    private final String name;
    private final String city;

    private final List<Screen> screens;

    public Theatre(int id, String name, String city, List<Screen> screens) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.screens = screens;
    }

    public String getCity() { return city; }
    public List<Screen> getScreens() { return screens; }
}
