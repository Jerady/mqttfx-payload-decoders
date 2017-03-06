package de.jensd.addon.payloadconverter.preset;

import de.jensd.addon.payloadconverter.AbstractPayloadConverter;
import de.jensd.addon.utils.ByteArray;

/**
 * Converts the payload data into formatted hex code
 *
 * @author Jens Deters
 * @version 1.0.0
 */
public class HexConverter extends AbstractPayloadConverter {

    @Override
    public String convert(byte[] payload) {
        return ByteArray.asHexFormatted(payload);
    }

    @Override
    public String id() {
        return "hex_converter";
    }

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public String description() {
        return "Converts the payload data into formatted hex code";
    }

    @Override
    public String name() {
        return "HEX Converter";
    }

}
