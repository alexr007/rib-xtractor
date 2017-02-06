import java.io.PrintWriter;

final public class XMLOuput {
    private final ParsedFile origin;

    public XMLOuput(ParsedFile origin) {
        this.origin = origin;
    }

    final private class WriteIt {
        private final PrintWriter writer;

        private WriteIt(PrintWriter writer) {
            this.writer = writer;
        }

        private void writeFileHeader() {
            writer.format("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n");
            writer.format("<gpx xmlns=\"http://www.topografix.com/GPX/1/1\" xmlns:gpxx=\"http://www.garmin.com/xmlschemas/GpxExtensions/v3\" xmlns:gpxtpx=\"http://www.garmin.com/xmlschemas/TrackPointExtension/v1\" creator=\"Recon Instruments MOD / Flight HUD\" version=\"1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd http://www.garmin.com/xmlschemas/GpxExtensions/v3 http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd http://www.garmin.com/xmlschemas/TrackPointExtension/v1 http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd\">\n");
        }

        private void writeTrackHeader(Track t) {
            writer.format("  <trk>\n");
            writer.format("    <name>Track %d</name>\n", t.number());
            writer.format("    <desc>%04d-%02d-%02d</desc>\n", t.year(), t.month(), t.day());
            writer.format("    <trkseg>\n");
        }

        private void writePoint(Track t, TrackPoint pt)
        {
            writer.format("      <trkpt lat=\"%1.6f\" lon=\"%1.6f\">\n", pt.lat(), pt.lon());
            writer.format("        <ele>%d</ele>\n", pt.alt());
            writer.format("        <name>%1.1f km/h</name>\n", pt.speed());
            writer.format("        <time>%04d-%02d-%02dT%02d:%02d:%02dZ</time>\n", t.year(), t.month(), t.day(), pt.hour(), pt.min(), pt.sec());
            writer.format("      </trkpt>\n");
        }

        private void writeTrackFooter() {
            writer.format("    </trkseg>\n");
            writer.format("  </trk>\n");
        }

        private void writeFileFooter() {
            writer.format("</gpx>\n");
            writer.close();
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
