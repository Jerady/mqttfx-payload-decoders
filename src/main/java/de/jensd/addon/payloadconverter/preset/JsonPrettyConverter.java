package de.jensd.addon.payloadconverter.preset;

import de.jensd.addon.payloadconverter.AbstractPayloadConverter;
import de.jensd.addon.utils.ByteArray;

/**
 * Converts json payload data into a pretty readable format
 *
 * @author Jens Deters
 * @version 1.0.0
 */
public class JsonPrettyConverter extends AbstractPayloadConverter {

    @Override
    public String convert(byte[] payload) {
        return ByteArray.asJSONFormatted(payload);
    }

    @Override
    public String id() {
        return "json_pretty_format_converter";
    }

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public String description() {
        return "Converts json payload data into a pretty readable format";
    }

    @Override
    public String name() {
        return "JSON Pretty Fomat Converter";
    }

}
