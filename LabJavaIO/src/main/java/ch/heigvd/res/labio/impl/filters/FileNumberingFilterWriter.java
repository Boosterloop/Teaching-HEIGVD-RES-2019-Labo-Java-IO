package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private int lineNumber = 1; // Line number for next line
  private boolean firstLine = true; // First time we write in this writer
  private boolean newLine = false; // We need to do a new line
  private char lastChar; // last char written
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      for(int i = 0; i < len; i++){
        if(i + off >= str.length()){
          break;
        }
        // Call write(int c)
        write(str.charAt(i + off));
      }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // Call write(String str, int off, int len)
    write(cbuf.toString(), off, len);
  }

  @Override
  public void write(int c) throws IOException {

    // First char written needs a new line
    if(firstLine) {
      out.write(lineNumber + "\t");
      lineNumber++;
      firstLine = false;
    }
    // \n always means we need a new line
    if(c == '\n') {
      newLine = true;
    }
    // If we have \r but no \n we need a new line
    if(lastChar == '\r' && c != '\n') {
      out.write(lineNumber + "\t");
      lineNumber++;
    }

    // Write char and save it
    out.write(c);
    lastChar = (char)c;

    // Make a new line with number and \t
    if(newLine) {
      out.write(lineNumber + "\t");
      lineNumber++;
      newLine = false;
    }
  }
}
