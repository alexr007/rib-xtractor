public class Coordinate {
    private final double lat;
    private final double lon;
    private final int alt;

    public Coordinate(double lat, double lon, int alt) {
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
    }

    public double lat() {
        return lat;
    }

    public double lon() {
        return lon;
    }

    public int alt() {
        return alt;
    }

    @Override
    public String toString() {
        return String.format("lat:%.6f long:%.6f alt: %d", alt, lat, lon);
    }
}
