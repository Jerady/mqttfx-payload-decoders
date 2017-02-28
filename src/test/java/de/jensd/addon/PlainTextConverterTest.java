package de.jensd.addon;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;
import static org.junit.Assert.*;
import de.jensd.addon.converter.PayloadConverter;

/**
 *
 * @author Jens Deters
 */
public class PlainTextConverterTest {

    @Test
    public void testPlainTextConverter() {
        final String CONTENT = "Hello World";
        final byte[] PAYLOAD = CONTENT.getBytes();
        AddOnRegistryServiceLoader registry = new AddOnRegistryServiceLoader();
        List<PayloadConverter> converters = registry.getAddOns(PayloadConverter.class);
        Map<String, PayloadConverter> convertersMap = converters.stream().collect(
                Collectors.toMap(c -> c.name(), c -> c));
        PayloadConverter converter = convertersMap.get("PlainTextConverter");
        assertNotNull("PlainTextConverter must not be null", converter);
        String converted = converter.convert(PAYLOAD);
        assertEquals(converted, CONTENT);
    }

}
