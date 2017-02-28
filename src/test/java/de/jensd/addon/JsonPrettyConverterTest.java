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
public class JsonPrettyConverterTest {

    @Test
    public void testJsonFormatConverter() {
        final String CONTENT = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
        final byte[] PAYLOAD = CONTENT.getBytes();
        AddOnRegistryServiceLoader registry = new AddOnRegistryServiceLoader();
        List<PayloadConverter> converters = registry.getAddOns(PayloadConverter.class);
        converters.forEach(System.out::println);
        Map<String, PayloadConverter> convertersMap = converters.stream().collect(
                Collectors.toMap(c -> c.name(), c -> c));
        PayloadConverter converter = convertersMap.get("JsonFomatConverter");
        assertNotNull("JsonFomatConverter must not be null", converter);
        String converted = converter.convert(PAYLOAD);
        String[] lines = converted.split("\r\n|\r|\n");
        assertTrue(lines.length == 11);
    }

}
