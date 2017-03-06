package de.jensd.addon.decoder.preset;

import de.jensd.addon.decoder.AbstractPayloadDecoder;
import de.jensd.addon.decoder.utils.ByteArray;

/**
 * Converts the payload data into formatted hex code
 *
 * @author Jens Deters
 * @version 1.0.0
 */
public class HexFormatDecoder extends AbstractPayloadDecoder {

    @Override
    public String decode(byte[] payload) {
        return ByteArray.asHexFormatted(payload);
    }

    @Override
    public String id() {
        return "hex_format_decoder";
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
