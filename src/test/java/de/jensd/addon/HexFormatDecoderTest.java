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
public class HexFormatDecoderTest {

    @Test
    public void testHexFormatDecoder() {
        final String CONTENT = "Hello World";
        final byte[] PAYLOAD = CONTENT.getBytes();
        AddOnRegistryServiceLoader registry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> decoders = registry.getAddOns(PayloadDecoder.class);
        Map<String, PayloadDecoder> decodersMap = decoders.stream().collect(
                Collectors.toMap(c -> c.id(), c -> c));
        PayloadDecoder decoder = decodersMap.get("hex_format_decoder");
        assertNotNull("HexFormatDecoder must not be null", decoder);
        String decoded = decoder.decode(PAYLOAD);
        assertEquals(decoded, "4865 6C6C 6F20 576F 726C 64");
    }

}
