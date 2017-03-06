package de.jensd.addon.decoder.preset;

import de.jensd.addon.decoder.AbstractPayloadDecoder;
import de.jensd.addon.decoder.utils.ByteArray;

/**
 * Decodes the payload data into base64 code
 * 
 * @author Jens Deters
 * @version 1.0.0
 */
public class Base64Decoder extends AbstractPayloadDecoder {

    @Override
    public String decode(byte[] payload) {
        return ByteArray.asBase64(payload);
    }

    @Override
    public String id() {
        return "base64_decoder";
    }

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public String description() {
        return "Decodes the payload data into base64 endcoding";
    }

    @Override
    public String name() {
        return "Base64 Decoder";
    }

}
