package de.jensd.addon.registry;

import de.jensd.addon.AddOnRegistryServiceLoader;
import de.jensd.addon.converter.PayloadConverter;
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
        List<PayloadConverter> converter = extensionRegistry.getAddOns(PayloadConverter.class);
        assertTrue(converter.size() == 3);
    }

    @Test
    public void testLoadExtensionsFromJar() {
        String lookupPath = "/Users/jens/NetBeansProjects/extensions-payload-converter/build/libs/";
        System.setProperty(AddOnRegistryServiceLoader.ADDON_LOOKUP_PATH_PROPERTY_NAME, lookupPath);
        AddOnRegistryServiceLoader extensionRegistry = new AddOnRegistryServiceLoader();
        List<PayloadConverter> converter = extensionRegistry.getAddOns(PayloadConverter.class);

    }
}
