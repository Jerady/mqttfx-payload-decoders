package de.jensd.addon.payloadconverter.preset;

import de.jensd.addon.payloadconverter.AbstractPayloadConverter;
import de.jensd.addon.utils.ByteArray;

/**
 *
 * Converts the payload data into plain text
 *
 * @author Jens Deters
 * @version 1.0.0
 */
public class PlainTextConverter extends AbstractPayloadConverter {

    @Override
    public String convert(byte[] payload) {
        return ByteArray.asString(payload);
    }

    @Override
    public String id() {
        return "plain_text_converter";
    }

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public String description() {
        return "Converts the payload data into plain text";
    }

    @Override
    public String name() {
        return "Plain Text Converter";
    }

}
