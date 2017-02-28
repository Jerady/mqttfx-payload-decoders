package de.jensd.addon.payloadconverter;

import de.jensd.addon.utils.ByteArray;

/**
 *
 * @author Jens Deters
 */
public class Base64Converter extends AbstractPayloadConverter {

    @Override
    public String convert(byte[] payload) {
        return ByteArray.asBase64(payload);
    }

    @Override
    public String id() {
        return "base64_converter";
    }

    @Override
    public String version() {
        return "0.0.1";
    }

    @Override
    public String description() {
        return "Converts the payload data into base64 code";
    }

    @Override
    public String name() {
        return "Base64 Converter";
    }

}
