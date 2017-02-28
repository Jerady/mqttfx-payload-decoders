package de.jensd.addon.registry;

import de.jensd.addon.AddOnRegistryServiceLoader;
import de.jensd.addon.payloadconverter.PayloadConverter;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jens Deters
 */
public class PayloadConverterRegistryTest {

    public PayloadConverterRegistryTest() {
    }

    @Test
    public void testLoadExtensions() {
        AddOnRegistryServiceLoader extensionRegistry = new AddOnRegistryServiceLoader();
        List<PayloadConverter> converters = extensionRegistry.getAddOns(PayloadConverter.class);
        assertTrue("Expected to find 4 payload converters", converters.size() == 4);
    }

    @Test
    public void testLoadExtensionsFromJar() {
        String lookupPath = "build/libs/";
        System.setProperty(AddOnRegistryServiceLoader.ADDON_LOOKUP_PATH_PROPERTY_NAME, lookupPath);
        AddOnRegistryServiceLoader extensionRegistry = new AddOnRegistryServiceLoader();
        List<PayloadConverter> converter = extensionRegistry.getAddOns(PayloadConverter.class);

    }
}
