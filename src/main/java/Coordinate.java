public class Coordinate {
  private final double lat;
  private final double lon;
  private final int alt;

  Coordinate(double lat, double lon, int alt) {
    this.lat = lat;
    this.lon = lon;
    this.alt = alt;
  }

  double lat() {
    return lat;
  }

  double lon() {
    return lon;
  }

  int alt() {
    return alt;
  }

  @Override
  public String toString() {
    return String.format("lat:%.6f long:%.6f alt: %d", lat, lon, alt);
  }
}
