import java.io.File;
import java.util.Locale;

public class RibXtractor
{
	public static void main(String[] args) throws Exception {
        final String MESSAGE_MAIN = "RIB->GPX xtractor v 1.0\nUsage: ribXtractor.jar <rib file(s),space separated>";
        final String MESSAGE_DONE = "Done";
        final String MESSAGE_READING = "\nReading: ";

        Locale.setDefault(new Locale("en", "US"));
		System.out.println(MESSAGE_MAIN);
        for (String fileName : args) {
            System.out.println(MESSAGE_READING + fileName);
            new XMLOuput(
                new File(fileName + ".gpx")
            )
            .saveTracks(
                new ParsedFile(
                    new File(fileName)
                ).converted()
            );
        }
        System.out.println(MESSAGE_DONE);
	}
}