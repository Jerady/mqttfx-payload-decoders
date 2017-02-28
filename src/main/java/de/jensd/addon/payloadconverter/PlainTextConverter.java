package de.jensd.addon.payloadconverter;

import de.jensd.addon.utils.ByteArray;

/**
 *
 * @author Jens Deters
 */
public class PlainTextConverter extends AbstractPayloadConverter{

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
        return "0.0.1";
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