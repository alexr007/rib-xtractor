import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class Track {
    private final LocalDate localDate;
    private final int trackNumber;
    private ArrayList<TrackPoint> items = new ArrayList<TrackPoint>(0);

    public Track(long timestamp, int trn) {
        this.localDate = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp),
            ZoneId.systemDefault())
            .toLocalDate();
        this.trackNumber = trn;
    }

    public void add(TrackPoint point) {
        items.add(point);
    }

    public int day() {
        return localDate.getDayOfMonth();
    }

    public int month() {
        return localDate.getMonthValue();
    }

    public int year() {
        return localDate.getYear();
    }

    public int number() {
        return trackNumber;
    }

    public ArrayList<TrackPoint> items() {
        return items;
    }

    @Override
    public String toString()
    {
        return String.format("Track: %d", number());
    }
}
