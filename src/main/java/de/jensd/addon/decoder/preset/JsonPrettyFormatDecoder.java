package de.jensd.addon.decoder.preset;

import de.jensd.addon.decoder.AbstractPayloadDecoder;
import de.jensd.addon.decoder.utils.ByteArray;

/**
 * Decodes JSON payload data into a readable format
 *
 * @author Jens Deters
 * @version 1.0.0
 */
public class JsonPrettyFormatDecoder extends AbstractPayloadDecoder {

      public JsonPrettyFormatDecoder() {
        idProperty().set("json_pretty_format_decoder");
        nameProperty().set("JSON Pretty Fomat Decoder");
        versionProperty().set("1.0.0");
        descriptionProperty().set("Decodes JSON payload data into a readable format");
    }
    
    @Override
    public String decode(byte[] payload) {
        return ByteArray.asJSONFormatted(payload);
    }

}
