package de.jensd.addon.payloadconverter;

import de.jensd.addon.utils.ByteArray;

/**
 *
 * @author Jens Deters
 */
public class JsonPrettyConverter  extends AbstractPayloadConverter {

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
        return "0.0.1";
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
