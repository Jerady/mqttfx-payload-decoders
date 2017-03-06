package de.jensd.addon.payloadconverter.preset;

import de.jensd.addon.payloadconverter.AbstractPayloadConverter;
import de.jensd.addon.utils.ByteArray;

/**
 * Converts the payload data into base64 code
 * 
 * @author Jens Deters
 * @version 1.0.0
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
        return "1.0.0";
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
