package de.jensd.addon.decoder.preset;

import de.jensd.addon.decoder.AbstractPayloadDecoder;
import de.jensd.addon.decoder.utils.ByteArray;

/**
 *
 * Decodes the payload data into plain text
 *
 * @author Jens Deters
 * @version 1.0.0
 */
public class PlainTextDecoder extends AbstractPayloadDecoder {

    @Override
    public String decode(byte[] payload) {
        return ByteArray.asString(payload);
    }

    @Override
    public String id() {
        return "plain_text_decoder";
    }

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public String description() {
        return "Decodes the payload data into plain text";
    }

    @Override
    public String name() {
        return "Plain Text Decoder";
    }

}
