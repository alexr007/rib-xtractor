import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class Track {
  private final LocalDate localDate;
  private final int trackNumber;
  private final ArrayList<TrackPoint> items = new ArrayList<>(0);

  Track(long timestamp, int trn) {
    this.localDate = LocalDateTime.ofInstant(
        Instant.ofEpochSecond(timestamp),
        ZoneId.systemDefault())
        .toLocalDate();
    this.trackNumber = trn;
  }

  void add(TrackPoint point) {
    items.add(point);
  }

  int day() {
    return localDate.getDayOfMonth();
  }

  int month() {
    return localDate.getMonthValue();
  }

  int year() {
    return localDate.getYear();
  }

  int number() {
    return trackNumber;
  }

  ArrayList<TrackPoint> items() {
    return items;
  }

  @Override
  public String toString()
  {
    return String.format("Track: %d", number());
  }
}
