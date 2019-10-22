/**
 * Created by alexr on 05.02.2017.
 */
public class LogEntry {
  private final byte[] origin;
  private static final int offsetTimestamp = 0;//len=4
  private static final int offsetLat = 7; //len=4
  private static final int offsetLon = 11; //len=4
  private static final int offsetAlt = 17; //len=2
  private static final int offsetSpeed = 15; //len=2

  private int decode2bytes(byte[] b, int offset) {
    return (b[offset+1]&0xFF)
        +((b[offset+0]&0xFF)<<8);
  }

  private long decode4bytes(byte[] b, int offset) {
    return (b[offset+3]&0xFF)
        +((b[offset+2]&0xFF)<<8)
        +((b[offset+1]&0xFF)<<16)
        +((b[offset+0]&0xFF)<<24);
  }

  private double decodeCoord(byte[] b, int offset) {
    int b0 = b[offset+0]&0xFF;
    int b1 = b[offset+1]&0xFF;
    int b2 = b[offset+2]&0xFF;
    int b3 = b[offset+3]&0xFF;
    double deg = b0;
    double min = (b1 & 0x7F) + b2 * 0.01f + b3 * 0.0001f;
    double value = deg+(min/60.0f);
    return ((b1 & 0x80) != 0) ? -value : value;
  }

  LogEntry(byte[] log_entry) {
    this.origin = log_entry.clone();
  }

  long timestamp() {
    return decode4bytes(origin, offsetTimestamp);
  }

  double speed() {
    return decode2bytes(origin, offsetSpeed) * 0.1f;
  }

  Coordinate coordinate() {
    return new Coordinate(
        decodeCoord(origin, offsetLat), // lat
        decodeCoord(origin, offsetLon), // lon
        decode2bytes(origin, offsetAlt) // alt
    );
  }

}
