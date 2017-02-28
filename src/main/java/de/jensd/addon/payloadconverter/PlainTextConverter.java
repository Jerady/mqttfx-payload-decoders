package de.jensd.addon.payloadconverter;

import de.jensd.addon.converter.PayloadConverter;

/**
 *
 * @author Jens Deters
 */
public class PlainTextConverter implements PayloadConverter {

    @Override
    public String convert(byte[] payload) {
        return ByteArray.asString(payload);
    }

    @Override
    public String name() {
        return "PlainTextConverter";
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
