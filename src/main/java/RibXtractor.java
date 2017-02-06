import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
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
                new ParsedFile(
                    new File(fileName)
                )
            )
            .save(
                new PrintWriter(
                    new FileWriter(
                        new File(fileName + ".gpx")
                    )
                )
            );
        }
        System.out.println(MESSAGE_DONE);
	}
}