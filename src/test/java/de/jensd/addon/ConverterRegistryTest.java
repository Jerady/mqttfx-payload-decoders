package de.jensd.addon;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import de.jensd.addon.converter.PayloadConverter;

/**
 *
 * @author Jens Deters
 */
public class ConverterRegistryTest {

    @Test
    public void testLoadPayloadConverter() {
        AddOnRegistryServiceLoader registry = new AddOnRegistryServiceLoader();
        List<PayloadConverter> converters = registry.getAddOns(PayloadConverter.class);
        assertTrue("Expected to find 3 payload converters", converters.size() == 3);
    }

    
}
