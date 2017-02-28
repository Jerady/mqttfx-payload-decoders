package de.jensd.addon.payloadconverter;

import de.jensd.addon.converter.PayloadConverter;

/**
 *
 * @author Jens Deters
 */
public class ThriftConverter implements PayloadConverter {

    @Override
    public String convert(byte[] payload) {
        return ByteArray.asHex(payload);
    }

    @Override
    public String name() {
        return "ThriftConverter";
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
