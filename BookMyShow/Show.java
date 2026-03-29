import java.util.Date;
import java.util.List;

public class Show {
    private final int id;
    private final Movie movie;
    private final Screen screen;
    private final ShowType type;
    private final Date startTime;

    private final List<ShowSeat> showSeats;

    public Show(int id, Movie movie, Screen screen,
                ShowType type, Date startTime,
                List<ShowSeat> showSeats) {

        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.type = type;
        this.startTime = startTime;
        this.showSeats = showSeats;
    }

    public List<ShowSeat> getShowSeats() { return showSeats; }
    public Movie getMovie() { return movie; }
    public Screen getScreen() { return screen; }
    public ShowType getType() { return type; }
    public Date getStartTime() { return startTime; }
}
