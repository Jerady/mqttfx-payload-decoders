package de.jensd.addon.payloadconverter;

import de.jensd.addon.converter.PayloadConverter;

/**
 *
 * @author Jens Deters
 */
public class JsonPrettyConverter implements PayloadConverter {

    @Override
    public String convert(byte[] payload) {
        return ByteArray.asJSONFormatted(payload);
    }

    @Override
    public String name() {
        return "JsonFomatConverter";
    }

    @Override
    public String version() {
        return "0.0.1";
    }

    @Override
    public String toString() {
        return name();
    }
   
}
