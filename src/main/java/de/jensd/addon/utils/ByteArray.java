package de.jensd.addon.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Jens Deters (jens.deters@codecentric.de)
 */
public class ByteArray {

    public static String asString(byte[] payload) {
        return new String(payload, Charset.forName("UTF-8"));
    }

    public static String asHex(byte[] payload) {
        return Hex.encodeHexString(payload);
    }

    public static String asBase64(byte[] payload) {
        return Base64.encodeBase64String(payload);
    }

    public static String asHexFormatted(byte[] payload) {
        StringBuilder builder = new StringBuilder(payload.length * 2);
        for (int i = 0; i < payload.length; i++) {
            builder.append(String.format("%02X", payload[i]));
            if (i % 2 == 1) {
                builder.append(" ");
            }
            if (i % 16 == 15) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public static String asJSONFormatted(byte[] payload) {
        String jsonString = asString(payload);
        ObjectMapper mapper = new ObjectMapper();
        String result;
        try {
            Object json = mapper.readValue(jsonString, Object.class);
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (IOException ex) {
            result = "*** PAYLOAD IS NOT VALID JSON DATA *** \n\n" + ex.getMessage();
        }
        return result;
    }
}
