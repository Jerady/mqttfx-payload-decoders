package de.jensd.addon.payloadconverter;

import de.jensd.addon.utils.ByteArray;

/**
 *
 * @author Jens Deters
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
        return "0.0.1";
    }

    @Override
    public String description() {
        return "Converts the payload data into hex code";
    }

    @Override
    public String name() {
        return "HEX Converter";
    }

}
