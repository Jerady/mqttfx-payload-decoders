package de.jensd.addon;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;
import static org.junit.Assert.*;
import de.jensd.addon.decoder.PayloadDecoder;

/**
 *
 * @author Jens Deters
 */
public class JsonPrettyDecoderTest {

    @Test
    public void testJsonFormatDecoder() {
        final String CONTENT = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
        final byte[] PAYLOAD = CONTENT.getBytes();
        AddOnRegistryServiceLoader registry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> decoders = registry.getAddOns(PayloadDecoder.class);
        decoders.forEach(System.out::println);
        Map<String, PayloadDecoder> decodersMap = decoders.stream().collect(
                Collectors.toMap(c -> c.getId(), c -> c));
        PayloadDecoder decoder = decodersMap.get("json_pretty_format_decoder");
        assertNotNull("JsonPrettyDecoder must not be null", decoder);
        String decoded = decoder.decode(PAYLOAD);
        String[] lines = decoded.split("\r\n|\r|\n");
        assertTrue(lines.length == 11);
    }

}
