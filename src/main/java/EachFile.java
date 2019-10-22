import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by alexr on 11.02.2017.
 */
class EachFile {
  private final File in;
  private final File out;

  EachFile(File in, File out) {
    this.in = in;
    this.out = out;
  }

  void convert() throws Exception {
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
