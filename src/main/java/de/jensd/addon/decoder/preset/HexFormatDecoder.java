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

    public HexFormatDecoder() {
        idProperty().set("hex_format_decoder");
        nameProperty().set("Hex Format Decoder");
        versionProperty().set("1.0.0");
        descriptionProperty().set("Decodes the payload data into a hex formatted string");
    }

    @Override
    public String decode(byte[] payload) {
        return ByteArray.asHexFormatted(payload);
    }

}
