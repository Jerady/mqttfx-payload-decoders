/**
 * Copyright (c) 2017 Jens Deters http://www.jensd.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package de.jensd.addon.registry;

import de.jensd.addon.AddOnRegistryServiceLoader;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import de.jensd.addon.decoder.PayloadDecoder;

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
        List<PayloadDecoder> converters = extensionRegistry.getAddOns(PayloadDecoder.class);
        assertTrue("Expected to find 5 payload decoders", converters.size() == 5);
    }

    @Test
    public void testLoadExtensionsFromJar() {
        String lookupPath = "build/libs/";
        System.setProperty(AddOnRegistryServiceLoader.ADDON_LOOKUP_PATH_PROPERTY_NAME, lookupPath);
        AddOnRegistryServiceLoader extensionRegistry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> converters = extensionRegistry.getAddOns(PayloadDecoder.class);
        converters.forEach(c -> {
            System.out.println(String.format("%-30s %-30s %-10s %s", c.getId(), c.getName(), c.getVersion(), c.getDescription()));
        });
        assertTrue("Expected to find 5 payload decoders", converters.size() == 5);
    }
}
