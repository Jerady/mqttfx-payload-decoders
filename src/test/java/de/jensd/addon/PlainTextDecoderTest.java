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
public class PlainTextDecoderTest {

    @Test
    public void testPlainTextDecoder() {
        final String CONTENT = "Hello World";
        final byte[] PAYLOAD = CONTENT.getBytes();
        AddOnRegistryServiceLoader registry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> decoders = registry.getAddOns(PayloadDecoder.class);
        Map<String, PayloadDecoder> decodersMap = decoders.stream().collect(
                Collectors.toMap(c -> c.getId(), c -> c));
        PayloadDecoder decoder = decodersMap.get("plain_text_decoder");
        assertNotNull("PlainTextDecoder must not be null", decoder);
        String decoded = decoder.decode(PAYLOAD);
        assertEquals(decoded, CONTENT);
    }

}
