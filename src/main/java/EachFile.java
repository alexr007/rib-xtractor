import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by alexr on 11.02.2017.
 */
public class EachFile {
    private final File in;
    private final File out;

    public EachFile(File in, File out) {
        this.in = in;
        this.out = out;
    }

    public void convert() throws Exception {
        new XMLOuput(
            new ParsedFile(in)
        )
        .save(
            new PrintWriter(
                new FileWriter(out)
            )
        );
    }
}
