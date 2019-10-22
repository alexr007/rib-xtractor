import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class TrackPoint {
  private final Coordinate coordinate;
  private final double speed;
  private final LocalTime localTime;

  TrackPoint(LogEntry entry) {
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

  double lat() {
    return coordinate.lat();
  }

  double lon() {
    return coordinate.lon();
  }

  int alt() {
    return coordinate.alt();
  }

  double speed() {
    return speed;
  }

  int hour() {
    return localTime.getHour();
  }

  int min() {
    return localTime.getMinute();
  }

  int sec() {
    return localTime.getSecond();
  }

  @Override
  public String toString() {
    return String.format("%02d:%02d:%02d  speed: %.1f km/h  alt: %d   %.6f  %.6f", hour(), min(), sec(), speed(), alt(), lat(), lon());
  }
}
