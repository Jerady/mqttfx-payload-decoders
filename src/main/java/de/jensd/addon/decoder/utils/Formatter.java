package de.jensd.addon.decoder.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 *
 * @author Jens Deters (jens.deters@softblade.de)
 */
public class Formatter {

  private Formatter(){}

  public static String asJSONFormatted(String jsonObject) {
    ObjectMapper mapper = new ObjectMapper();
    String result;
    try {
      Object json = mapper.readValue(jsonObject, Object.class);
      result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    } catch (IOException ex) {
      result = "*** PAYLOAD IS NOT VALID JSON DATA *** \n\n" + ex.getMessage();
    }
    return result;
  }
}
