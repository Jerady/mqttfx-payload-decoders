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

    public PlainTextDecoder() {
        idProperty().set("plain_text_decoder");
        nameProperty().set("Plain Text Decoder");
        versionProperty().set("1.0.0");
        descriptionProperty().set("Decodes the payload data into plain text");
    }

    @Override
    public String decode(byte[] payload) {
        return ByteArray.asString(payload);
    }
}
