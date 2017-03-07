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

    public Base64Decoder() {
        idProperty().set("base64_decoder");
        nameProperty().set("Base64 Decoder");
        versionProperty().set("1.0.0");
        descriptionProperty().set("Decodes the payload data into base64 endcoding");
    }

    @Override
    public String decode(byte[] payload) {
        return ByteArray.asBase64(payload);
    }
    
}
