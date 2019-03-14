package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    String[] separators = {"\r\n", "\n", "\r"};
    String[] result = new String[2];

    int index = 0; // index of first char of separator in lines
    int separatorSize = 0;

    // For each separator
    for(int i = 0; i < separators.length; i++) {
      // Search if it is in lines
      index = lines.indexOf(separators[i]);
      if(index != -1) { // If it is
        separatorSize = separators[i].length(); // Save lenght of separator
        result[0] = lines.substring(0, index + separatorSize); // Save first line
        break;
      }
    }

    // If it does not have any line separator
    if(index == -1) {
      result[0] = "";
      result[1] = lines;
    } else {
      result[1] = lines.substring(index + separatorSize);
    }
    return result;
  }
}
