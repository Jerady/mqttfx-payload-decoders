package de.jensd.addon;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import de.jensd.addon.decoder.PayloadDecoder;

/**
 *
 * @author Jens Deters
 */
public class DecoderRegistryTest {

    @Test
    public void testLoadPayloadDecoders() {
        AddOnRegistryServiceLoader registry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> decoders = registry.getAddOns(PayloadDecoder.class);
        assertTrue("Expected to find 4 payload decoders", decoders.size() == 4);
    }

    
}
