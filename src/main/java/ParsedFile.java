import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

class ParsedFile {
  private static final int ribHeaderSize = 14;
  private static final int ribEntrySize = 32;
  private final File file;

  ParsedFile(File file) {
    this.file = file;
  }

  ArrayList<Track> tracks() throws Exception {
    ArrayList<Track> tracks = new ArrayList<>();
    FileInputStream input = new FileInputStream(file);
    byte[] ribHeader = new byte[ribHeaderSize];
    byte[] ribEntry = new byte[ribEntrySize];
    int trackNumber = 0;
    if (input.read(ribHeader) != ribHeaderSize) {
      throw new Exception("Header read error");
    }
    while (input.read(ribEntry) == ribEntrySize) {
      LogEntry entry = new LogEntry(ribEntry);
      if (tracks.isEmpty()) {
        tracks.add(new Track(entry.timestamp(), trackNumber++));
      }
      tracks.get(0).add(new TrackPoint(entry));
    }
    return tracks;
  }
}
