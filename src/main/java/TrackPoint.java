import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class TrackPoint {
    private final Coordinate coordinate;
    private final double speed;
    private final LocalTime localTime;

    public TrackPoint(LogEntry entry) {
        this(
            entry.coordinate(),
            entry.speed(),
            entry.timestamp()
        );
    }

    private TrackPoint(Coordinate crd, double spd, long tsp) {
        this.coordinate = crd;
        this.speed = spd;
        this.localTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(tsp),
            ZoneId.systemDefault())
            .toLocalTime();
    }

    public double lat() {
        return coordinate.lat();
    }

    public double lon() {
        return coordinate.lon();
    }

    public int alt() {
        return coordinate.alt();
    }

    public double speed() {
        return speed;
    }

    public int hour() {
        return localTime.getHour();
    }

    public int min() {
        return localTime.getMinute();
    }

    public int sec() {
        return localTime.getSecond();
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d  speed: %.1f km/h  alt: %d   %.6f  %.6f", hour(), min(), sec(), speed(), alt(), lat(), lon());
    }
}
