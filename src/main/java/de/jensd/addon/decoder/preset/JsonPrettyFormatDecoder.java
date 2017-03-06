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

    @Override
    public String decode(byte[] payload) {
        return ByteArray.asJSONFormatted(payload);
    }

    @Override
    public String id() {
        return "json_pretty_format_decoder";
    }

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public String description() {
        return "Decodes JSON payload data into a readable format";
    }

    @Override
    public String name() {
        return "JSON Pretty Fomat Decoder";
    }

}
