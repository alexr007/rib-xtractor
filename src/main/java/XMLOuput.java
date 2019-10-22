import java.io.PrintWriter;

final public class XMLOuput {
  private final ParsedFile origin;

  public XMLOuput(ParsedFile origin) {
    this.origin = origin;
  }

  final private class WriteIt {
    private final PrintWriter w;

    private WriteIt(PrintWriter w) {
      this.w = w;
    }

    private void writeFileHeader() {
      w.format("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n");
      w.format("<gpx xmlns=\"http://www.topografix.com/GPX/1/1\" xmlns:gpxx=\"http://www.garmin.com/xmlschemas/GpxExtensions/v3\" xmlns:gpxtpx=\"http://www.garmin.com/xmlschemas/TrackPointExtension/v1\" creator=\"Recon Instruments MOD / Flight HUD\" version=\"1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd http://www.garmin.com/xmlschemas/GpxExtensions/v3 http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd http://www.garmin.com/xmlschemas/TrackPointExtension/v1 http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd\">\n");
    }

    private void writeTrackHeader(Track t) {
      w.format("  <trk>\n");
      w.format("    <name>Track %d</name>\n", t.number());
      w.format("    <desc>%04d-%02d-%02d</desc>\n", t.year(), t.month(), t.day());
      w.format("    <trkseg>\n");
    }

    private void writePoint(Track t, TrackPoint pt)
    {
      w.format("      <trkpt lat=\"%1.6f\" lon=\"%1.6f\">\n", pt.lat(), pt.lon());
      w.format("        <ele>%d</ele>\n", pt.alt());
      w.format("        <name>%1.1f km/h</name>\n", pt.speed());
      w.format("        <time>%04d-%02d-%02dT%02d:%02d:%02dZ</time>\n", t.year(), t.month(), t.day(), pt.hour(), pt.min(), pt.sec());
      w.format("      </trkpt>\n");
    }

    private void writeTrackFooter() {
      w.format("    </trkseg>\n");
      w.format("  </trk>\n");
    }

    private void writeFileFooter() {
      w.format("</gpx>\n");
      w.close();
    }
  }

  public void save(PrintWriter writer) throws Exception {
    WriteIt wi = new WriteIt(writer);
    wi.writeFileHeader();
    for (Track t : origin.tracks()) {
      wi.writeTrackHeader(t);
      for (TrackPoint pt : t.items()) {
        wi.writePoint(t, pt);
      }
      wi.writeTrackFooter();
    }
    wi.writeFileFooter();
  }

}
