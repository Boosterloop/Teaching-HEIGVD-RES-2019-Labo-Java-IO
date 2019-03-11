package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // Convert all the string in upper case then call write of superclass
    super.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // For all characters in cbuf, convert to upper case
    for(int i = 0; i < cbuf.length; i++) {
      cbuf[i] = Character.toUpperCase(cbuf[i]);
    }
    // Call write of superclass
    super.write(cbuf, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    // Call write of superclass with the upper case version of char
    super.write(Character.toUpperCase((char)c));
  }
}
